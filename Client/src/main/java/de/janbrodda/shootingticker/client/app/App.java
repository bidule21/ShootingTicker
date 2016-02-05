package de.janbrodda.shootingticker.client.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.janbrodda.shootingticker.client.settings.Settings;
import de.janbrodda.shootingticker.client.api.API;
import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.files.FileLister;
import de.janbrodda.shootingticker.client.files.FileWatcher;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileLister;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileParser;

public class App {
	private static App instance;

	private Settings settings;
	private API api;

	private File selectedCompetitionFolder;
	private Competition selectedCompetition;

	private FileWatcher fileWatcher;
	private boolean isUploading = false;
	private int lastUploadTimestamp;
	
	private final Runnable uploadRunnable = new Runnable() {
		@Override
		public void run() {
			System.out.println("Uploading");
			instance.startSynchronousCompetitionUpload();
		}
	};

	private App(Settings settings, API api) {
		if (settings.competitionBasePath == null || settings.competitionBasePath.equals("")) {
			throw new IllegalArgumentException("settings parameter -competitionBasePath- missing");
		}

		this.settings = settings;
		this.api = api;
	}

	public static App get() {
		if (instance == null) {
			Settings settings = Settings.get();
			API api = API.get();
			instance = new App(settings, api);
		}
		return instance;
	}

	public List<Competition> getRemoteCompetitions() {
		return api.loadAllRemoteCompetitions();
	}

	public void selectCompetition(Competition competition) {
		if (competition.id > 0L && competition.numShots > 0) {
			selectedCompetition = competition;
		} else {
			throw new IllegalArgumentException("The provided competition has missing fields (id,numShots)");
		}
	}

	public void selectCompetitionFolder(File file) {
		if (file.isDirectory() && file.canRead()) {
			selectedCompetitionFolder = file;
		} else {
			throw new IllegalArgumentException("Cant read selected folder, or it is not a directory");
		}
	}

	public List<FolderData> getAvailableFolders() {
		List<FolderData> result = new ArrayList<>();
		List<File> directories = FileLister.getDirectoriesWithXmlContent(settings.competitionBasePath);

		for (File file : directories) {
			String folderName = file.getAbsolutePath().replace(settings.competitionBasePath, "");

			FolderData folder = new FolderData();
			folder.file = file;
			folder.name = folderName;
			result.add(folder);
		}

		return result;
	}

	public void startCompetitionUpload() {
		if (selectedCompetition == null || selectedCompetitionFolder == null || isUploading) {
			throw new IllegalArgumentException("Application state not correct. {" + selectedCompetition + ","
					+ selectedCompetitionFolder + "," + isUploading);
		}

		final App app = this;

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					app.stopCompetitionUpload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}));

		isUploading = true;
		fileWatcher = new FileWatcher(selectedCompetitionFolder);
		uploadRunnable.run();
		fileWatcher.addFileHandler(uploadRunnable);
	}

	private void startSynchronousCompetitionUpload() {
		if (selectedCompetition == null || selectedCompetitionFolder == null) {
			throw new IllegalArgumentException(
					"Application state not correct. {" + selectedCompetition + "," + selectedCompetitionFolder);
		}

		// Protect us from too many frequent uploads
		if (System.currentTimeMillis() - settings.minSecondsBetweenUploads * 1000 < lastUploadTimestamp) {
			return;
		}

		List<File> shooterFiles = ShooterFileLister.getUniqueShooterFiles(selectedCompetitionFolder);
		selectedCompetition.teams = ShooterFileParser.parseCompetition(shooterFiles).teams;

		api.saveCompetition(selectedCompetition);
	}

	public boolean stopCompetitionUpload() {
		if (isUploading) {
			if (fileWatcher != null) {
				fileWatcher.stopWatching();
			}
			isUploading = false;
			return true;
		} else {
			return false;
		}

	}
}

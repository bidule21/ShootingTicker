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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

	private static App instance;

	private final Settings settings;
	private final API api;

	private File selectedLocalCompetitionFolder;
	private Competition selectedRemoteCompetition;

	private FileWatcher fileWatcher;
	private boolean isUploading = false;
	private int lastUploadTimestamp;

	public List<Runnable> uploadStartedHandlers = new ArrayList<>();
	public List<Runnable> uploadStoppedHandlers = new ArrayList<>();
	public List<Runnable> uploadActiveHandlers = new ArrayList<>();

	private final Runnable uploadRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				System.out.println("Uploading");
				instance.startSynchronousCompetitionUpload();
			} catch (IOException ex) {
				Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	};

	private App(Settings settings, API api) {
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

	public List<Competition> getRemoteCompetitions() throws IOException {
		return api.loadAllRemoteCompetitions();
	}

	public void saveCompetition(Competition competition) throws IOException {
		api.saveCompetition(competition);
	}

	public void deleteCompetition(Competition competition) throws IOException {
		api.deleteCompetition(competition);
	}

	public void selectRemoteCompetition(Competition competition) {
		if (competition.id > 0L && competition.numShots > 0) {
			selectedRemoteCompetition = competition;
		} else {
			throw new IllegalArgumentException("The provided competition has missing fields (id,numShots)");
		}
	}

	public void selectLocalCompetitionFolder(File file) {
		if (file.isDirectory() && file.canRead()) {
			selectedLocalCompetitionFolder = file;
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
		if (selectedRemoteCompetition == null || selectedLocalCompetitionFolder == null || isUploading) {
			throw new IllegalArgumentException("Application state not correct. {" + selectedRemoteCompetition + ","
					+ selectedLocalCompetitionFolder + "," + isUploading);
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

		for (Runnable uploadStartedHandler : uploadStartedHandlers) {
			uploadStartedHandler.run();
		}

		isUploading = true;
		fileWatcher = new FileWatcher(selectedLocalCompetitionFolder);
		uploadRunnable.run();
		fileWatcher.addFileHandler(uploadRunnable);
	}

	private void startSynchronousCompetitionUpload() throws IOException {
		if (selectedRemoteCompetition == null || selectedLocalCompetitionFolder == null) {
			throw new IllegalArgumentException(
					"Application state not correct. {" + selectedRemoteCompetition + "," + selectedLocalCompetitionFolder);
		}

		// Protect us from too many frequent uploads
		if (System.currentTimeMillis() - settings.minSecondsBetweenUploads * 1000 < lastUploadTimestamp) {
			return;
		}

		for (Runnable uploadActiveHandler : uploadActiveHandlers) {
			uploadActiveHandler.run();
		}

		List<File> shooterFiles = ShooterFileLister.getUniqueShooterFiles(selectedLocalCompetitionFolder);
		selectedRemoteCompetition.teams = ShooterFileParser.parseCompetition(shooterFiles).teams;

		api.saveCompetition(selectedRemoteCompetition);
	}

	public boolean stopCompetitionUpload() {
		boolean result;

		if (isUploading) {
			if (fileWatcher != null) {
				fileWatcher.stopWatching();
			}
			isUploading = false;
			result = true;
		} else {
			result = false;
		}

		for (Runnable uploadStoppedHandler : uploadStoppedHandlers) {
			uploadStoppedHandler.run();
		}

		return result;

	}
}

package de.janbrodda.shootingticker.client.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.janbrodda.shootingticker.client.Settings;
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

	private boolean isUploading = false;
	private File selectedCompetitionFolder;
	private Competition selectedCompetition;

	private Thread uploadThread = new Thread();

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
				} catch (Exception ignored) {
				}
			}
		}));

		uploadThread = new Thread() {
			@Override
			public void run() {
				isUploading = true;
				FileWatcher fileWatcher = new FileWatcher();
				fileWatcher.watchDirectory(selectedCompetitionFolder);
				fileWatcher.addFileHandler(new Runnable() {
					@Override
					public void run() {
						app.startSynchronousCompetitionUpload();
					}
				});
				while (!Thread.currentThread().isInterrupted()) {
				}
				fileWatcher.stopWatching();
				interrupt();
			}
		};

		/*new Thread(
		new Runnable() {
			@Override
			public void run() {
				FileWatcher fileWatcher = new FileWatcher();
				fileWatcher.watchDirectory(selectedCompetitionFolder);
				fileWatcher.addFileHandler(new Runnable() {
					@Override
					public void run() {
						app.startSynchronousCompetitionUpload();
					}
				});
			}
		});*/
		uploadThread.start();
	}

	private void startSynchronousCompetitionUpload() {
		if (selectedCompetition == null || selectedCompetitionFolder == null) {
			throw new IllegalArgumentException("Application state not correct. {" + selectedCompetition + ","
					+ selectedCompetitionFolder);
		}

		List<File> shooterFiles = ShooterFileLister.getUniqueShooterFiles(selectedCompetitionFolder);
		selectedCompetition.teams = ShooterFileParser.parseCompetition(shooterFiles).teams;

		api.saveCompetition(selectedCompetition);
	}

	public boolean stopCompetitionUpload() {
		if (!uploadThread.isInterrupted()) {
			uploadThread.interrupt();
			isUploading = false;
			return true;
		} else {
			return false;
		}

	}
}

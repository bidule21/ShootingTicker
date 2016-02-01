package de.janbrodda.shootingticker.client.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileWatcher implements FileAlterationListener {
	private final List<File> watchedDirectories = new ArrayList<>();
	private final FileAlterationMonitor monitor = new FileAlterationMonitor(5000);

	private final List<Runnable> directoryActionHandlers = new ArrayList<>();
	private long lastDirectoryActionCall = 0;

	private final List<Runnable> fileActionHandlers = new ArrayList<>();
	private long lastFileActionCall = 0;

	public FileWatcher() {
		try {
			monitor.start();
		} catch (Exception ex) {
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					monitor.stop();
				} catch (Exception ignored) {
				}
			}
		}));
	};

	private void callDirectoryHandlers() {
		long currentActionCall = System.currentTimeMillis();
		if (currentActionCall - lastDirectoryActionCall < 1000) {
			return;
		} else {
			lastDirectoryActionCall = Long.MAX_VALUE;
		}

		for (Runnable runnable : directoryActionHandlers) {
			runnable.run();
		}

		lastDirectoryActionCall = System.currentTimeMillis();
	}

	public void addDirectoryHandler(Runnable runnable) {
		if (!directoryActionHandlers.contains(runnable)) {
			directoryActionHandlers.add(runnable);
		}
	}

	private void callFileHandlers() {
		long currentActionCall = System.currentTimeMillis();
		if (currentActionCall - lastFileActionCall < 1000) {
			return;
		} else {
			lastFileActionCall = Long.MAX_VALUE;
		}

		for (Runnable runnable : fileActionHandlers) {
			runnable.run();
		}

		lastFileActionCall = System.currentTimeMillis();
	}

	public void addFileHandler(Runnable runnable) {
		if (!fileActionHandlers.contains(runnable)) {
			fileActionHandlers.add(runnable);
		}
	}

	public void watchDirectory(String directory) {
		if (watchedDirectories.contains(directory)) {
			throw new IllegalArgumentException("I am already watching this Directory.");
		}

		File file = FileUtils.getReadableDirectory(directory);
		FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(file);
		fileAlterationObserver.addListener(this);
		monitor.addObserver(fileAlterationObserver);
	}

	@Override
	public void onDirectoryChange(File arg0) {
		callDirectoryHandlers();
	}

	@Override
	public void onDirectoryCreate(File arg0) {
		callDirectoryHandlers();
	}

	@Override
	public void onDirectoryDelete(File arg0) {
		callDirectoryHandlers();
	}

	@Override
	public void onFileChange(File arg0) {
		callFileHandlers();
	}

	@Override
	public void onFileCreate(File arg0) {
		callFileHandlers();
	}

	@Override
	public void onFileDelete(File arg0) {
		callFileHandlers();
	}

	@Override
	public void onStart(FileAlterationObserver arg0) {
	}

	@Override
	public void onStop(FileAlterationObserver arg0) {
	}

}

package de.janbrodda.shootingticker.client.files;

import java.io.File;
import java.security.AccessControlException;

public class FileUtils {
	protected FileUtils() {
	}

	public static File getReadableDirectory(String directory) {
		final File file = new File(directory);

		if (!file.isDirectory() || !file.exists()) {
			throw new IllegalArgumentException("The provided File has to be a Directory.");
		}
		if (!file.canRead()) {
			throw new AccessControlException("Can't read the specified Directory");
		}

		return file;
	}
}

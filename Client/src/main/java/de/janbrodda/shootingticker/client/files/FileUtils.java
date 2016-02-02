package de.janbrodda.shootingticker.client.files;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

	public static String getExecutableFilePath() {
		String parentPath = "";
		String parentPathEncoded = new File(FileUtils.class.getProtectionDomain()
				.getCodeSource()
				.getLocation()
				.getPath())
				.getParent();

		try {
			parentPath = URLDecoder.decode(parentPathEncoded, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		return parentPath;
	}
}

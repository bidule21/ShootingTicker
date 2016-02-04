package de.janbrodda.shootingticker.client.files;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class FileLister {
	protected FileLister() {
	}

	public static List<File> getDirectoriesWithXmlContent(String directory) {
		File file = FileUtils.getReadableDirectory(directory);
		Map<String, File> foundDirs = new HashMap<>();

		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, new RegexFileFilter("^(.*).xml$"),
				DirectoryFileFilter.DIRECTORY);

		for (File foundFile : files) {
			String foundPath = foundFile.getParent();
			if (!foundDirs.containsKey(foundPath)) {
				foundDirs.put(foundPath, foundFile.getParentFile());
			}
		}

		return new ArrayList<File>(foundDirs.values());
	}

	public static List<File> getDirectories(File file) {
		File[] resultArray = file.listFiles((FileFilter) FileFilterUtils.directoryFileFilter());
		return Arrays.asList(resultArray);
	}

	public static List<File> getFiles(File file) {
		Collection<File> result = org.apache.commons.io.FileUtils.listFiles(file, new RegexFileFilter("^(.*).xml$"),
				DirectoryFileFilter.DIRECTORY);
		return new ArrayList<File>(result);
	}

}

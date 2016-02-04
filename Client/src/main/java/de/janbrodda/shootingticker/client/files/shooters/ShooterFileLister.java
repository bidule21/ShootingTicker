package de.janbrodda.shootingticker.client.files.shooters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.janbrodda.shootingticker.client.files.FileLister;

public class ShooterFileLister {
	protected ShooterFileLister() {
	}

	public static List<File> getUniqueShooterFiles(File file) {
		List<File> subDirectories = FileLister.getDirectories(file);
		if (subDirectories.size() > 0) {
			throw new IllegalArgumentException("The provided Directory is not in the correct Format.");
		}

		List<File> allFiles = FileLister.getFiles(file);
		List<File> uniqueFiles = new ArrayList<>();
		Map<String, Integer> laneIdToFileId = new HashMap<>();

		// Go through all Files and save fileId and laneId
		for (File singleFile : allFiles) {
			String fileName = singleFile.getName();
			String[] fileNameParts = fileName.split("_");
			int fileId = Integer.parseInt(fileNameParts[0]);
			String laneId = fileNameParts[2].substring(0, fileNameParts[2].length() - 4);

			if (laneIdToFileId.containsKey(laneId) && laneIdToFileId.get(laneId) < fileId) {
				laneIdToFileId.put(laneId, fileId);
			} else if (!laneIdToFileId.containsKey(laneId)) {
				laneIdToFileId.put(laneId, fileId);
			}
		}

		// Go through all Files and save newest File to output List
		for (File singleFile : allFiles) {
			String fileName = singleFile.getName();
			String[] fileNameParts = fileName.split("_");
			int fileId = Integer.parseInt(fileNameParts[0]);
			if (laneIdToFileId.containsValue(fileId)) {
				uniqueFiles.add(singleFile);
			}
		}

		return uniqueFiles;
	}
}

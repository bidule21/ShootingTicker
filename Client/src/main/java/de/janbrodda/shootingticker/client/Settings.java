package de.janbrodda.shootingticker.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class Settings {
	private String apiUrl;
	private String apiKey;
	private String competitionBasePath;

	private static Settings instance;
	private static File settingsFile;
	private static final Charset settingsCharset = StandardCharsets.UTF_8;

	static {
		try {
			settingsFile = new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().toURI()
					.getPath() + "/settings.json");
		} catch (URISyntaxException ex) {
		}
	}

	/**
	 * Class for saving settings data. Get instance by calling get().
	 * Data is automatically saved when changed.
	 */
	private Settings() {
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
		save();
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
		save();
	}

	public void setCompetitionBasePath(String competitionBasePath) {
		this.competitionBasePath = competitionBasePath;
		save();
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getCompetitionBasePath() {
		return competitionBasePath;
	}

	public static Settings get() {
		if (instance == null) {
			try {
				Gson gson = new Gson();
				String settingsJson = new String(Files.readAllBytes(Paths.get(settingsFile.getAbsolutePath())), settingsCharset);
				instance = gson.fromJson(settingsJson, Settings.class);
			} catch (IOException ex) {
				System.err.println("Can't read Settings File.");
			}
		}

		return instance;
	}

	private void save() {
		Gson gson = new Gson();
		String settingsJson = gson.toJson(this);

		try {
			PrintWriter writer = new PrintWriter(settingsFile, settingsCharset.toString());
			writer.println(settingsJson);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException ex) {
			System.err.println("Can't save Settings to File.");
		}
	}

}

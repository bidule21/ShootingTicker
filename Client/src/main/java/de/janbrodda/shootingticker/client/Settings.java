package de.janbrodda.shootingticker.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.client.files.FileUtils;

/**
 * Class for saving settings data. Get instance by calling get().
 * Remember to call save() when you changed data.
 */
public class Settings {

	public String apiUrl;
	public String apiKey;

	public String competitionBasePath;
	
	public int minSecondsBetweenUploads = 2;

	public String proxyHost;
	public int proxyPort = 8080;
	public String proxyUser;
	public String proxyPass;
	public boolean useProxy = false;

	private static Settings instance;
	private static File settingsFile;
	private static final Charset settingsCharset = StandardCharsets.UTF_8;

	static {
		try {
			settingsFile = new File(FileUtils.getExecutableFilePath() + "/settings.json");

			if (!settingsFile.exists()) {
				PrintWriter writer = new PrintWriter(settingsFile, settingsCharset.toString());
				writer.println("{}");
				writer.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Settings() {
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

	public void save() {
		if (instance == null) {
			return;
		}

		Gson gson = new Gson();
		String settingsJson = gson.toJson(instance);

		try {
			PrintWriter writer = new PrintWriter(settingsFile, settingsCharset.toString());
			writer.println(settingsJson);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException ex) {
			System.err.println("Can't save Settings to File.");
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

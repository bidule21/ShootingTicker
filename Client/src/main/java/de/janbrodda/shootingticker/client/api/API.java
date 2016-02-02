package de.janbrodda.shootingticker.client.api;

import java.util.List;

import de.janbrodda.shootingticker.client.Settings;
import de.janbrodda.shootingticker.client.data.Competition;

public class API {
	private static API instance;

	private Settings settings;

	private API(Settings settings) {
		this.settings = settings;
	}

	public static API get() {
		if (instance == null) {
			Settings settings = Settings.get();
			instance = new API(settings);
		}
		return instance;
	}

	public List<Competition> getRemoveCompetitions() {
		//TODO
		return null;
	}
}

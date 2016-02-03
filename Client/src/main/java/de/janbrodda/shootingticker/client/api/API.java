package de.janbrodda.shootingticker.client.api;

import java.util.List;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.client.Settings;
import de.janbrodda.shootingticker.client.api.WebRequest.Method;
import de.janbrodda.shootingticker.client.data.Competition;

public class API {
	private static API instance;
	private static Gson gson = new Gson();

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

	public List<Competition> loadAllRemoteCompetitions() {
		WebRequest request = new WebRequest(Method.GET, settings.apiUrl + "/api/get");

		String resultJson = request.load();
		Response response = gson.fromJson(resultJson, Response.class);

		return response.data.competitions;
	}

	public Competition loadSingleRemoteCompetition(long competitionId) {
		WebRequest request = new WebRequest(Method.GET, settings.apiUrl + "/api/get/" + competitionId);

		String resultJson = request.load();
		Response response = gson.fromJson(resultJson, Response.class);

		return response.data.competitions.get(0);
	}
}

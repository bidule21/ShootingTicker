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
		if (settings.apiKey == null) {
			throw new IllegalArgumentException("settings parameter -apiKey- missing");
		} else if (settings.apiUrl == null) {
			throw new IllegalArgumentException("settings parameter -apiUrl- missing");
		}

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

		if (response.status != Response.Status.Success) {
			throw new RemoteApiException(response.message);
		}

		return response.data.competitions;
	}

	public Competition loadSingleRemoteCompetition(long competitionId) {
		WebRequest request = new WebRequest(Method.GET, settings.apiUrl + "/api/get/" + competitionId);

		String resultJson = request.load();
		Response response = gson.fromJson(resultJson, Response.class);

		if (response.status != Response.Status.Success) {
			throw new RemoteApiException(response.message);
		}

		return response.data.competitions.get(0);
	}

	public void saveCompetition(Competition competition) {
		WebRequest request = new WebRequest(Method.POST, settings.apiUrl + "/api/put");
		request.parameters.put("apikey", settings.apiKey);
		request.parameters.put("competition", gson.toJson(competition));

		String resultJson = request.load();
		Response response = gson.fromJson(resultJson, Response.class);

		if (response.status != Response.Status.Success) {
			throw new RemoteApiException(response.message);
		}
	}

	public void deleteCompetition(Competition Competition) {
		WebRequest request = new WebRequest(Method.POST, settings.apiUrl + "/api/delete");
		request.parameters.put("apikey", settings.apiKey);
		request.parameters.put("competitionid", Competition.id + "");

		String resultJson = request.load();
		Response response = gson.fromJson(resultJson, Response.class);

		if (response.status != Response.Status.Success) {
			throw new RemoteApiException(response.message);
		}
	}
}

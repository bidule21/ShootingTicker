package de.janbrodda.shootingticker.server.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.janbrodda.shootingticker.server.Database;
import de.janbrodda.shootingticker.server.data.Competition;
import de.janbrodda.shootingticker.server.data.Team;
import de.janbrodda.shootingticker.server.response.Response;
import de.janbrodda.shootingticker.server.response.Response.Status;
import de.janbrodda.shootingticker.server.response.ResponseHandler;

public class OutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1382170629285320626L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long competitionId = 0;
		try {
			competitionId = Long.parseLong(req.getPathInfo().substring(1));
		} catch (NumberFormatException | NullPointerException e) {
		}

		List<Competition> competitions = new ArrayList<>();

		if (competitionId > 0) { // Fetch single Competition
			long serverTimestamp = 0;
			long clientTimestamp = -1;
			try {
				clientTimestamp = Long.parseLong(req.getParameter("t"));
			} catch (NumberFormatException e) {
			}

			Competition result = Database.getCompetitionById(competitionId);

			if (result == null){
				Response response = new Response();
				response.status = Status.Error;
				response.message = "No Competition found for ID: "+competitionId;
				ResponseHandler.setResponse(resp, response);
				return;
			}

			serverTimestamp = result.timestamp;
			if (serverTimestamp <= clientTimestamp) {
				resp.setStatus(304);
				return;
			}

			competitions.add(result);

		} else { // Fetch Competition Overview
			competitions = Database.getAllCompetitions();
			for (Competition competition : competitions) {
				for (Team team : competition.teams) {
					team.shooters = null;
				}
			}
		}

		Response response = new Response();
		response.status = Status.Success;
		response.data.put("competitions", competitions);
		ResponseHandler.setResponse(resp, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Response response = new Response();
		response.status = Response.Status.Error;
		response.message = "POST Requests are not allowed";
		ResponseHandler.setResponse(resp, response);
	}
}

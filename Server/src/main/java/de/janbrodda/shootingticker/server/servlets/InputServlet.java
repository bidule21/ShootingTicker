package de.janbrodda.shootingticker.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.server.Database;
import de.janbrodda.shootingticker.server.Settings;
import de.janbrodda.shootingticker.server.data.Competition;
import de.janbrodda.shootingticker.server.response.Response;
import de.janbrodda.shootingticker.server.response.Response.Status;
import de.janbrodda.shootingticker.server.response.ResponseHandler;

public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = -4076175774959467997L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Response response = new Response();
		response.status = Response.Status.Error;
		response.message = "No Get Requests allowed for Input";
		ResponseHandler.setResponse(resp, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String competitionJson = req.getParameter("competition");
		Response response = new Response();

		if (!Settings.apiKey.equals(req.getParameter("apikey"))) {
			response.status = Status.Error;
			response.message = "Wrong API Key";
			ResponseHandler.setResponse(resp, response);
			return;
		}

		if (competitionJson == null) {
			response.status = Response.Status.Error;
			response.message = "No Data found";
			ResponseHandler.setResponse(resp, response);
			return;
		}

		Gson gson = new Gson();
		Competition competition = gson.fromJson(competitionJson, Competition.class);

		if (competition == null) {
			response.status = Response.Status.Error;
			response.message = "Competition Data was no valid JSON";
			return;
		}

		Long sourceCompetitionId = competition.id;

		boolean validationError = false;
		if (competition.remainingSeconds == null) {
			response.message = "Missing Field - remainingSeconds";
			validationError = true;
		} else if (competition.numShots == null || competition.numShots == 0) {
			response.message = "Missing Field - numShots";
			validationError = true;
		}

		if (validationError) {
			response.status = Response.Status.Error;
			ResponseHandler.setResponse(resp, response);
			return;
		}

		long insertTimestamp = System.currentTimeMillis();
		competition.timestamp = insertTimestamp;
		Long insertedCompetitionId = Database.saveCompetition(competition);

		if (insertedCompetitionId.equals(sourceCompetitionId)) {
			response.message = "Competition updated";
			response.method = Response.Method.Update;
		} else {
			response.message = "Competition inserted";
			response.method = Response.Method.Insert;
		}

		response.status = Response.Status.Success;
		response.data.put("id", insertedCompetitionId);
		response.data.put("timestamp", insertTimestamp);
		ResponseHandler.setResponse(resp, response);

	}
}

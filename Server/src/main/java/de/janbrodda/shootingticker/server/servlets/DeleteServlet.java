package de.janbrodda.shootingticker.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.janbrodda.shootingticker.server.Database;
import de.janbrodda.shootingticker.server.Settings;
import de.janbrodda.shootingticker.server.response.Response;
import de.janbrodda.shootingticker.server.response.Response.Status;
import de.janbrodda.shootingticker.server.response.ResponseHandler;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = -4076175774959467997L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Response response = new Response();
		response.status = Response.Status.Error;
		response.message = "No Get Requests allowed for Deletion";
		ResponseHandler.setResponse(resp, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Response response = new Response();

		if (!Settings.apiKey.equals(req.getParameter("apikey"))) {
			response.status = Status.Error;
			response.message = "Wrong API Key";
			ResponseHandler.setResponse(resp, response);
			return;
		}

		Long competitionId = Long.parseLong(req.getParameter("competitionId"));
		Database.deleteCompetition(competitionId);

		response.status = Response.Status.Success;
		response.message = "Competition deleted";
		response.data.put("id", competitionId);
		ResponseHandler.setResponse(resp, response);

	}
}

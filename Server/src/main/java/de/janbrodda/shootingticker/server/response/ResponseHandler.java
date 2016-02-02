package de.janbrodda.shootingticker.server.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseHandler {
	public static void setResponse(HttpServletResponse resp, Response response) {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		try {
			resp.getWriter().print(gson.toJson(response));
		} catch (IOException e) {
		}
	}
}

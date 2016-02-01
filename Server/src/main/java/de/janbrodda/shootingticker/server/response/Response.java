package de.janbrodda.shootingticker.server.response;

import java.util.HashMap;
import java.util.Map;

public class Response {
	public static enum Status {
		Success,
		Error
	}
	public static enum Method {
		Select,
		Insert,
		Update, 
		Delete
	}
	
	public Status status;
	public Method method;
	public String message;
	public Map<String, Object> data = new HashMap<>();
}

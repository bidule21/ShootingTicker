package de.janbrodda.shootingticker.client.api;

public class RemoteApiException extends RuntimeException {
	private static final long serialVersionUID = -3065904652787797986L;

	public RemoteApiException() {
		super();
	}

	public RemoteApiException(String message) {
		super(message);
	}

	public RemoteApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public RemoteApiException(Throwable cause) {
		super(cause);
	}

}

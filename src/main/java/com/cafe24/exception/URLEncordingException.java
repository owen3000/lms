package com.cafe24.exception;

public class URLEncordingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public URLEncordingException() {
		super("URLEncordingException Occurs");
	}
	
	public URLEncordingException(String message) {
		super(message);
	}
}

package com.gl.app.exception;

public class SIMDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SIMDoesNotExistsException(String message) {
		super(message);
	}

	public SIMDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}

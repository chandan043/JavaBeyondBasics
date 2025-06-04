package com.gl.app.exception;

public class CustomerDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerDoesNotExistException(String message) {
		super(message);
	}

	public CustomerDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}

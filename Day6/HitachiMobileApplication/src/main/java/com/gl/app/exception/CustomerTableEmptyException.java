package com.gl.app.exception;

public class CustomerTableEmptyException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CustomerTableEmptyException(String message) {
		super(message);
	}
	

}

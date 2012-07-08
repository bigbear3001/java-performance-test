package com.perhab.napalm.statement;

public class InvocationError extends RuntimeException {

	public InvocationError(String message, Exception e) {
		super(message, e);
	}

}

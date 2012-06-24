package com.perhab.napalm.statement;

public class StatementNotInitalizableException extends RuntimeException {

	public StatementNotInitalizableException(String message, Exception e) {
		super(message, e);
	}

	public StatementNotInitalizableException(String message) {
		super(message);
	}

}

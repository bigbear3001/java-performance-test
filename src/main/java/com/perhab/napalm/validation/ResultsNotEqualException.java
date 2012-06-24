package com.perhab.napalm.validation;

import com.perhab.napalm.Result;

public class ResultsNotEqualException extends RuntimeException {

	/**
	 * generated unique serialization id;
	 */
	private static final long serialVersionUID = 2329473630934454322L;

	public ResultsNotEqualException(final Result first, final Result second) {
		super(first + " is not equal to " + second);
	}

}

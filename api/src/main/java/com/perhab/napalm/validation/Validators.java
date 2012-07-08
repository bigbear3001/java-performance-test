package com.perhab.napalm.validation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.perhab.napalm.Result;

public class Validators extends Vector<Validator> {

	/**
	 * generated unique serialization id
	 */
	private static final long serialVersionUID = 4970889867408284714L;

	public void validate(Collection<Result> results) {
		for(Validator validator : this) {
			validator.validate(results);
		}
	}

}

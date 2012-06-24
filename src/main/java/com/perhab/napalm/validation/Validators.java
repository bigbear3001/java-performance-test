package com.perhab.napalm.validation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.perhab.napalm.Result;

public class Validators extends Vector<Validator> {

	public void validate(Collection<Result> results) {
		for(Validator validator : this) {
			validator.validate(results);
		}
	}

}

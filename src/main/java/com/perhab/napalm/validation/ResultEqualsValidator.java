package com.perhab.napalm.validation;

import java.util.Collection;
import java.util.Iterator;

import com.perhab.napalm.Result;

public class ResultEqualsValidator implements Validator {

	public void validate(Collection<Result> results) {
		Iterator<Result> iterator = results.iterator();
		Result first = iterator.next();
		while (iterator.hasNext()) {
			Result second = iterator.next();
			if (!first.equals(second)) {
				throw new ResultsNotEqualException(first, second);
			}
		}
	}

}

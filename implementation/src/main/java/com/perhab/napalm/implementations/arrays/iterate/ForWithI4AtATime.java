package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Ignore;

public class ForWithI4AtATime implements ArrayIteration {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i += 4) {
			result += integers[i]
					+ integers[i + 1]
					+ integers[i + 2]
					+ integers[i + 3];
		}
		return result;
	}

}

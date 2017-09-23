package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Ignore;

public class ForWithI16AtATime implements ArrayIteration {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i += 16) {
			result += integers[i]
					+ integers[i + 1]
					+ integers[i + 2]
					+ integers[i + 3]
					+ integers[i + 4]
					+ integers[i + 5]
					+ integers[i + 6]
					+ integers[i + 7]
					+ integers[i + 8]
					+ integers[i + 9]
					+ integers[i + 10]
					+ integers[i + 11]
					+ integers[i + 12]
					+ integers[i + 13]
					+ integers[i + 14]
					+ integers[i + 15];
		}
		return result;
	}

}

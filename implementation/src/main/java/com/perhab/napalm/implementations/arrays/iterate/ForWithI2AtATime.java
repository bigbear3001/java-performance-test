package com.perhab.napalm.implementations.arrays.iterate;


public class ForWithI2AtATime implements ArrayIteration {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i += 2) {
			result += integers[i]
					+ integers[i + 1];
		}
		return result;
	}

}

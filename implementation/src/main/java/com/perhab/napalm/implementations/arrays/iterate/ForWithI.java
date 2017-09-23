package com.perhab.napalm.implementations.arrays.iterate;

public class ForWithI implements ArrayIterationUnevenLength {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i++) {
			result += integers[i];
		}
		return result;
	}

}

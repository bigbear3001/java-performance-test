package com.perhab.napalm.implementations.arrays.iterate;

public class ShortFor implements ArrayIterationUnevenLength {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (Integer integer : integers) {
			result += integer;
		}
		return result;
	}

}

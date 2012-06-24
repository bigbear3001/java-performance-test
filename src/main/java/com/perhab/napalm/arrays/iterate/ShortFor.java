package com.perhab.napalm.arrays.iterate;

public class ShortFor implements ArrayIteration {

	public Integer sum(final Integer[] integers) {
		Integer result = new Integer(0);
		for (Integer integer : integers) {
			result += integer;
		}
		return result;
	}

}

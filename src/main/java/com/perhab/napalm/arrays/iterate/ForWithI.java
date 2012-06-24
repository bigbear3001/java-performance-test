package com.perhab.napalm.arrays.iterate;

public class ForWithI implements ArrayIteration {

	public Integer sum(final Integer[] integers) {
		Integer result = new Integer(0);
		for (int i = 0; i < integers.length; i++) {
			result += integers[i];
		}
		return result;
	}

}

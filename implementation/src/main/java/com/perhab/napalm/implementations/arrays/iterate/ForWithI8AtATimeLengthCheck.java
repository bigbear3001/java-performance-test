package com.perhab.napalm.implementations.arrays.iterate;

public class ForWithI8AtATimeLengthCheck implements ArrayIterationUnevenLength {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i += 8) {
			result += integers[i]
					+ (integers.length > i + 1 ? integers[i + 1] : 0)
					+ (integers.length > i + 2 ? integers[i + 2] : 0)
					+ (integers.length > i + 3 ? integers[i + 3] : 0)
					+ (integers.length > i + 4 ? integers[i + 4] : 0)
					+ (integers.length > i + 5 ? integers[i + 5] : 0)
					+ (integers.length > i + 6 ? integers[i + 6] : 0)
					+ (integers.length > i + 7 ? integers[i + 7] : 0);
		}
		return result;
	}

}

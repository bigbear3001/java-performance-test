package com.perhab.napalm.implementations.arrays.iterate;

public class ForWithI8AtATimeLengthCheckStaticFunction implements ArrayIterationUnevenLength {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		for (int i = 0; i < integers.length; i += 8) {
			result += integers[i]
					+ getSafe(integers, i + 1)
					+ getSafe(integers, i + 2)
					+ getSafe(integers, i + 3)
					+ getSafe(integers, i + 4)
					+ getSafe(integers, i + 5)
					+ getSafe(integers, i + 6)
					+ getSafe(integers, i + 7);
		}
		return result;
	}

	private static Integer getSafe(Integer[] integers, int index) {
		return integers.length > index ? integers[index] : 0;
	}

}

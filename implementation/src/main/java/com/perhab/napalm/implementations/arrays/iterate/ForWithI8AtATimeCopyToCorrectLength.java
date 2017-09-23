package com.perhab.napalm.implementations.arrays.iterate;

import java.util.Arrays;

public class ForWithI8AtATimeCopyToCorrectLength implements ArrayIterationUnevenLength {

	@Override
	public Integer sum(final Integer[] integers) {
		Integer result = 0;
		int neededLength = integers.length + (8 - integers.length % 8) % 8;
		Integer[] integersCorrectLength = Arrays.copyOf(integers, neededLength);
		for (int i = integers.length; i < integersCorrectLength.length; i++) {
			integersCorrectLength[i] = 0;
		}
		for (int i = 0; i < integersCorrectLength.length; i += 8) {
			result += integersCorrectLength[i]
					+ integersCorrectLength[i + 1]
					+ integersCorrectLength[i + 2]
					+ integersCorrectLength[i + 3]
					+ integersCorrectLength[i + 4]
					+ integersCorrectLength[i + 5]
					+ integersCorrectLength[i + 6]
					+ integersCorrectLength[i + 7];
		}
		return result;
	}

}

package com.perhab.napalm.implementations.string.concat;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;


public interface StringConcatination {

	/**
	 * put the strings a and b together.
	 * @param a - first string
	 * @param b - second string
	 * @return a + b
	 */
	@Execute(parameters = {@Parameter("a"), @Parameter("b") }, iterations = {1, Execute.HUNDRED, Execute.TEN_THOUSAND, Execute.MILLION })
	String concat(String a, String b);
}

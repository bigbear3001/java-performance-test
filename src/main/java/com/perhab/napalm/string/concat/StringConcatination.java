package com.perhab.napalm.string.concat;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;


public interface StringConcatination {

	@Execute(parameters = {@Parameter("a"), @Parameter("b")})
	public String concat(String a, String b);
}

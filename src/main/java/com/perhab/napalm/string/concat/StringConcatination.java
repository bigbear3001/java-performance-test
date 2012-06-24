package com.perhab.napalm.string.concat;

import com.perhab.napalm.statement.Execute;

public interface StringConcatination {

	@Execute
	public String concat(String a, String b);
}

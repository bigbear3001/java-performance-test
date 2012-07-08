package com.perhab.napalm.implementations.string.replace;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface CharacterReplace {
	
	@Execute(parameters = {@Parameter(Parameter.ANY_STRING)})
	String replace(String string);
}

package com.perhab.napalm.implementations.string.replace;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface CharacterReplace {
	
	@Execute(parameters = {@Parameter(Parameter.ANY_STRING)})
	@Description("Tests the performance of replacing a certain character in a String")
	String replace(String string);
}

package com.perhab.napalm.implementations.generics.methods;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface CallMethod {
	
	@Execute(parameters=@Parameter("A"))
	@Description("Tests the performance of calling a method on an object of (currently) unkown type.")
	String callMethod(String parameter) throws Exception;
}

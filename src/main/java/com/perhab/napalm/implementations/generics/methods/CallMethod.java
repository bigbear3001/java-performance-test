package com.perhab.napalm.implementations.generics.methods;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface CallMethod {
	
	@Execute(parameters=@Parameter("A"))
	public String callMethod(String parameter) throws Exception;
}

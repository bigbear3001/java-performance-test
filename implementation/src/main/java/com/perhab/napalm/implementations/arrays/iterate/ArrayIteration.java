package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface ArrayIteration {
	
	@Execute(parameters = {@Parameter(arrayDefinition = "1...20")})
	Integer sum(final Integer[] integers);
}

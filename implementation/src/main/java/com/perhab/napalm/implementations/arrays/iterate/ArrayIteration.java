package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface ArrayIteration {
	
	@Execute(parameters = {@Parameter(arrayDefinition = "1...20")})
	@Description("Tests the performance of creating the sum of an integer array.")
	Integer sum(final Integer[] integers);
}

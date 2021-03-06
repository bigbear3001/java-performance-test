package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface ArrayIteration {

	@Execute(parameters = {
			// Array length needs to be a multiple of 16 currently (not all classes have length checks)
			@Parameter(arrayDefinition = "1...64")
	})
	@Description("Tests the performance of creating the sum of an integer array.")
	Integer sum(final Integer[] integers);
}

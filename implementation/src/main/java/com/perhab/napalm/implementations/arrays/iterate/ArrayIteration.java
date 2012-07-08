package com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface ArrayIteration {
	
	@Execute(parameters = {@Parameter("1"), @Parameter("2") })
	public Integer sum(final Integer[] integers);
}

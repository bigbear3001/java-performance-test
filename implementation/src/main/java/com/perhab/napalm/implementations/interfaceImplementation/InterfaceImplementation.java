package com.perhab.napalm.implementations.interfaceImplementation;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface InterfaceImplementation {

	@Execute(parameters = {
			// Array length needs to be a multiple of 16 currently (not all classes have length checks)
			@Parameter("1"),
			@Parameter("2")
	}, iterations = {1, 1000, 1000000, 10000000})
	@Description("Tests the performance of implementing an interface.")
	Integer add(Integer a, Integer b) throws Exception;
}

package com.perhab.napalm.implementations.generics.methods.findconstructor;

import java.lang.reflect.Constructor;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface FindConstructor {
	
	@Execute(parameters = @Parameter())
	@Description("Tests the performance of finding a constructor with reflection.")
	Constructor<?> findConstructor() throws Exception;
}

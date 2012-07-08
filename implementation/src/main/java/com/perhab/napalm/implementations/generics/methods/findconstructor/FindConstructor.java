package com.perhab.napalm.implementations.generics.methods.findconstructor;

import java.lang.reflect.Constructor;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

public interface FindConstructor {
	
	@Execute(parameters = @Parameter())
	Constructor<?> findConstructor() throws Exception;
}

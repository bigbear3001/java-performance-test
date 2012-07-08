package com.perhab.napalm.implementations.generics.methods.findconstructor;

import java.lang.reflect.Constructor;

public class FindConstructorDirty implements FindConstructor {

	@Override
	public final Constructor<?> findConstructor() throws Exception {
		return SampleClassForFindConstructor.class.getConstructor(new Class<?>[]{String.class});
	}

}

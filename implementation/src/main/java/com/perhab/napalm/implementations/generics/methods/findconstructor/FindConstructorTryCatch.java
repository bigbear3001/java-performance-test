package com.perhab.napalm.implementations.generics.methods.findconstructor;

import java.lang.reflect.Constructor;

public class FindConstructorTryCatch implements FindConstructor {

	@Override
	public final Constructor<?> findConstructor() {
		try {
			return SampleClassForFindConstructor.class.getConstructor(new Class<?>[]{String.class});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}

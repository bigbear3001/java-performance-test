package com.perhab.napalm.implementations.generics.methods.findconstructor;

import java.lang.reflect.Constructor;

public class FindConstructorIterationAndComparison implements FindConstructor {

	@Override
	public final Constructor<?> findConstructor() {
		for (Constructor<?> constructor : SampleClassForFindConstructor.class.getConstructors()) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
				return constructor;
			}
		}
		return null;
	}

}

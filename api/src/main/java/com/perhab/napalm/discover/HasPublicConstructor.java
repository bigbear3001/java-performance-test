package com.perhab.napalm.discover;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import com.google.common.base.Predicate;

public class HasPublicConstructor implements Predicate<Class<?>> {

	static HasPublicConstructor instance;
	
	@Override
	public boolean apply(final Class<?> input) {
		for (Constructor<?> constructor : input.getConstructors()) {
			if (constructor.getParameterTypes().length == 0
					&& Modifier.isPublic(constructor.getModifiers())) {
				return true;
			}
		}
		return false;
	}

	public static synchronized HasPublicConstructor get() {
		if (instance == null) {
			instance = new HasPublicConstructor();
		}
		return instance;
	}

}

package com.perhab.napalm.discover;

import javassist.Modifier;

import com.google.common.base.Predicate;

public class IsAbstract implements Predicate<Class<?>> {

	private static IsAbstract instance;

	@Override
	public final boolean apply(final Class<?> input) {
		return Modifier.isAbstract(input.getModifiers());
	}
	
	public static synchronized IsAbstract get() {
		if (instance == null) {
			instance = new IsAbstract();
		}
		return instance;
	}

}

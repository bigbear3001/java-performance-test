package com.perhab.napalm.implementations.generics.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionMethodCall extends AbstractCallMethod {
	
	private Method method;
	
	public ReflectionMethodCall() {
		initMethod();
	}

	protected void initMethod() {
		try {
			method = AbstractCallMethod.class.getMethod("method", String.class);
		} catch (SecurityException e) {
			log.error("Cannot get the method needed for this test.", e);
		} catch (NoSuchMethodException e) {
			log.error("Cannot get the method needed for this test.", e);
		}
	}

	@Override
	public String callMethod(final String parameter) {
		try {
			return (String) method.invoke(this, parameter);
		} catch (IllegalArgumentException e) {
			log.error("Cannot invoke method needed for this test.", e);
		} catch (IllegalAccessException e) {
			log.error("Cannot invoke method needed for this test.", e);
		} catch (InvocationTargetException e) {
			log.error("Cannot invoke method needed for this test.", e);
		}
		return null;
	}

}

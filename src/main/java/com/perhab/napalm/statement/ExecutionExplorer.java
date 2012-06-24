package com.perhab.napalm.statement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class ExecutionExplorer {

	private ExecutionExplorer() { }
	
	public static Method findExecutionMethod(Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			Execute annotation = method.getAnnotation(Execute.class);
			if (annotation != null) {
				return method;
			}
		}
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			Method method = findExecutionMethod(superclass);
			if (method != null) {
				return method;
			}
		}
		for (Class<?> interfaceClass : clazz.getInterfaces()) {
			Method method = findExecutionMethod(interfaceClass);
			if (method != null) {
				return method;
			}
		}
		return null;
	}
}

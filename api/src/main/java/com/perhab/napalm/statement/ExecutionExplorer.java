package com.perhab.napalm.statement;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Slf4j
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

	public static Object[] getArguments(Method method) {
		Execute execution = method.getAnnotation(Execute.class);
		Parameter[] parameters = execution.parameters();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] values = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			Object parameter = parameters[i].value();
			if (parameterTypes[i].isAssignableFrom(parameter.getClass())) {
				values[i] = parameter;
			} else if (parameterTypes[i].isArray()) {
				values[i] = convertToArray(parameterTypes[i], parameters);
			} else {
				values[i] = convertTo(parameterTypes[i], parameter);
			}
		}
		return values;
	}

	/**
	 * @param method - method to inspect for the {@link Execute} annotation where the iterations are set.
	 * @return iterations set for method
	 */
	public static int[] getIterations(final Method method) {
		Execute execution = method.getAnnotation(Execute.class);
		return execution.iterations();
	}

	private static Object convertToArray(Class<?> clazz, Parameter[] parameters) {
		String arrayClassName = clazz.getName().substring(2, clazz.getName().length() - 1);
		Class<?> arrayClass;
		try {
			arrayClass = Class.forName(arrayClassName);
		} catch (ClassNotFoundException e) {
			log.error("Cannot find class for array name {}", clazz, e);
			throw new StatementNotInitalizableException("Cannot find class for array name " + clazz, e);
		}
		Object[] values = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			values[i] = convertTo(arrayClass, parameters[i].value());
		}
		return values;
	}

	private static Object convertTo(Class<?> clazz, Object parameter) {
		if (clazz.isAssignableFrom(parameter.getClass())) {
			return clazz.cast(parameter);
		} else if (parameter instanceof String && clazz.equals(Integer.class)) {
			return Integer.parseInt((String) parameter);
		}
		throw new StatementNotInitalizableException("Cannot convert " + parameter + "(" + parameter.getClass() + ") to " + clazz);
	}

}

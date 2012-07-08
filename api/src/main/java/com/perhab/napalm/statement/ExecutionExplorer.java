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
		Integer[] integers = new Integer[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			integers[i] = (Integer) convertTo(Integer.class, parameters[i].value());
		}
		return integers;
	}

	private static Object convertTo(Class<?> clazz, Object parameter) {
		if (parameter instanceof String && clazz.equals(Integer.class)) {
			return Integer.parseInt((String) parameter);
		}
		throw new StatementNotInitalizableException("Cannot convert " + parameter + "(" + parameter.getClass() + ") to " + clazz);
	}

}

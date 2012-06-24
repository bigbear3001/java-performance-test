package com.perhab.napalm.statement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Getter;

import com.perhab.napalm.Result;
import com.perhab.napalm.string.concat.StringConcatination;

public class Statement {

	private Object implementationObject;
	
	private Method method;
	
	@Getter
	private Object[] arguments;
	
	public Statement(Class<?> implementation) {
		try {
			implementationObject = implementation.getConstructor(new Class[0]).newInstance(new Object[]{});
		} catch (SecurityException e) {
			throw new StatementNotInitalizableException("Cannot find or access no args constructor of " + implementation, e);
		} catch (NoSuchMethodException e) {
			throw new StatementNotInitalizableException("Cannot find or access no args constructor of " + implementation, e);
		} catch (IllegalArgumentException e) {
			throw new StatementNotInitalizableException("Cannot instanciate object with no args constructor (" + implementation + ")", e);
		} catch (InstantiationException e) {
			throw new StatementNotInitalizableException("Cannot instanciate object with no args constructor (" + implementation + ")", e);
		} catch (IllegalAccessException e) {
			throw new StatementNotInitalizableException("Cannot instanciate object with no args constructor (" + implementation + ")", e);
		} catch (InvocationTargetException e) {
			throw new StatementNotInitalizableException("Cannot instanciate object with no args constructor (" + implementation + ")", e);
		}
		method = ExecutionExplorer.findExecutionMethod(implementation);
		if (method == null) {
			throw new StatementNotInitalizableException("Cannot find a method annotaed with @Execution in this class " + implementation);
		}
		arguments = ExecutionExplorer.getArguments(method);
	}

	public Result execute() {
		Result result = new Result(this);
		try {
		result.setResult(method.invoke(implementationObject, arguments));
		for (int i = 0; i < 10000000; i++) {
			method.invoke(implementationObject, arguments);
		}
		return result.stop();
		} catch (InvocationTargetException e) {
			throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
		} catch (IllegalArgumentException e) {
			throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
		} catch (IllegalAccessException e) {
			throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
		}
	}

	@Override
	public final String toString() {
		return implementationObject.getClass().getName();
	}
}

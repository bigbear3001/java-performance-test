package com.perhab.napalm.statement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.perhab.napalm.Result;
import com.perhab.napalm.StatementGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BaseStatement implements Statement {

	private Object implementationObject;
	
	@Getter
	private Method method;
	
	@Getter(lazy = true)
	private final StatementGroup group = StatementGroup.getStatementGroup(this);

	@Getter(lazy = true)
	private final String sourceCode = ExecutionExplorer.getSourceCode(method, implementationObject);
	
	@Getter
	private Object[] arguments;
	
	private int[] iterations;

	private int threads;

	public static List<BaseStatement> getBaseStatements(Class<?> implementation) {
		ArrayList<BaseStatement> statements = new ArrayList<>();
		for (Method executionMethod : ExecutionExplorer.findExecutionMethods(implementation)) {
			try {
				statements.add(new BaseStatement(
						implementation.getConstructor(new Class[0]).newInstance(),
						executionMethod,
						ExecutionExplorer.getArguments(executionMethod),
						ExecutionExplorer.getIterations(executionMethod),
						ExecutionExplorer.getThreads(executionMethod)
				));
			} catch (SecurityException | NoSuchMethodException e) {
				throw new StatementNotInitalizableException("Cannot find or access no args constructor of " + implementation, e);
			} catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
				throw new StatementNotInitalizableException("Cannot instanciate object with no args constructor (" + implementation + ")", e);
			}

		}
		return statements;
	}

	/* (non-Javadoc)
	 * @see com.perhab.napalm.statement.StatementInterface#execute()
	 */
	public Result execute() {
		ExecutionThread[] executionThreads = new ExecutionThread[threads];
		for (int i = 0; i < threads; i++) {
			executionThreads[i] = new ExecutionThread();
		}
		for (int i = 0; i < threads; i++) {
			executionThreads[i].start();
		}
		for (int i = 0; i < threads; i++) {
			try {
				executionThreads[i].join();
			} catch (InterruptedException e) {
				log.error("Got interrupted", e);
			}
		}
		return executionThreads[0].result;
	}

	@Override
	public final String toString() {
		return implementationObject.getClass().getName();
	}

	public int getExpectedTimes() {
		return iterations.length + 1;
	}

	private class ExecutionThread extends Thread {

		Result result;

		@Override
		public void run() {
			result = new Result(BaseStatement.this);
			try {
				result.setResult(method.invoke(implementationObject, arguments));
				int j = 1;
				for (int i = 0; i < iterations.length; i++) {
					for (; j < iterations[i]; j++) {
						method.invoke(implementationObject, arguments);
					}
					result.time();
				}
				result.stop();
			} catch (InvocationTargetException e) {
				throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
			} catch (IllegalArgumentException e) {
				throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
			} catch (IllegalAccessException e) {
				throw new InvocationError("Cannot invode method " + method + " on implementation " + implementationObject + " with args " + arguments, e);
			}
		}
	}
}

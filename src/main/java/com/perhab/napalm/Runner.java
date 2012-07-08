package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;

import com.perhab.napalm.implementations.arrays.iterate.ForWithI;
import com.perhab.napalm.implementations.arrays.iterate.ShortFor;
import com.perhab.napalm.implementations.generics.methods.InterfaceMethodCall;
import com.perhab.napalm.implementations.generics.methods.LazyReflectionMethodCall;
import com.perhab.napalm.implementations.generics.methods.NormalMethodCall;
import com.perhab.napalm.implementations.generics.methods.ReflectionMethodCall;
import com.perhab.napalm.implementations.generics.methods.ReflectionMethodCallWithExceptions;
import com.perhab.napalm.implementations.string.concat.StringBufferImplementation;
import com.perhab.napalm.implementations.string.concat.StringBufferImplementation2;
import com.perhab.napalm.implementations.string.concat.StringBuilderImplementation;
import com.perhab.napalm.implementations.string.concat.StringBuilderImplementation2;
import com.perhab.napalm.implementations.string.concat.StringImplementation;
import com.perhab.napalm.implementations.string.concat.StringImplementation2;
import com.perhab.napalm.implementations.string.replace.CharReplace;
import com.perhab.napalm.implementations.string.replace.RegexReplace;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.BaseStatement;
import com.perhab.napalm.statement.Statement;
import com.perhab.napalm.validation.ResultEqualsValidator;
import com.perhab.napalm.validation.Validator;
import com.perhab.napalm.validation.Validators;

public class Runner {

	/**
	 * Validators use to verify the results of the tes methods.
	 */
	private Validators validators = new Validators();
	
	/**
	 * initialize a new runner and add the ResultEqualsValidator as default validator.
	 */
	public Runner() {
		addValidator(new ResultEqualsValidator());
	}
	
	/**
	 * Add a new validator for verification of the results.
	 * @param validator - validator to add
	 */
	public final void addValidator(final Validator validator) {
		validators.add(validator);
	}

	/**
	 * Main method for running the class as a java program.
	 * @param args - arguments passed in the command line
	 */
	public static void main(final String[] args) {
		Runner runner = new Runner();
		Collection<Result> results = runner.run(discover());
		new SystemOutPrinter().print(results);
		
	}

	/**
	 * @return all classes discovered that have a method that is annotated with {@link Execute}.
	 */
	private static Class<?>[] discover() {
		return new Class<?>[]{
				StringBufferImplementation.class,
				StringBuilderImplementation.class,
				StringImplementation.class,
				StringBufferImplementation2.class,
				StringImplementation2.class,
				StringBuilderImplementation2.class,
				ForWithI.class,
				ShortFor.class,
				NormalMethodCall.class,
				ReflectionMethodCall.class,
				ReflectionMethodCallWithExceptions.class,
				LazyReflectionMethodCall.class,
				InterfaceMethodCall.class,
				CharReplace.class,
				RegexReplace.class
			};
	}
	
	/**
	 * Run the performance tests with given implementations.
	 * @param implementations - classes that contain a method annotated with {@link Execute}
	 * @return collection with the performance test results of the given implementations
	 */
	public final Collection<Result> run(final Class<?>[] implementations) {
		Collection<StatementGroup> groups = prepare(implementations);
		Collection<Result> results = new ArrayList<Result>();
		for (StatementGroup group : groups) {
			results.addAll(group.execute(validators));
		}
		return results;
	}

	/**
	 * Prepare the given implementations for running as a performance test.
	 * @param implementations - array with classes that have a method annotated with {@link Execution}
	 * @return prepared implementations (Statements)
	 */
	private Collection<StatementGroup> prepare(final Class<?>[] implementations) {
		ArrayList<StatementGroup> statementGroups = new ArrayList<StatementGroup>(implementations.length);
		for (Class<?> implementation : implementations) {
			BaseStatement statement = new BaseStatement(implementation);
			if (!statementGroups.contains(statement.getGroup())) {
				statementGroups.add(statement.getGroup());
			}
		}
		return statementGroups;
	}
}

package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.perhab.napalm.arrays.iterate.ForWithI;
import com.perhab.napalm.arrays.iterate.ShortFor;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Statement;
import com.perhab.napalm.string.concat.StringBufferImplementation;
import com.perhab.napalm.string.concat.StringBufferImplementation2;
import com.perhab.napalm.string.concat.StringBuilderImplementation;
import com.perhab.napalm.string.concat.StringBuilderImplementation2;
import com.perhab.napalm.string.concat.StringConcatination;
import com.perhab.napalm.string.concat.StringImplementation;
import com.perhab.napalm.string.concat.StringImplementation2;
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
				ShortFor.class
				};
	}
	
	/**
	 * Run the performance tests with given implementations.
	 * @param implementations - classes that contain a method annotated with {@link Execute}
	 * @return collection with the performance test results of the given implementations
	 */
	public final Collection<Result> run(final Class<?>[] implementations) {
		Collection<Statement> statements = prepare(implementations);
		Collection<Result> results = new ArrayList<Result>();
		for (Statement statement : statements) {
			results.add(statement.execute());
		}
		validators.validate(results);
		return results;
	}

	/**
	 * Prepare the given implementations for running as a performance test.
	 * @param implementations - array with classes that have a method annotated with {@link Execution}
	 * @return prepared implementations (Statements)
	 */
	private Collection<Statement> prepare(final Class<?>[] implementations) {
		ArrayList<Statement> statments = new ArrayList<Statement>(implementations.length);
		for (Class<?> implementation : implementations) {
			statments.add(new Statement(implementation));
		}
		return statments;
	}
}

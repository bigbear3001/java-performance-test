package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

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
		Collection<Result> results = runner.run(new StringConcatination[]{
				new StringBufferImplementation(),
				new StringBuilderImplementation(),
				new StringImplementation(),
				new StringBufferImplementation2(),
				new StringImplementation2(),
				new StringBuilderImplementation2()
				});
		new SystemOutPrinter().print(results);
		
	}
	
	public Collection<Result> run(StringConcatination[] implementations) {
		Collection<Statement> statements = prepare(implementations);
		Collection<Result> results = new ArrayList<Result>();
		for (Statement statement : statements) {
			results.add(statement.execute());
		}
		validators.validate(results);
		return results;
	}

	private Collection<Statement> prepare(StringConcatination[] implementations) {
		ArrayList<Statement> statments = new ArrayList<Statement>(implementations.length);
		for (StringConcatination implementation : implementations) {
			statments.add(new Statement(implementation));
		}
		return statments;
	}
}

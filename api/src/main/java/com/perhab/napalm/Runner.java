package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.perhab.napalm.discover.Discover;
import com.perhab.napalm.statement.BaseStatement;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.ExecuteParallel;
import com.perhab.napalm.validation.ResultEqualsValidator;
import com.perhab.napalm.validation.Validator;
import com.perhab.napalm.validation.Validators;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;
import org.kohsuke.args4j.spi.RestOfArgumentsHandler;
import org.kohsuke.args4j.spi.StringOptionHandler;

@Slf4j
public class Runner {

	@Option(name = "-f", aliases = {"--html-file"}, usage = "name of the html file to generate", handler = StringOptionHandler.class)
	String filename;

	@Option(name = "-m", aliases = {"--markdown-file"}, usage = "name of the markdown file to generate", handler = StringOptionHandler.class)
	String markdownFilename;

	@Option(name = "-s", aliases = {"--silent"}, usage = "make runner silent (do not print out results)", handler = BooleanOptionHandler.class)
	Boolean silent;

	@Option(name ="", handler = RestOfArgumentsHandler.class)
	String pendingArguments;

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
		try {
			new CmdLineParser(runner).parseArgument(args);
			if (runner.pendingArguments != null) {
				log.error("unkown argument: {}", runner.pendingArguments);
				return;
			}
			Collection<Result> results = runner.run(discover());
			if (runner.silent == null || Boolean.FALSE.equals(runner.silent)) {
				new SystemOutPrinter().print(results);
			}
			if (runner.filename != null) {
				new HtmlFileOutPrinter(runner.filename).print(results);
			}
			if (runner.markdownFilename != null) {
				new MarkdownFileOutPrinter(runner.markdownFilename).print(results);
			}
		} catch (CmdLineException e) {
			log.error("Cannot run program:", e);
		}

	}

	/**
	 * @return all classes discovered that have a method that is annotated with {@link Execute}.
	 */
	private static Class<?>[] discover() {
		return Discover.findClassesWithMethodsAnnotatedWith(Execute.class, ExecuteParallel.class);
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
	 * @param implementations - array with classes that have a method annotated with {@link Execute}
	 * @return prepared implementations (Statements)
	 */
	private Collection<StatementGroup> prepare(final Class<?>[] implementations) {
		ArrayList<StatementGroup> statementGroups = new ArrayList<StatementGroup>(implementations.length);
		for (Class<?> implementation : implementations) {
			for (BaseStatement statement : BaseStatement.getBaseStatements(implementation)) {
				if (!statementGroups.contains(statement.getGroup())) {
					statementGroups.add(statement.getGroup());
				}
			}
		}
		return statementGroups;
	}
}

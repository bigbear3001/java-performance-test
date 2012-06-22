package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.perhab.napalm.string.concat.StringBufferImplementation;
import com.perhab.napalm.string.concat.StringBuilderImplementation;
import com.perhab.napalm.string.concat.StringConcatination;
import com.perhab.napalm.string.concat.StringImplementation;

public class Runner {

	public static void main(String[] args) {
		Runner runner = new Runner();
		Collection<Result> results = runner.run(new StringConcatination[]{
				new StringBufferImplementation(),
				new StringImplementation(),
				new StringBuilderImplementation()
				});
		new SystemOutPrinter().print(results);
		
	}
	
	public Collection<Result> run(StringConcatination[] implementations) {
		Collection<Statement> statements = prepare(implementations);
		Collection<Result> results = new ArrayList<Result>();
		for(Statement statement : statements) {
			results.add(statement.execute());
		}
		validate(results);
		return results;
	}

	private void validate(Collection<Result> results) {
		Iterator<Result> iterator = results.iterator();
		Result first = iterator.next();
		while(iterator.hasNext()) {
			Result second = iterator.next();
			if(!first.equals(second)) {
				throw new ResultsNotEqualException(first, second);
			}
		}
	}

	private Collection<Statement> prepare(StringConcatination[] implementations) {
		ArrayList<Statement> statments = new ArrayList<Statement>(implementations.length);
		for (StringConcatination implementation : implementations) {
			statments.add(new Statement(implementation));
		}
		return statments;
	}
}

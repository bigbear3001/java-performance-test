package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.perhab.napalm.statement.Description;
import lombok.Getter;

import com.perhab.napalm.statement.Statement;
import com.perhab.napalm.validation.Validators;

public class StatementGroup extends ArrayList<Statement> {
	
	/**
	 * unique serialization version id
	 */
	private static final long serialVersionUID = 2L;

	@Getter
	private Class<?> implementedInterface;

	@Getter
	private String description;

	private static HashMap<Class<?>, StatementGroup> groups = new HashMap<Class<?>, StatementGroup>();
	
	private StatementGroup(Class<?> clazz) {
		this(clazz, null);
	}


	private StatementGroup(Class<?> clazz, String givenDescription) {
		implementedInterface = clazz;
		description = givenDescription;
	}


	public static StatementGroup getStatementGroup(Statement statement) {
		Class<?> declaringClass = statement.getMethod().getDeclaringClass();
		if (!groups.containsKey(declaringClass)) {
			Description description = statement.getMethod().getAnnotation(Description.class);
			StatementGroup statementGroup;
			if (description != null) {
				statementGroup = new StatementGroup(declaringClass, description.value());
			} else {
				statementGroup = new StatementGroup(declaringClass);
			}
			groups.put(declaringClass, statementGroup);
		}
		StatementGroup group = groups.get(declaringClass);
		if (!group.contains(statement)) {
			group.add(statement);
		}
		return groups.get(declaringClass);
	}

	public Collection<Result> execute(Validators validators) {
		ArrayList<Result> result = new ArrayList<Result>();
		for (Statement statement : this) {
			result.add(statement.execute());
		}
		validators.validate(result);
		return result;
	}
}

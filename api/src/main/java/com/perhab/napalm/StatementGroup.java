package com.perhab.napalm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import lombok.Getter;

import com.perhab.napalm.statement.Statement;
import com.perhab.napalm.validation.Validators;

public class StatementGroup extends ArrayList<Statement> {
	
	/**
	 * generated unique serialization id
	 */
	private static final long serialVersionUID = 6050434184506420855L;

	@Getter
	private Class<?> implementedInterface;

	private static HashMap<Class<?>, StatementGroup> groups = new HashMap<Class<?>, StatementGroup>();
	
	private StatementGroup(Class<?> clazz) {
		implementedInterface = clazz;
	}
	
	public static StatementGroup getStatementGroup(Statement statement) {
		Class<?> declaringClass = statement.getMethod().getDeclaringClass();
		if (!groups.containsKey(declaringClass)) {
			groups.put(declaringClass, new StatementGroup(declaringClass));
		}
		StatementGroup group = groups.get(declaringClass);
		if (!group.contains(statement)) {
			group.add(statement);
		}
		return groups.get(declaringClass);
	}

	public Collection<Result> execute(Validators validators) {
		ArrayList<Result> result = new ArrayList<Result>();
		for(Statement statement : this) {
			result.add(statement.execute());
		}
		validators.validate(result);
		return result;
	}
}

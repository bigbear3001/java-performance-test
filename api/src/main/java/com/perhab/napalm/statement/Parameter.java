package com.perhab.napalm.statement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {
	String EMPTY_STRING = "";

	/**
	 * Magic value to generate a random string.
	 */
	String ANY_STRING = "ANY_STRING";

	/**
	 * Array definition to be parsed into an array.
	 * * 1...100 generates integers from 1 to 100 into an array.
	 */
	String arrayDefinition() default "";

	/**
	 * Value that should be given to method invocation.
	 */
	String value() default EMPTY_STRING;
}

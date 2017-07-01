package com.perhab.napalm.statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {
	String EMPTY_STRING = "";
	
	String ANY_STRING = "ANY_STRING";

	String value() default EMPTY_STRING;
}

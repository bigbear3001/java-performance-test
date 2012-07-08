package com.perhab.napalm.statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the method to be executed for performance test.
 * @author bigbear3001
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Execute {
	
	int HUNDRED = 100;
	int TEN_THOUSAND = 10000;
	int MILLION = 1000000;
	
	Parameter[] parameters();
	int[] iterations() default {1, HUNDRED, TEN_THOUSAND, MILLION};
}

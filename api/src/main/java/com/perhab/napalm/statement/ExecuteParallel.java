package com.perhab.napalm.statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the method to be executed for performance test in parallel.
 * @author bigbear3001
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecuteParallel {

	Parameter[] parameters();
	int[] iterations() default {1, Execute.HUNDRED, Execute.TEN_THOUSAND, Execute.MILLION};
	int threads() default 2;
}

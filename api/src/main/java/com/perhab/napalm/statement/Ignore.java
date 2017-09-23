package com.perhab.napalm.statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An implementation class of an interface annotated with {@link Execute} or {@link ExecuteParallel} can be marked as
 * ignored with this annotation. An ignored implementation will not be picked up by the {@link com.perhab.napalm.Runner}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
}

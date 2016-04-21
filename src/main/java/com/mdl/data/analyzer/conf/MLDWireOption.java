package com.mdl.data.analyzer.conf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to place on a setter method to wire a command line option into
 * @author angelo
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MLDWireOption {
	/** menu option settings */
	String value();
	String shortValue();
	String description();
	boolean mandatory() default true;
}
package com.mdl.data.analyzer.conf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation wire the statistics algorithm with the relted command digited via command line.
 * @author angelo
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MLDWireAlg {
	/** menu option settings */
	String value(); // this is the command / algorithm
	String description();
}
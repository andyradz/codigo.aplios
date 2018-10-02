package com.codigo.aplios.sdk.core.attribute;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category attribute
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Author {

	/**
	 * Właściwość reprezentuje imię i nazwisko autora zmiany
	 *
	 * @return Wartość tekstowa
	 */
	String name() default "Andrzej Radziszewski";

	/**
	 * Właściwość repreznetuje login autora
	 *
	 * @return Wartość tekstowa
	 */
	String login() default "andrzej.radziszewski";

	/**
	 * Właściwość repreznetuje email autora.
	 *
	 * @return Wartość tekstowa
	 */
	String contact() default "ar.radziszewski@gmail.com";

	/**
	 * Właściwość repreznetuje zawód autora
	 *
	 * @return Wartość tekstowa
	 */
	String profession() default "developer";

	/**
	 * Właściwość reprezentuje nazwę firmy autora
	 *
	 * @return Wartość tekstowa
	 */
	String company() default "codigo digital technology";

}

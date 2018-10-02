package com.codigo.aplios.validation.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author andrzej.radziszewski
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PeselValidator.class)
public @interface PeselCheck {

	String message() default "Invalid field value literal!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

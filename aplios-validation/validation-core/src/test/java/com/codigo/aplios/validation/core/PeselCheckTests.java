package com.codigo.aplios.validation.core;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//https://www.javaworld.com/article/2137346/data-storage/java-tip-hibernate-validation-in-a-standalone-implementation.html

/**
 * Unit test for simple App.
 */
public class PeselCheckTests {
	private static Validator validator;

	// -----------------------------------------------------------------------------------------------------------------

	@BeforeClass
	public static void setUp() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		PeselCheckTests.validator = factory.getValidator();
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void manufacturerIsNull() {
		final Pesel pesel = new Pesel(
			"02345678903");
		final Set<ConstraintViolation<Pesel>> constraintViolations = PeselCheckTests.validator.validate(pesel);

		Assert.assertThat(0, CoreMatchers.is(constraintViolations.size()));
	}

	// -----------------------------------------------------------------------------------------------------------------
}

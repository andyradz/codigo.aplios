/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.validation.core;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author andrzej.radziszewski
 */
public class PeselValidator implements ConstraintValidator<PeselCheck, Pesel> {

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void initialize(final PeselCheck constraintAnnotation) {

	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean isValid(final Pesel value, final ConstraintValidatorContext context) {
		return true;
	}

	// -----------------------------------------------------------------------------------------------------------------

}

package com.codigo.aplios.validation.core;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author andrzej.radziszewski
 */
@PeselCheck
public class Pesel {

	// -----------------------------------------------------------------------------------------------------------------

	public Pesel(final String pesel) {
		this.pesel = pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getPesel() {
		return this.pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@NotNull
	@Length(max = 11, min = 11, message = "długość")
	@Pattern(regexp = "\\d+")
	private final String pesel;

	// -----------------------------------------------------------------------------------------------------------------
}

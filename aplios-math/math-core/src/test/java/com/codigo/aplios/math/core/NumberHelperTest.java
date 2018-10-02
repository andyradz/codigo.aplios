package com.codigo.aplios.math.core;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.codigo.aplios.math.core.NumberHelper.DecimalPrecision;

public class NumberHelperTest {

	@Rule
	public final JUnitStopWatch stopWatch = new JUnitStopWatch();

	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void shouldReturnTrue() {

		final double myFraction = NumberHelper.fraction(1.7976931348623157, DecimalPrecision.PRECTO6);

		Assert.assertThat(Double.valueOf(0.797693)
				.doubleValue(), CoreMatchers.equalTo(myFraction));
	}

	// -----------------------------------------------------------------------------------------------------------------
}

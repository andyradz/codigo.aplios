package com.codigo.aplios.group.sdk.core;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.group.sdk.core.helper.StringOperator;

/**
 * Klasa wykonuje testy operatora dla klasy <code>String</code>
 *
 * @author andrzej.radziszewski
 *
 */
public class StringOperatorTest {

	@Test
	@DisplayName("Metoda wykonuje test wartość null - oczekuje TRUE")
	public void testNullString_sholudRetrunTrue() {

		// arrange
		final String chain = null;

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(StringOperator.isNullOrEmpty(chain)));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość not null - oczekuje FALSE")
	public void testNotNullString_RetrunFalse() {

		// arrange
		final String chain = "01234  56789";

		// assert
		MatcherAssert.assertThat(false, CoreMatchers.is(StringOperator.isNullOrEmpty(chain)));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość empty - oczekuje TRUE")
	public void testEmptyString_sholudRetrunFalse() {

		// arrange
		final String chain = "";

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(StringOperator.isNullOrEmpty(chain)));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość blanks - oczekuje TRUE")
	public void testBlankString_sholudRetrunFalse() {

		// arrange
		final String chain = "     ";

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(StringOperator.isNullOrEmpty(chain)));
	}

}

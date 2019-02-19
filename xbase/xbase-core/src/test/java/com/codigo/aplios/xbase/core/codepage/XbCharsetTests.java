package com.codigo.aplios.xbase.core.codepage;

import java.nio.charset.Charset;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@DisplayName("Test1")
//@RunWith(JUnitPL)
public class XbCharsetTests {

	private static String MAZOVIA_CHARSET = "MAZOVIA";

	private static String ASCII_CHARSET = "US-ASCII";

	private static int MAZOVIA_CODEPAGE = 0x69;

	private static int UNKNOWN_CODEPAGE = 500;

	@Disabled
	@Test
	@DisplayName("Test istnienia mechanizmu kodowania znaków MAZOVIA na podstawie kodu strony kodowej")
	public void sholudReturnMazoviaCharsetOfCodepage() {

		final Charset mazovia = XbCharset.ofCodepage(XbCharsetTests.MAZOVIA_CODEPAGE);

		MatcherAssert.assertThat(XbCharsetTests.MAZOVIA_CHARSET, CoreMatchers.equalTo(mazovia.name()));
	}

	@Disabled
	@Test
	@DisplayName("Test istnienia mechanizmu kodowania znaków UNKNOWN gdy nie jest wskazane typ kodowania")
	public void sholudReturnDefaultCharsetOfCodepage() {

		final Charset ascii = XbCharset.ofCodepage(XbCharsetTests.UNKNOWN_CODEPAGE);

		MatcherAssert.assertThat(XbCharsetTests.ASCII_CHARSET, CoreMatchers.equalTo(ascii.name()));
	}

	@Disabled
	@Test
	@DisplayName("Test istnienia mechanizmu kodowania znaków MAZOVIA na podstawie nazwy strony kodowej")
	public void sholudReturnMazoviaCodepageOfCharset() {

		final int mazovia = XbCharset.ofCharset(Charset.forName(XbCharsetTests.MAZOVIA_CHARSET));

		MatcherAssert.assertThat(XbCharsetTests.MAZOVIA_CODEPAGE, CoreMatchers.is(mazovia));
	}

}

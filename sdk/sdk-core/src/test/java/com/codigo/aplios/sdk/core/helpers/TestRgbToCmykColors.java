package com.codigo.aplios.sdk.core.helpers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w RBG na CMYK")
public class TestRgbToCmykColors {

	// https://www.rapidtables.com/convert/color/rgb-to-cmyk.html
	// https://www.w3schools.com/colors/colors_picker.asp?colorhex=00ffff

	@Test
	@DisplayName("Test konwersji wartości barwy białej RGB na CMYK")
	public void shouldBeCmykWhiteColor() {

		final var rgbWhite = 0xFF_FF_FF;
		final var cmykWhite = RgbToCmykColorConverter.of(rgbWhite);
		System.out.println("while " + cmykWhite);

		MatcherAssert.assertThat(cmykWhite.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getYellowColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykWhite.getCyanValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getMagnetaValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getYellowValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getBlackValue(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej RGB na CMYK")
	public void shouldBeCmykRedColor() {

		final var rgbRed = 0xFF_00_00;
		final var cmykRed = RgbToCmykColorConverter.of(rgbRed);
		System.out.println("red " + cmykRed);

		MatcherAssert.assertThat(cmykRed.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykRed.getMagnetaColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykRed.getYellowColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykRed.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykRed.getCyanValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykRed.getMagnetaValue(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykRed.getYellowValue(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykRed.getBlackValue(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej RGB na CMYK")
	public void shouldBeCmykLimeColor() {

		final var rgbLime = 0x00_FF_00;
		final var cmykLime = RgbToCmykColorConverter.of(rgbLime);
		System.out.println("lime " + cmykLime);

		MatcherAssert.assertThat(cmykLime.getCyanColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykLime.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykLime.getYellowColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykLime.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykLime.getCyanValue(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykLime.getMagnetaValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykLime.getYellowValue(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykLime.getBlackValue(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej RGB na CMYK")
	public void shouldBeCmykBlackColor() {

		final var rgbBlack = 0x00_00_00;
		final var cmykBlack = RgbToCmykColorConverter.of(rgbBlack);
		System.out.println("black " + cmykBlack);

		MatcherAssert.assertThat(cmykBlack.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getYellowColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykBlack.getCyanValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getMagnetaValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getYellowValue(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getBlackValue(), CoreMatchers.is(1.0));
	}

}

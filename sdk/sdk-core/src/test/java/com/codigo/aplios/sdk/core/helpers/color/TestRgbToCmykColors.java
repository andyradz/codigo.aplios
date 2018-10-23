package com.codigo.aplios.sdk.core.helpers.color;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.helpers.color.ColorConverter;

@DisplayName("Testy konwersji wartości barwy zapisanej w RBG na CMYK")
public class TestRgbToCmykColors {

	// https://www.rapidtables.com/convert/color/rgb-to-cmyk.html
	// https://www.w3schools.com/colors/colors_picker.asp?colorhex=00ffff

	@Test
	@DisplayName("Test konwersji wartości barwy białej RGB na CMYK")
	public void shouldBeCmykWhiteColor() {

		final var rgbWhite = 0xFF_FF_FF;
		final var cmykWhite = ColorConverter.ofRgbToCmyk(rgbWhite);
		System.out.println("while " + cmykWhite);

		MatcherAssert.assertThat(cmykWhite.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getYellowColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykWhite.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykWhite.getCyanFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getMagnetaFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getYellowFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykWhite.getBlackFactor(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej RGB na CMYK")
	public void shouldBeCmykRedColor() {

		final var rgbRed = 0xFF_00_00;
		final var cmykRed = ColorConverter.ofRgbToCmyk(rgbRed);
		System.out.println("red " + cmykRed);

		MatcherAssert.assertThat(cmykRed.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykRed.getMagnetaColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykRed.getYellowColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykRed.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykRed.getCyanFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykRed.getMagnetaFactor(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykRed.getYellowFactor(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykRed.getBlackFactor(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej RGB na CMYK")
	public void shouldBeCmykLimeColor() {

		final var rgbLime = 0x00_FF_00;
		final var cmykLime = ColorConverter.ofRgbToCmyk(rgbLime);
		System.out.println("lime " + cmykLime);

		MatcherAssert.assertThat(cmykLime.getCyanColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykLime.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykLime.getYellowColor(), CoreMatchers.is(100));
		MatcherAssert.assertThat(cmykLime.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykLime.getCyanFactor(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykLime.getMagnetaFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykLime.getYellowFactor(), CoreMatchers.is(1.0));
		MatcherAssert.assertThat(cmykLime.getBlackFactor(), CoreMatchers.is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej RGB na CMYK")
	public void shouldBeCmykBlackColor() {

		final var rgbBlack = 0x00_00_00;
		final var cmykBlack = ColorConverter.ofRgbToCmyk(rgbBlack);
		System.out.println("black " + cmykBlack);

		MatcherAssert.assertThat(cmykBlack.getCyanColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getMagnetaColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getYellowColor(), CoreMatchers.is(0));
		MatcherAssert.assertThat(cmykBlack.getBlackColor(), CoreMatchers.is(0));

		MatcherAssert.assertThat(cmykBlack.getCyanFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getMagnetaFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getYellowFactor(), CoreMatchers.is(0.0));
		MatcherAssert.assertThat(cmykBlack.getBlackFactor(), CoreMatchers.is(1.0));
	}

}

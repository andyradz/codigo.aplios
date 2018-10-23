package com.codigo.aplios.sdk.core.helpers.color;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.helpers.color.ColorConverter;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSV")
public final class TestRgbToHsvColor {

	@Test
	@DisplayName("Test konwersji wartości barwy white HEX na RGB")
	public void shouldBeHsvWhiteColor() {

		final var red = 255;
		final var green = 255;
		final var blue = 255;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeHsvBlackColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 0;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeHsvRedColor() {

		final var red = 255;
		final var green = 0;
		final var blue = 0;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(100.));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeHsvGreenColor() {

		final var red = 0;
		final var green = 255;
		final var blue = 0;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(120.));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(100.));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeHsvBlueColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 255;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(240.));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(100.));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy różowej HEX na RGB")
	public void shouldBeHsvPinkColor() {

		final var red = 255;
		final var green = 192;
		final var blue = 203;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(350.));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(24.7));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomarańczowej HEX na RGB")
	public void shouldBeHsvCoralColor() {

		final var red = 255;
		final var green = 127;
		final var blue = 80;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(16.));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(68.6));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwej HEX na RGB")
	public void shouldBeHsvGrayColor() {

		final var red = 10;
		final var green = 128;
		final var blue = 155;

		final var rgbToHsv = ColorConverter.ofRgbToHsv(red, green, blue);

		MatcherAssert.assertThat(rgbToHsv.getHue(), CoreMatchers.is(191.0));
		MatcherAssert.assertThat(rgbToHsv.getSaturation(), CoreMatchers.is(93.5));
		MatcherAssert.assertThat(rgbToHsv.getValue(), CoreMatchers.is(60.8));
	}
}

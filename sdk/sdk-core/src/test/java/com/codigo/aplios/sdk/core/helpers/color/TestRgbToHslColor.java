package com.codigo.aplios.sdk.core.helpers.color;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.helpers.color.ColorConverter;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSL")
public class TestRgbToHslColor {

	@Test
	@DisplayName("Test konwersji wartości barwy białej HEX na RGB")
	public void shouldBeHslWhiteColor() {

		final var red = 255;
		final var green = 255;
		final var blue = 255;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		MatcherAssert.assertThat(converter.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(converter.getSaturation(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(converter.getLightness(), CoreMatchers.is(100.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeHslBlackColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		MatcherAssert.assertThat(converter.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(converter.getSaturation(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(converter.getLightness(), CoreMatchers.is(.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeHslGreenColor() {

		final var red = 0;
		final var green = 255;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		MatcherAssert.assertThat(converter.getHue(), CoreMatchers.is(120.0));
		MatcherAssert.assertThat(converter.getSaturation(), CoreMatchers.is(100.0));
		MatcherAssert.assertThat(converter.getLightness(), CoreMatchers.is(50.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeHslBlueColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 255;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		MatcherAssert.assertThat(converter.getHue(), CoreMatchers.is(240.0));
		MatcherAssert.assertThat(converter.getSaturation(), CoreMatchers.is(100.0));
		MatcherAssert.assertThat(converter.getLightness(), CoreMatchers.is(50.0));

	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeHslRedColor() {

		final var red = 255;
		final var green = 0;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		MatcherAssert.assertThat(converter.getHue(), CoreMatchers.is(.0));
		MatcherAssert.assertThat(converter.getSaturation(), CoreMatchers.is(100.0));
		MatcherAssert.assertThat(converter.getLightness(), CoreMatchers.is(50.0));
	}

}

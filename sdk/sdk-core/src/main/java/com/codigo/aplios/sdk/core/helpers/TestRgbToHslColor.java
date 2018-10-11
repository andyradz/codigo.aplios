package com.codigo.aplios.sdk.color;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSL")
public class TestRgbToHslColor {

	@Test
	@DisplayName("Test konwersji wartości barwy białej HEX na RGB")
	public void shouldBeHslWhiteColor() {

		var red = 255;
		var green = 255;
		var blue = 255;

		var converter = RgbToHslColorConverter.of(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(.0));
		assertThat(converter.getLightness(), is(100.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeHslBlackColor() {

		var red = 0;
		var green = 0;
		var blue = 0;

		var converter = RgbToHslColorConverter.of(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(.0));
		assertThat(converter.getLightness(), is(.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeHslGreenColor() {

		var red = 0;
		var green = 255;
		var blue = 0;

		var converter = RgbToHslColorConverter.of(red, green, blue);

		assertThat(converter.getHue(), is(120.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeHslBlueColor() {

		var red = 0;
		var green = 0;
		var blue = 255;

		var converter = RgbToHslColorConverter.of(red, green, blue);

		assertThat(converter.getHue(), is(240.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));

	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeHslRedColor() {

		var red = 255;
		var green = 0;
		var blue = 0;

		var converter = RgbToHslColorConverter.of(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));
	}

}

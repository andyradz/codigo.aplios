package com.codigo.aplios.sdk.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSV")
public final class TestRgbToHsvColor {

	@Test
	@DisplayName("Test konwersji wartości barwy white HEX na RGB")
	public void shouldBeHsvWhiteColor() {

		var red = 255;
		var green = 255;
		var blue = 255;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(.0));
		assertThat(rgbToHsv.getSaturation(), is(.0));
		assertThat(rgbToHsv.getValue(), is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeHsvBlackColor() {

		var red = 0;
		var green = 0;
		var blue = 0;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(.0));
		assertThat(rgbToHsv.getSaturation(), is(.0));
		assertThat(rgbToHsv.getValue(), is(.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeHsvRedColor() {

		var red = 255;
		var green = 0;
		var blue = 0;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(.0));
		assertThat(rgbToHsv.getSaturation(), is(100.));
		assertThat(rgbToHsv.getValue(), is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeHsvGreenColor() {

		var red = 0;
		var green = 255;
		var blue = 0;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(120.));
		assertThat(rgbToHsv.getSaturation(), is(100.));
		assertThat(rgbToHsv.getValue(), is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeHsvBlueColor() {

		var red = 0;
		var green = 0;
		var blue = 255;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(240.));
		assertThat(rgbToHsv.getSaturation(), is(100.));
		assertThat(rgbToHsv.getValue(), is(100.));
	}
	
	@Test
	@DisplayName("Test konwersji wartości barwy różowej HEX na RGB")
	public void shouldBeHsvPinkColor() {
		
		var red = 255;
		var green = 192;
		var blue = 203;
		
		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);
		
		assertThat(rgbToHsv.getHue(), is(350.));
		assertThat(rgbToHsv.getSaturation(), is(24.7));
		assertThat(rgbToHsv.getValue(), is(100.));
	}
	
	@Test
	@DisplayName("Test konwersji wartości barwy pomarańczowej HEX na RGB")
	public void shouldBeHsvCoralColor() {
		
		var red = 255;
		var green = 127;
		var blue = 80;
		
		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);
		
		assertThat(rgbToHsv.getHue(), is(16.));
		assertThat(rgbToHsv.getSaturation(), is(68.6));
		assertThat(rgbToHsv.getValue(), is(100.));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwej HEX na RGB")
	public void shouldBeHsvGrayColor() {

		var red = 10;
		var green = 128;
		var blue = 155;

		var rgbToHsv = RgbToHsvColorConverter.of(red, green, blue);

		assertThat(rgbToHsv.getHue(), is(191.0));
		assertThat(rgbToHsv.getSaturation(), is(93.5));
		assertThat(rgbToHsv.getValue(), is(60.8));
	}
}

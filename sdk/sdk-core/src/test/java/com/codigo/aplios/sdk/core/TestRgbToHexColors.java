package com.codigo.aplios.sdk.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HEX")
public class TestRgbToHexColors {

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej RGB na HEX")
	public void shouldBeHEXBlackColor() {

		// white
		final int blackRed = 0;
		final int blackGreen = 0;
		final int blackBlue = 0;

		final String blackWhite = new RgbToHexColorConverter(blackRed, blackGreen, blackBlue).toString();
		System.out.println("white HEX " + blackWhite);

		assertThat(blackWhite, is("0"));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy białej RGB na HEX")
	public void shouldBeHEXWhiteColor() {

		// white
		final int whiteRed = 255;
		final int whiteGreen = 255;
		final int whiteBlue = 255;

		final String hexWhite = new RgbToHexColorConverter(whiteRed, whiteGreen, whiteBlue).toString();
		System.out.println("white HEX " + hexWhite);

		assertThat(hexWhite, is("FFFFFF"));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomidorowej RGB na HEX")
	public void shouldBeHEXTomatoColor() {

		// tomato
		final int tomatoRed = 255;
		final int tomatoGreen = 99;
		final int tomatoBlue = 71;

		final String hexTomato = new RgbToHexColorConverter(tomatoRed, tomatoGreen, tomatoBlue).toString();
		System.out.println("tomato HEX " + hexTomato);

		assertThat(hexTomato, is("FF6347"));
	}
}

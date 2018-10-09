package com.codigo.aplios.sdk.core.helpers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w HEX na RGB")
public class TestHexToRgbColors {

	@Test
	@DisplayName("Test konwersji wartości barwy białej HEX na RGB")
	public void shouldBeRGBWhiteColor() {

		final String hexWhite = "FFFFFF";
		final var rgbWhite = HexToRbgColorConverter.of(hexWhite);
		System.out.println("while " + rgbWhite);

		MatcherAssert.assertThat(rgbWhite.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbWhite.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbWhite.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeRGBRedColor() {

		final String hexRed = "FF0000";
		final var rgbRed = HexToRbgColorConverter.of(hexRed);
		System.out.println("red " + rgbRed);

		MatcherAssert.assertThat(rgbRed.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbRed.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbRed.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeRGBLimeColor() {

		final String hexLime = "00FF00";
		final var rgbLime = HexToRbgColorConverter.of(hexLime);
		System.out.println("lime " + rgbLime);

		MatcherAssert.assertThat(rgbLime.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbLime.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbLime.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeRGBBlueColor() {

		final String hexBlue = "0000FF";
		final var rgbBlue = HexToRbgColorConverter.of(hexBlue);
		System.out.println("blue " + rgbBlue);

		MatcherAssert.assertThat(rgbBlue.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbBlue.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbBlue.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy żółtej HEX na RGB")
	public void shouldBeRGBYellowColor() {

		final String hexYellow = "FFFF00";
		final var rgbYellow = HexToRbgColorConverter.of(hexYellow);
		System.out.println("yellow " + rgbYellow);

		MatcherAssert.assertThat(rgbYellow.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbYellow.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbYellow.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy seledynowy HEX na RGB")
	public void shouldBeRGBCyanColor() {

		final String hexCyan = "00FFFF";
		final var rgbCyen = HexToRbgColorConverter.of(hexCyan);
		System.out.println("cyen " + rgbCyen);

		MatcherAssert.assertThat(rgbCyen.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbCyen.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbCyen.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy różowy HEX na RGB")
	public void shouldBeRGBMagnetaColor() {

		final String hexMagneta = "FF00FF";
		final var rgbMagneta = HexToRbgColorConverter.of(hexMagneta);
		System.out.println("magneta " + rgbMagneta);

		MatcherAssert.assertThat(rgbMagneta.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbMagneta.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbMagneta.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy srebny HEX na RGB")
	public void shouldBeRGBSilverColor() {

		final String hexSilver = "C0C0C0";
		final var rgbSilver = HexToRbgColorConverter.of(hexSilver);
		System.out.println("silver " + rgbSilver);

		MatcherAssert.assertThat(rgbSilver.getRedValue(), CoreMatchers.is(192));
		MatcherAssert.assertThat(rgbSilver.getGreenValue(), CoreMatchers.is(192));
		MatcherAssert.assertThat(rgbSilver.getBlueValue(), CoreMatchers.is(192));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwy HEX na RGB")
	public void shouldBeRGBGrayColor() {

		final String hexGray = "808080";
		final var rgbGray = HexToRbgColorConverter.of(hexGray);
		System.out.println("gray " + rgbGray);

		MatcherAssert.assertThat(rgbGray.getRedValue(), CoreMatchers.is(128));
		MatcherAssert.assertThat(rgbGray.getGreenValue(), CoreMatchers.is(128));
		MatcherAssert.assertThat(rgbGray.getBlueValue(), CoreMatchers.is(128));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeRGBBlackColor() {

		final String hexBlack = "000000";
		final var rgbBlack = HexToRbgColorConverter.of(hexBlack);
		System.out.println("black " + rgbBlack);

		MatcherAssert.assertThat(rgbBlack.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbBlack.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbBlack.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy złotej HEX na RGB")
	public void shouldBeRGBGoldColor() {

		final String hexGold = "FFD700";
		final var rgbGold = HexToRbgColorConverter.of(hexGold);
		System.out.println("gold " + rgbGold);

		MatcherAssert.assertThat(rgbGold.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbGold.getGreenValue(), CoreMatchers.is(215));
		MatcherAssert.assertThat(rgbGold.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czekoladowej HEX na RGB")
	public void shouldBeRGBChocolateColor() {

		final String hexChocolate = "D2691E";
		final var rgbChocolate = HexToRbgColorConverter.of(hexChocolate);
		System.out.println("chocolate " + rgbChocolate);

		MatcherAssert.assertThat(rgbChocolate.getRedValue(), CoreMatchers.is(210));
		MatcherAssert.assertThat(rgbChocolate.getGreenValue(), CoreMatchers.is(105));
		MatcherAssert.assertThat(rgbChocolate.getBlueValue(), CoreMatchers.is(30));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomidorowej HEX na RGB")
	public void shouldBeRGBTomatoColor() {

		final String hexTomato = "FF6347";
		final var rgbTomato = HexToRbgColorConverter.of(hexTomato);
		System.out.println("tomato " + rgbTomato);

		MatcherAssert.assertThat(rgbTomato.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbTomato.getGreenValue(), CoreMatchers.is(99));
		MatcherAssert.assertThat(rgbTomato.getBlueValue(), CoreMatchers.is(71));
	}
}

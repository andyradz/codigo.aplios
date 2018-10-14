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
		final var rgbModel = rgbWhite.getRgbColorModel();
		System.out.println("while " + rgbWhite);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeRGBRedColor() {

		final String hexRed = "FF0000";
		final var rgbRed = HexToRbgColorConverter.of(hexRed);
		final var rgbModel = rgbRed.getRgbColorModel();
		System.out.println("red " + rgbRed);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeRGBLimeColor() {

		final String hexLime = "00FF00";
		final var rgbLime = HexToRbgColorConverter.of(hexLime);
		final var rgbModel = rgbLime.getRgbColorModel();
		System.out.println("lime " + rgbLime);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeRGBBlueColor() {

		final String hexBlue = "0000FF";
		final var rgbBlue = HexToRbgColorConverter.of(hexBlue);
		final var rgbModel = rgbBlue.getRgbColorModel();
		System.out.println("blue " + rgbBlue);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy żółtej HEX na RGB")
	public void shouldBeRGBYellowColor() {

		final String hexYellow = "FFFF00";
		final var rgbYellow = HexToRbgColorConverter.of(hexYellow);
		final var rgbModel = rgbYellow.getRgbColorModel();
		System.out.println("yellow " + rgbYellow);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy seledynowy HEX na RGB")
	public void shouldBeRGBCyanColor() {

		final String hexCyan = "00FFFF";
		final var rgbCyan = HexToRbgColorConverter.of(hexCyan);
		final var rgbModel = rgbCyan.getRgbColorModel();
		System.out.println("cyan " + rgbCyan);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy różowy HEX na RGB")
	public void shouldBeRGBMagnetaColor() {

		final String hexMagneta = "FF00FF";
		final var rgbMagneta = HexToRbgColorConverter.of(hexMagneta);
		final var rgbModel = rgbMagneta.getRgbColorModel();
		System.out.println("magneta " + rgbMagneta);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy srebny HEX na RGB")
	public void shouldBeRGBSilverColor() {

		final String hexSilver = "C0C0C0";
		final var rgbSilver = HexToRbgColorConverter.of(hexSilver);
		final var rgbModel = rgbSilver.getRgbColorModel();
		System.out.println("silver " + rgbSilver);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(192));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(192));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(192));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwy HEX na RGB")
	public void shouldBeRGBGrayColor() {

		final String hexGray = "808080";
		final var rgbGray = HexToRbgColorConverter.of(hexGray);
		final var rgbModel = rgbGray.getRgbColorModel();
		System.out.println("gray " + rgbGray);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(128));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(128));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(128));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeRGBBlackColor() {

		final String hexBlack = "000000";
		final var rgbBlack = HexToRbgColorConverter.of(hexBlack);
		final var rgbModel = rgbBlack.getRgbColorModel();
		System.out.println("black " + rgbBlack);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(0));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy złotej HEX na RGB")
	public void shouldBeRGBGoldColor() {

		final String hexGold = "FFD700";
		final var rgbGold = HexToRbgColorConverter.of(hexGold);
		final var rgbModel = rgbGold.getRgbColorModel();
		System.out.println("gold " + rgbGold);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(215));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czekoladowej HEX na RGB")
	public void shouldBeRGBChocolateColor() {

		final String hexChocolate = "D2691E";
		final var rgbChocolate = HexToRbgColorConverter.of(hexChocolate);
		final var rgbModel = rgbChocolate.getRgbColorModel();
		System.out.println("chocolate " + rgbChocolate);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(210));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(105));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(30));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomidorowej HEX na RGB")
	public void shouldBeRGBTomatoColor() {

		final String hexTomato = "FF6347";
		final var rgbTomato = HexToRbgColorConverter.of(hexTomato);
		final var rgbModel = rgbTomato.getRgbColorModel();
		System.out.println("tomato " + rgbTomato);

		MatcherAssert.assertThat(rgbModel.getRedValue(), CoreMatchers.is(255));
		MatcherAssert.assertThat(rgbModel.getGreenValue(), CoreMatchers.is(99));
		MatcherAssert.assertThat(rgbModel.getBlueValue(), CoreMatchers.is(71));
	}
}

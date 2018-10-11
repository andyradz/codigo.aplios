package com.codigo.aplios.sdk.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w HEX na RGB")
public class TestHexToRgbColors {

	@Test
	@DisplayName("Test konwersji wartości barwy białej HEX na RGB")
	public void shouldBeRGBWhiteColor() {

		final String hexWhite = "FFFFFF";
		var rgbWhite = HexToRbgColorConverter.of(hexWhite);
		var rgbModel = rgbWhite.getRgbColorModel();
		System.out.println("while " + rgbWhite);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(255));
		assertThat(rgbModel.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeRGBRedColor() {

		final String hexRed = "FF0000";
		var rgbRed = HexToRbgColorConverter.of(hexRed);
		var rgbModel = rgbRed.getRgbColorModel();
		System.out.println("red " + rgbRed);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(0));
		assertThat(rgbModel.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeRGBLimeColor() {

		final String hexLime = "00FF00";
		var rgbLime = HexToRbgColorConverter.of(hexLime);
		var rgbModel = rgbLime.getRgbColorModel();
		System.out.println("lime " + rgbLime);

		assertThat(rgbModel.getRedValue(), is(0));
		assertThat(rgbModel.getGreenValue(), is(255));
		assertThat(rgbModel.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeRGBBlueColor() {

		final String hexBlue = "0000FF";
		var rgbBlue = HexToRbgColorConverter.of(hexBlue);
		var rgbModel = rgbBlue.getRgbColorModel();
		System.out.println("blue " + rgbBlue);

		assertThat(rgbModel.getRedValue(), is(0));
		assertThat(rgbModel.getGreenValue(), is(0));
		assertThat(rgbModel.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy żółtej HEX na RGB")
	public void shouldBeRGBYellowColor() {

		final String hexYellow = "FFFF00";
		var rgbYellow = HexToRbgColorConverter.of(hexYellow);
		var rgbModel = rgbYellow.getRgbColorModel();
		System.out.println("yellow " + rgbYellow);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(255));
		assertThat(rgbModel.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy seledynowy HEX na RGB")
	public void shouldBeRGBCyanColor() {

		final String hexCyan = "00FFFF";
		var rgbCyan = HexToRbgColorConverter.of(hexCyan);
		var rgbModel = rgbCyan.getRgbColorModel();
		System.out.println("cyan " + rgbCyan);

		assertThat(rgbModel.getRedValue(), is(0));
		assertThat(rgbModel.getGreenValue(), is(255));
		assertThat(rgbModel.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy różowy HEX na RGB")
	public void shouldBeRGBMagnetaColor() {

		final String hexMagneta = "FF00FF";
		var rgbMagneta = HexToRbgColorConverter.of(hexMagneta);
		var rgbModel = rgbMagneta.getRgbColorModel();
		System.out.println("magneta " + rgbMagneta);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(0));
		assertThat(rgbModel.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy srebny HEX na RGB")
	public void shouldBeRGBSilverColor() {

		final String hexSilver = "C0C0C0";
		var rgbSilver = HexToRbgColorConverter.of(hexSilver);
		var rgbModel = rgbSilver.getRgbColorModel();
		System.out.println("silver " + rgbSilver);

		assertThat(rgbModel.getRedValue(), is(192));
		assertThat(rgbModel.getGreenValue(), is(192));
		assertThat(rgbModel.getBlueValue(), is(192));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwy HEX na RGB")
	public void shouldBeRGBGrayColor() {

		final String hexGray = "808080";
		var rgbGray = HexToRbgColorConverter.of(hexGray);
		var rgbModel = rgbGray.getRgbColorModel();
		System.out.println("gray " + rgbGray);

		assertThat(rgbModel.getRedValue(), is(128));
		assertThat(rgbModel.getGreenValue(), is(128));
		assertThat(rgbModel.getBlueValue(), is(128));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeRGBBlackColor() {

		final String hexBlack = "000000";
		var rgbBlack = HexToRbgColorConverter.of(hexBlack);
		var rgbModel = rgbBlack.getRgbColorModel();
		System.out.println("black " + rgbBlack);

		assertThat(rgbModel.getRedValue(), is(0));
		assertThat(rgbModel.getGreenValue(), is(0));
		assertThat(rgbModel.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy złotej HEX na RGB")
	public void shouldBeRGBGoldColor() {

		final String hexGold = "FFD700";
		var rgbGold = HexToRbgColorConverter.of(hexGold);
		var rgbModel = rgbGold.getRgbColorModel();
		System.out.println("gold " + rgbGold);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(215));
		assertThat(rgbModel.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czekoladowej HEX na RGB")
	public void shouldBeRGBChocolateColor() {

		final String hexChocolate = "D2691E";
		var rgbChocolate = HexToRbgColorConverter.of(hexChocolate);
		var rgbModel = rgbChocolate.getRgbColorModel();
		System.out.println("chocolate " + rgbChocolate);

		assertThat(rgbModel.getRedValue(), is(210));
		assertThat(rgbModel.getGreenValue(), is(105));
		assertThat(rgbModel.getBlueValue(), is(30));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomidorowej HEX na RGB")
	public void shouldBeRGBTomatoColor() {

		final String hexTomato = "FF6347";
		var rgbTomato = HexToRbgColorConverter.of(hexTomato);
		var rgbModel = rgbTomato.getRgbColorModel();
		System.out.println("tomato " + rgbTomato);

		assertThat(rgbModel.getRedValue(), is(255));
		assertThat(rgbModel.getGreenValue(), is(99));
		assertThat(rgbModel.getBlueValue(), is(71));
	}
}

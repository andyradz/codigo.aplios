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
		System.out.println("while " + rgbWhite);

		assertThat(rgbWhite.getRedValue(), is(255));
		assertThat(rgbWhite.getGreenValue(), is(255));
		assertThat(rgbWhite.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeRGBRedColor() {

		final String hexRed = "FF0000";
		var rgbRed = HexToRbgColorConverter.of(hexRed);
		System.out.println("red " + rgbRed);

		assertThat(rgbRed.getRedValue(), is(255));
		assertThat(rgbRed.getGreenValue(), is(0));
		assertThat(rgbRed.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeRGBLimeColor() {

		final String hexLime = "00FF00";
		var rgbLime = HexToRbgColorConverter.of(hexLime);
		System.out.println("lime " + rgbLime);

		assertThat(rgbLime.getRedValue(), is(0));
		assertThat(rgbLime.getGreenValue(), is(255));
		assertThat(rgbLime.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeRGBBlueColor() {

		final String hexBlue = "0000FF";
		var rgbBlue = HexToRbgColorConverter.of(hexBlue);
		System.out.println("blue " + rgbBlue);

		assertThat(rgbBlue.getRedValue(), is(0));
		assertThat(rgbBlue.getGreenValue(), is(0));
		assertThat(rgbBlue.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy żółtej HEX na RGB")
	public void shouldBeRGBYellowColor() {

		final String hexYellow = "FFFF00";
		var rgbYellow = HexToRbgColorConverter.of(hexYellow);
		System.out.println("yellow " + rgbYellow);

		assertThat(rgbYellow.getRedValue(), is(255));
		assertThat(rgbYellow.getGreenValue(), is(255));
		assertThat(rgbYellow.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy seledynowy HEX na RGB")
	public void shouldBeRGBCyanColor() {

		final String hexCyan = "00FFFF";
		var rgbCyen = HexToRbgColorConverter.of(hexCyan);
		System.out.println("cyen " + rgbCyen);

		assertThat(rgbCyen.getRedValue(), is(0));
		assertThat(rgbCyen.getGreenValue(), is(255));
		assertThat(rgbCyen.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy różowy HEX na RGB")
	public void shouldBeRGBMagnetaColor() {

		final String hexMagneta = "FF00FF";
		var rgbMagneta = HexToRbgColorConverter.of(hexMagneta);
		System.out.println("magneta " + rgbMagneta);

		assertThat(rgbMagneta.getRedValue(), is(255));
		assertThat(rgbMagneta.getGreenValue(), is(0));
		assertThat(rgbMagneta.getBlueValue(), is(255));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy srebny HEX na RGB")
	public void shouldBeRGBSilverColor() {

		final String hexSilver = "C0C0C0";
		var rgbSilver = HexToRbgColorConverter.of(hexSilver);
		System.out.println("silver " + rgbSilver);

		assertThat(rgbSilver.getRedValue(), is(192));
		assertThat(rgbSilver.getGreenValue(), is(192));
		assertThat(rgbSilver.getBlueValue(), is(192));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy siwy HEX na RGB")
	public void shouldBeRGBGrayColor() {

		final String hexGray = "808080";
		var rgbGray = HexToRbgColorConverter.of(hexGray);
		System.out.println("gray " + rgbGray);

		assertThat(rgbGray.getRedValue(), is(128));
		assertThat(rgbGray.getGreenValue(), is(128));
		assertThat(rgbGray.getBlueValue(), is(128));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeRGBBlackColor() {

		final String hexBlack = "000000";
		var rgbBlack = HexToRbgColorConverter.of(hexBlack);
		System.out.println("black " + rgbBlack);

		assertThat(rgbBlack.getRedValue(), is(0));
		assertThat(rgbBlack.getGreenValue(), is(0));
		assertThat(rgbBlack.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy złotej HEX na RGB")
	public void shouldBeRGBGoldColor() {

		final String hexGold = "FFD700";
		var rgbGold = HexToRbgColorConverter.of(hexGold);
		System.out.println("gold " + rgbGold);

		assertThat(rgbGold.getRedValue(), is(255));
		assertThat(rgbGold.getGreenValue(), is(215));
		assertThat(rgbGold.getBlueValue(), is(0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czekoladowej HEX na RGB")
	public void shouldBeRGBChocolateColor() {

		final String hexChocolate = "D2691E";
		var rgbChocolate = HexToRbgColorConverter.of(hexChocolate);
		System.out.println("chocolate " + rgbChocolate);

		assertThat(rgbChocolate.getRedValue(), is(210));
		assertThat(rgbChocolate.getGreenValue(), is(105));
		assertThat(rgbChocolate.getBlueValue(), is(30));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy pomidorowej HEX na RGB")
	public void shouldBeRGBTomatoColor() {

		final String hexTomato = "FF6347";
		var rgbTomato = HexToRbgColorConverter.of(hexTomato);
		System.out.println("tomato " + rgbTomato);

		assertThat(rgbTomato.getRedValue(), is(255));
		assertThat(rgbTomato.getGreenValue(), is(99));
		assertThat(rgbTomato.getBlueValue(), is(71));
	}
}

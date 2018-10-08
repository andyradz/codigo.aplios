package com.codigo.aplios.sdk.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w RBG na CMYK")
public class TestRgbToCmykColors {

	// https://www.rapidtables.com/convert/color/rgb-to-cmyk.html
	// https://www.w3schools.com/colors/colors_picker.asp?colorhex=00ffff

	@Test
	@DisplayName("Test konwersji wartości barwy białej RGB na CMYK")
	public void shouldBeCmykWhiteColor() {

		var rgbWhite = 0xFF_FF_FF;
		var cmykWhite = RgbToCmykColorConverter.of(rgbWhite);
		System.out.println("while " + cmykWhite);

		assertThat(cmykWhite.getCyanColor(), is(0));
		assertThat(cmykWhite.getMagnetaColor(), is(0));
		assertThat(cmykWhite.getYellowColor(), is(0));
		assertThat(cmykWhite.getBlackColor(), is(0));

		assertThat(cmykWhite.getCyanValue(), is(0.0));
		assertThat(cmykWhite.getMagnetaValue(), is(0.0));
		assertThat(cmykWhite.getYellowValue(), is(0.0));
		assertThat(cmykWhite.getBlackValue(), is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej RGB na CMYK")
	public void shouldBeCmykRedColor() {

		var rgbRed = 0xFF_00_00;
		var cmykRed = RgbToCmykColorConverter.of(rgbRed);
		System.out.println("red " + cmykRed);

		assertThat(cmykRed.getCyanColor(), is(0));
		assertThat(cmykRed.getMagnetaColor(), is(100));
		assertThat(cmykRed.getYellowColor(), is(100));
		assertThat(cmykRed.getBlackColor(), is(0));

		assertThat(cmykRed.getCyanValue(), is(0.0));
		assertThat(cmykRed.getMagnetaValue(), is(1.0));
		assertThat(cmykRed.getYellowValue(), is(1.0));
		assertThat(cmykRed.getBlackValue(), is(0.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej RGB na CMYK")
	public void shouldBeCmykLimeColor() {

		var rgbLime = 0x00_FF_00;
		var cmykLime = RgbToCmykColorConverter.of(rgbLime);
		System.out.println("lime " + cmykLime);

		assertThat(cmykLime.getCyanColor(), is(100));
		assertThat(cmykLime.getMagnetaColor(), is(0));
		assertThat(cmykLime.getYellowColor(), is(100));
		assertThat(cmykLime.getBlackColor(), is(0));

		assertThat(cmykLime.getCyanValue(), is(1.0));
		assertThat(cmykLime.getMagnetaValue(), is(0.0));
		assertThat(cmykLime.getYellowValue(), is(1.0));
		assertThat(cmykLime.getBlackValue(), is(0.0));
	}
	
	@Test
	@DisplayName("Test konwersji wartości barwy czarnej RGB na CMYK")
	public void shouldBeCmykBlackColor() {
		
		var rgbBlack = 0x00_00_00;
		var cmykBlack = RgbToCmykColorConverter.of(rgbBlack);
		System.out.println("black " + cmykBlack);
		
		assertThat(cmykBlack.getCyanColor(), is(0));
		assertThat(cmykBlack.getMagnetaColor(), is(0));
		assertThat(cmykBlack.getYellowColor(), is(0));
		assertThat(cmykBlack.getBlackColor(), is(0));
		
		assertThat(cmykBlack.getCyanValue(), is(0.0));
		assertThat(cmykBlack.getMagnetaValue(), is(0.0));
		assertThat(cmykBlack.getYellowValue(), is(0.0));
		assertThat(cmykBlack.getBlackValue(), is(1.0));
	}

}

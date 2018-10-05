package com.codigo.aplios.sdk.color;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class TestRgbToCmykColors {

	// https://www.rapidtables.com/convert/color/rgb-to-cmyk.html
	//https://www.w3schools.com/colors/colors_picker.asp?colorhex=00ffff

	@Test
	public void test1() {

		/**
		 * (R,G,B) Hex (C,M,Y,K) Black (0,0,0) #000000 (0,0,0,1) White (255,255,255)
		 * #FFFFFF (0,0,0,0) Red (255,0,0) #FF0000 (0,1,1,0) Green (0,255,0) #00FF00
		 * (1,0,1,0) Blue (0,0,255) #0000FF (1,1,0,0) Yellow (255,255,0) #FFFF00
		 * (0,0,1,0) Cyan (0,255,255) #00FFFF (1,0,0,0) Magenta (255,0,255) #FF00FF
		 * (0,1,0,0)
		 **/

		// The R,G,B values are divided by 255 to change the range from 0..255 to 0..1:

		// K = 1-max(R', G', B')

		// The cyan color (C) is calculated from the red (R') and black (K) colors:

		// C = (1-R'-K) / (1-K)

		// The magenta color (M) is calculated from the green (G') and black (K) colors:

		// M = (1-G'-K) / (1-K)

		// The yellow color (Y) is calculated from the blue (B') and black (K) colors:

		// Y = (1-B'-K) / (1-K)

		var red = 0.0;
		var green = 0.0;
		var blue = 0.0;
		var cyan = 0.0;
		var magneta = 0.0;
		var yellow = 0.0;
		var max = 0.0;
		var black = 0.0;

		red = 255.0;
		green = 10.0;
		blue = 50.0;

		red = red / 255.0;
		green = green / 255.0;
		blue = blue / 255.0;

		max = Math.max(blue, Math.max(red, green));

		black = 1.0 - max;

		if (black != 1.0) {

			cyan = (1.0 - red - black) / (1.0 - black);
			magneta = (1.0 - green - black) / (1.0 - black);
			yellow = (1.0 - blue - black) / (1.0 - black);
		}

		cyan = Math.round(100.0 * cyan);
		magneta = Math.round(100.0 * magneta);
		yellow = Math.round(100.0 * yellow);
		black = Math.round(100.0 * black);
		
		fail("Dodać implementacje");

	}
}

package com.codigo.aplios.sdk.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//https://pl.wikipedia.org/wiki/HSV_(grafika)

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSV")
public final class TestRgbToHsvColor {

	@Test
	public void test1() {

		var red = 10.0;
		var green = 128.0;
		var blue = 155.0;

		red /= 255.0;
		green /= 255.0;
		blue /= 255.0;

		var minVal = Math.min(red, Math.min(green, blue));
		var maxVal = Math.max(red, Math.max(green, blue));
		var delta = maxVal - minVal;

		var value = maxVal;
		var hue = 0.0;
		var saturation = 0.0;

		if (delta == 0.0) {
			hue = 0.0;
			saturation = 0.0;
		} else {
			saturation = delta / maxVal;
			var del_R = (((maxVal - red) / 6.0) + (delta / 2.0)) / delta;
			var del_G = (((maxVal - green) / 6.0) + (delta / 2.0)) / delta;
			var del_B = (((maxVal - blue) / 6.0) + (delta / 2.0)) / delta;

			if (red == maxVal) {
				hue = del_B - del_G;
			} else if (green == maxVal) {
				hue = (1.0 / 3.0) + del_R - del_B;
			} else if (blue == maxVal) {
				hue = (2.0 / 3.0) + del_G - del_R;
			}

			if (hue < 0.0) {
				hue += 1.0;
			}
			if (hue > 1.0) {
				hue -= 1.0;
			}
		}

		hue = Math.round(hue * 360.0);
		saturation = Math.round(saturation * 1000.0) / 10.0;
		value = Math.round(value * 1000.0) / 10.0;

		assertThat(hue, is(191.0));
		assertThat(saturation, is(93.5));
		assertThat(value, is(60.8));

	}

}

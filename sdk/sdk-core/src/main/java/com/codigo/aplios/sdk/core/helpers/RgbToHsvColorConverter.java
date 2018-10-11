package com.codigo.aplios.sdk.color;

/**
 * Klasa reprezentuje mechanizm konwertera modelu wartości koloru RGB na HSV
 * 
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category converter
 */
public final class RgbToHsvColorConverter {

	/**
	 * Procedura fabrykująca instancję konwertera modelu kolory<code>RgbToHsvColorConverter</code> na
	 * podstawie wartość składowych modelu RGB
	 * 
	 * @param rgbColor
	 *            Wartość koloru w modelu RGB
	 * @return Instancja konwertera koloru RgbToHsvColorConverter
	 */
	public static RgbToHsvColorConverter of(int rgbColor) {

		return new RgbToHsvColorConverter(rgbColor);
	}

	/**
	 * Procedura fabrykująca instancję konwertera modelu kolory<code>RgbToHsvColorConverter</code> na
	 * podstawie wartość składowych modelu RGB
	 * 
	 * @param rgbRed
	 *            Składowa barwy czerwonej modelu RGB
	 * @param rgbGreen
	 *            Składowa barwy zielonej modelu RGB
	 * @param rgbBlue
	 *            Składowa barwy niebieskiej modelu RGB
	 * @return Instancja konwertera koloru RgbToHsvColorConverter
	 */
	public static RgbToHsvColorConverter of(int rgbRed, int rgbGreen, int rgbBlue) {

		return new RgbToHsvColorConverter(rgbRed, rgbGreen, rgbBlue);
	}

	private static final int RGB_MASK_VALUE = 0xFF;
	private static final int SHIFT_BY_2BYTES = 16;
	private static final int SHIFT_BY_1BYTES = 8;

	private int rgbColorValue;
	private double minOfRgbValue;
	private double maxOfRgbValue;
	private double hue;
	private double saturation;
	private double value;
	private double delta;

	/**
	 * Podstawowy konstruktor obiektu klas<code>RgbToHsvColorConverter</code>
	 * 
	 * @param rgbValue
	 */
	private RgbToHsvColorConverter(int rgbValue) {

		this((rgbValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE, (rgbValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE,
				rgbValue & RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klas<code>RgbToHsvColorConverter</code>
	 * 
	 * @param redValue
	 *            Składowa barwy czerwonej modelu RGB
	 * @param greenValue
	 *            Składowa barwy zielonej modelu RGB
	 * @param blueValue
	 *            Składowa barwy niebieskiej modelu RGB
	 */
	private RgbToHsvColorConverter(int redValue, int greenValue, int blueValue) {

		if ((redValue < 0) && (redValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		rgbColorValue = blueValue;
		rgbColorValue |= greenValue << SHIFT_BY_1BYTES;
		rgbColorValue |= redValue << SHIFT_BY_2BYTES;

		var red = 0.0;
		red = (rgbColorValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE;

		var green = 0.0;
		green = (rgbColorValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE;

		var blue = 0.0;
		blue = rgbColorValue & RGB_MASK_VALUE;

		red /= RGB_MASK_VALUE;
		green /= RGB_MASK_VALUE;
		blue /= RGB_MASK_VALUE;

		minOfRgbValue = Math.min(red, Math.min(green, blue));
		maxOfRgbValue = Math.max(red, Math.max(green, blue));
		delta = maxOfRgbValue - minOfRgbValue;
		value = maxOfRgbValue;

		if (delta != 0.0) {

			saturation = delta / maxOfRgbValue;
			var deltaRed = (((maxOfRgbValue - red) / 6.0) + (delta / 2.0)) / delta;
			var deltaGreen = (((maxOfRgbValue - green) / 6.0) + (delta / 2.0)) / delta;
			var deltaBlue = (((maxOfRgbValue - blue) / 6.0) + (delta / 2.0)) / delta;

			if (red == maxOfRgbValue)
				hue = deltaBlue - deltaGreen;
			else if (green == maxOfRgbValue)
				hue = (1.0 / 3.0) + deltaRed - deltaBlue;
			else if (blue == maxOfRgbValue)
				hue = (2.0 / 3.0) + deltaGreen - deltaRed;

			if (hue < 0.0)
				hue += 1.0;

			if (hue > 1.0)
				hue -= 1.0;
		}
	}

	/**
	 * Właściwość określa składową odcienia światła w modelu HSV
	 * 
	 * @return Wartość odcienia światła
	 */
	public double getHue() {

		return Math.round(hue * 360.0);
	}

	/**
	 * Właściwość określa składową nasycenia koloru w modelu HSV
	 * 
	 * @return Wartość nasycenia koloru
	 */
	public double getSaturation() {

		return Math.round(saturation * 1E3) / 1E1;
	}

	/**
	 * Właściwość określa składową mocy światła białego(brightness) w modelu HSV
	 * 
	 * @return Wartość mocy światła białego
	 */
	public double getValue() {

		return Math.round(value * 1E3) / 1E1;
	}
}

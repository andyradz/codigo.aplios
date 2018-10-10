package com.codigo.aplios.sdk.color;

public final class RgbToHsvColorConverter {

	public static RgbToHsvColorConverter of(int rgbColor) {

		return new RgbToHsvColorConverter(rgbColor);
	}

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
	private double lightness;

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToCmykColorConverter</code>
	 * 
	 * @param rgbValue
	 */
	private RgbToHsvColorConverter(int rgbValue) {

		this((rgbValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE, (rgbValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE,
				rgbValue & RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RgbToHexColorConverter</code>
	 * 
	 * @param redValue
	 *            Parametr określa wartość składowej barwy RED w notacji RGB
	 * @param greenValue
	 *            Parametr określa wartość składowej barwy GREEN w notacji RGB
	 * @param blueValue
	 *            Parametr określa wartość składowej barwy BLUE w notacji RGB
	 */
	public RgbToHsvColorConverter(int redValue, int greenValue, int blueValue) {

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

		minOfRgbValue = Math.min(blue, Math.min(red, green));
		maxOfRgbValue = Math.max(blue, Math.max(red, green));

		lightness = (maxOfRgbValue + minOfRgbValue) / 2.0;

		if (maxOfRgbValue != minOfRgbValue) {
			final var d = maxOfRgbValue - minOfRgbValue;

			saturation = lightness > 0.5 ? d / (2.0 - maxOfRgbValue - minOfRgbValue)
					: d / (maxOfRgbValue + minOfRgbValue);

			if (maxOfRgbValue == red)
				hue = (green - blue) / d + (green < blue ? 6.0 : 0.0);

			else if (maxOfRgbValue == green)
				hue = (blue - red) / d + 2.0;

			else if (maxOfRgbValue == blue)
				hue = (red - green) / d + 4.0;
		}

	}

	public double getHue() {

		return Math.round(hue * 60.0);
	}

	public double getSaturation() {

		return Math.round(saturation * 1E3) / 1E1;
	}

	public double getLightness() {

		return Math.round(lightness * 1E3) / 1E1;
	}

}

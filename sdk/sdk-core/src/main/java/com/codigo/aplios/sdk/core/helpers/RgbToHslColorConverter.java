package com.codigo.aplios.sdk.core.helpers;

public final class RgbToHslColorConverter {

	public static RgbToHslColorConverter of(final int rgbColor) {

		return new RgbToHslColorConverter(
			rgbColor);
	}

	public static RgbToHslColorConverter of(final int rgbRed, final int rgbGreen, final int rgbBlue) {

		return new RgbToHslColorConverter(
			rgbRed, rgbGreen, rgbBlue);
	}

	private static final int	RGB_MASK_VALUE	= 0xFF;
	private static final int	SHIFT_BY_2BYTES	= 16;
	private static final int	SHIFT_BY_1BYTES	= 8;

	private int				rgbColorValue;
	private final double	minOfRgbValue;
	private final double	maxOfRgbValue;
	private double			hue;
	private double			saturation;
	private final double	lightness;

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToCmykColorConverter</code>
	 *
	 * @param rgbValue
	 */
	private RgbToHslColorConverter(final int rgbValue) {

		this((rgbValue >> RgbToHslColorConverter.SHIFT_BY_2BYTES) & RgbToHslColorConverter.RGB_MASK_VALUE,
				(rgbValue >> RgbToHslColorConverter.SHIFT_BY_1BYTES) & RgbToHslColorConverter.RGB_MASK_VALUE,
				rgbValue & RgbToHslColorConverter.RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RgbToHexColorConverter</code>
	 *
	 * @param redValue
	 *        Parametr określa wartość składowej barwy RED w notacji RGB
	 * @param greenValue
	 *        Parametr określa wartość składowej barwy GREEN w notacji RGB
	 * @param blueValue
	 *        Parametr określa wartość składowej barwy BLUE w notacji RGB
	 */
	private RgbToHslColorConverter(final int redValue, final int greenValue, final int blueValue) {

		if ((redValue < 0) && (redValue > RgbToHslColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RgbToHslColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RgbToHslColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		this.rgbColorValue = blueValue;
		this.rgbColorValue |= greenValue << RgbToHslColorConverter.SHIFT_BY_1BYTES;
		this.rgbColorValue |= redValue << RgbToHslColorConverter.SHIFT_BY_2BYTES;

		var red = 0.0;
		red = (this.rgbColorValue >> RgbToHslColorConverter.SHIFT_BY_2BYTES) & RgbToHslColorConverter.RGB_MASK_VALUE;

		var green = 0.0;
		green = (this.rgbColorValue >> RgbToHslColorConverter.SHIFT_BY_1BYTES) & RgbToHslColorConverter.RGB_MASK_VALUE;

		var blue = 0.0;
		blue = this.rgbColorValue & RgbToHslColorConverter.RGB_MASK_VALUE;

		red /= RgbToHslColorConverter.RGB_MASK_VALUE;
		green /= RgbToHslColorConverter.RGB_MASK_VALUE;
		blue /= RgbToHslColorConverter.RGB_MASK_VALUE;

		this.minOfRgbValue = Math.min(blue, Math.min(red, green));
		this.maxOfRgbValue = Math.max(blue, Math.max(red, green));

		this.lightness = (this.maxOfRgbValue + this.minOfRgbValue) / 2.0;

		if (this.maxOfRgbValue != this.minOfRgbValue) {
			final var d = this.maxOfRgbValue - this.minOfRgbValue;

			this.saturation = this.lightness > 0.5 ? d / (2.0 - this.maxOfRgbValue - this.minOfRgbValue)
					: d / (this.maxOfRgbValue + this.minOfRgbValue);

			if (this.maxOfRgbValue == red)
				this.hue = ((green - blue) / d) + (green < blue ? 6.0 : 0.0);

			else if (this.maxOfRgbValue == green)
				this.hue = ((blue - red) / d) + 2.0;

			else if (this.maxOfRgbValue == blue)
				this.hue = ((red - green) / d) + 4.0;
		}

	}

	public double getHue() {

		return Math.round(this.hue * 60.0);
	}

	public double getSaturation() {

		return Math.round(this.saturation * 1E3) / 1E1;
	}

	public double getLightness() {

		return Math.round(this.lightness * 1E3) / 1E1;
	}

}

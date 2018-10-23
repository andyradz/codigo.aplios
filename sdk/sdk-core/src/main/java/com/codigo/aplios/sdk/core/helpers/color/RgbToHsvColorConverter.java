package com.codigo.aplios.sdk.core.helpers.color;

/**
 * Klasa reprezentuje mechanizm konwertera modelu wartości koloru RGB na HSV
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category converter
 */
public final class RgbToHsvColorConverter {

	private static final int	RGB_MASK_VALUE	= 0xFF;
	private static final int	SHIFT_BY_2BYTES	= 16;
	private static final int	SHIFT_BY_1BYTES	= 8;

	private int				rgbColorValue;
	private final double	minOfRgbValue;
	private final double	maxOfRgbValue;
	private double			hue;
	private double			saturation;
	private final double	value;
	private final double	delta;

	/**
	 * Podstawowy konstruktor obiektu klas<code>RgbToHsvColorConverter</code>
	 *
	 * @param rgbValue
	 */
	RgbToHsvColorConverter(final int rgbValue) {

		this((rgbValue >> RgbToHsvColorConverter.SHIFT_BY_2BYTES) & RgbToHsvColorConverter.RGB_MASK_VALUE,
				(rgbValue >> RgbToHsvColorConverter.SHIFT_BY_1BYTES) & RgbToHsvColorConverter.RGB_MASK_VALUE,
				rgbValue & RgbToHsvColorConverter.RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klas<code>RgbToHsvColorConverter</code>
	 *
	 * @param redValue
	 *        Składowa barwy czerwonej modelu RGB
	 * @param greenValue
	 *        Składowa barwy zielonej modelu RGB
	 * @param blueValue
	 *        Składowa barwy niebieskiej modelu RGB
	 */
	RgbToHsvColorConverter(final int redValue, final int greenValue, final int blueValue) {

		if ((redValue < 0) && (redValue > RgbToHsvColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RgbToHsvColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RgbToHsvColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		this.rgbColorValue = blueValue;
		this.rgbColorValue |= greenValue << RgbToHsvColorConverter.SHIFT_BY_1BYTES;
		this.rgbColorValue |= redValue << RgbToHsvColorConverter.SHIFT_BY_2BYTES;

		var red = 0.0;
		red = (this.rgbColorValue >> RgbToHsvColorConverter.SHIFT_BY_2BYTES) & RgbToHsvColorConverter.RGB_MASK_VALUE;

		var green = 0.0;
		green = (this.rgbColorValue >> RgbToHsvColorConverter.SHIFT_BY_1BYTES) & RgbToHsvColorConverter.RGB_MASK_VALUE;

		var blue = 0.0;
		blue = this.rgbColorValue & RgbToHsvColorConverter.RGB_MASK_VALUE;

		red /= RgbToHsvColorConverter.RGB_MASK_VALUE;
		green /= RgbToHsvColorConverter.RGB_MASK_VALUE;
		blue /= RgbToHsvColorConverter.RGB_MASK_VALUE;

		this.minOfRgbValue = Math.min(red, Math.min(green, blue));
		this.maxOfRgbValue = Math.max(red, Math.max(green, blue));
		this.delta = this.maxOfRgbValue - this.minOfRgbValue;
		this.value = this.maxOfRgbValue;

		if (this.delta != 0.0) {

			this.saturation = this.delta / this.maxOfRgbValue;
			final var deltaRed = (((this.maxOfRgbValue - red) / 6.0) + (this.delta / 2.0)) / this.delta;
			final var deltaGreen = (((this.maxOfRgbValue - green) / 6.0) + (this.delta / 2.0)) / this.delta;
			final var deltaBlue = (((this.maxOfRgbValue - blue) / 6.0) + (this.delta / 2.0)) / this.delta;

			if (red == this.maxOfRgbValue)
				this.hue = deltaBlue - deltaGreen;
			else if (green == this.maxOfRgbValue)
				this.hue = ((1.0 / 3.0) + deltaRed) - deltaBlue;
			else if (blue == this.maxOfRgbValue)
				this.hue = ((2.0 / 3.0) + deltaGreen) - deltaRed;

			if (this.hue < 0.0)
				this.hue += 1.0;

			if (this.hue > 1.0)
				this.hue -= 1.0;
		}
	}

	/**
	 * Właściwość określa składową odcienia światła w modelu HSV
	 *
	 * @return Wartość odcienia światła
	 */
	public double getHue() {

		return Math.round(this.hue * 360.0);
	}

	/**
	 * Właściwość określa składową nasycenia koloru w modelu HSV
	 *
	 * @return Wartość nasycenia koloru
	 */
	public double getSaturation() {

		return Math.round(this.saturation * 1E3) / 1E1;
	}

	/**
	 * Właściwość określa składową mocy światła białego(brightness) w modelu HSV
	 *
	 * @return Wartość mocy światła białego
	 */
	public double getValue() {

		return Math.round(this.value * 1E3) / 1E1;
	}
}

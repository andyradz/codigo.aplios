package com.codigo.aplios.sdk.core.helpers.color;

/**
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category converter
 */
public final class ColorConverter {

	public static HexToRbgColorConverter ofHexToRgb(final String hexColorValue) {

		return new HexToRbgColorConverter(
			hexColorValue);
	}

	public static RgbToCmykColorConverter ofRgbToCmyk(final int rgbColorValue) {

		return new RgbToCmykColorConverter(
			rgbColorValue);
	}

	public static RgbToCmykColorConverter ofRgbToCmyk(final String hexColorValue) {

		return new RgbToCmykColorConverter(
			hexColorValue);
	}

	public static RgbToCmykColorConverter ofRgbToCmyk(final int redValue, final int greenValue, final int blueValue) {

		return new RgbToCmykColorConverter(
			redValue, greenValue, blueValue);
	}

	public static RgbToHslColorConverter ofRgbToHsl(final int rgbColor) {

		return new RgbToHslColorConverter(
			rgbColor);
	}

	public static RgbToHslColorConverter ofRgbToHsl(final int rgbRed, final int rgbGreen, final int rgbBlue) {

		return new RgbToHslColorConverter(
			rgbRed, rgbGreen, rgbBlue);
	}

	/**
	 * Procedura fabrykująca instancję konwertera modelu kolory<code>RgbToHsvColorConverter</code> na
	 * podstawie wartość składowych modelu RGB
	 *
	 * @param rgbColor
	 *        Wartość koloru w modelu RGB
	 * @return Instancja konwertera koloru RgbToHsvColorConverter
	 */
	public static RgbToHsvColorConverter ofRgbToHsv(final int rgbColor) {

		return new RgbToHsvColorConverter(
			rgbColor);
	}

	/**
	 * Procedura fabrykująca instancję konwertera modelu kolory<code>RgbToHsvColorConverter</code> na
	 * podstawie wartość składowych modelu RGB
	 *
	 * @param rgbRed
	 *        Składowa barwy czerwonej modelu RGB
	 * @param rgbGreen
	 *        Składowa barwy zielonej modelu RGB
	 * @param rgbBlue
	 *        Składowa barwy niebieskiej modelu RGB
	 * @return Instancja konwertera koloru RgbToHsvColorConverter
	 */
	public static RgbToHsvColorConverter ofRgbToHsv(final int rgbRed, final int rgbGreen, final int rgbBlue) {

		return new RgbToHsvColorConverter(
			rgbRed, rgbGreen, rgbBlue);
	}

	private ColorConverter() {
	}
}

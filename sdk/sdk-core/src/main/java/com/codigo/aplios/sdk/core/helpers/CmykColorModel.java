package com.codigo.aplios.sdk.core.helpers;

public final class CmykColorModel {

	public static CmykColorModel of(final int rgbValue) {

		return new CmykColorModel(
			rgbValue);
	}

	public static CmykColorModel of(final int redValue, final int greenValue, final int blueValue) {

		return new CmykColorModel(
			redValue, greenValue, blueValue);
	}

	public static CmykColorModel of(final String hexColorValue) {

		return new CmykColorModel(
			hexColorValue);
	}

	private static final int	RGB_MASK_VALUE	= 255;
	private static final int	CMYK_FACTOR		= 1;
	private static final int	ONE_HUNDRED		= 100;

	private final RgbColorModel rgbColorModel;

	private CmykColorModel(final int rgbValue) {

		this.rgbColorModel = RgbColorModel.of(rgbValue);
	}

	private CmykColorModel(final String hexColorValue) {

		this.rgbColorModel = RgbColorModel.of(hexColorValue);
	}

	private CmykColorModel(final int redValue, final int greenValue, final int blueValue) {

		this.rgbColorModel = RgbColorModel.of(redValue, greenValue, blueValue);
	}

	/**
	 * Właściwość określa współczynnik wartość składowej BLACK w notacji CMYK
	 *
	 * @return Wartość składowej barwy BLACK
	 */
	private double getBlackValue() {

		return CmykColorModel.CMYK_FACTOR - getMaxOfRgbValue();
	}

	private int getMaxOfRgbValue() {

		return Math.max(this.rgbColorModel.getRedValue(),
				Math.max(this.rgbColorModel.getGreenValue(), this.rgbColorModel.getBlueValue()));
	}

	public int getCyanColor() {

		final var black = getBlackValue();
		final var red = this.rgbColorModel.getRedValue() / CmykColorModel.RGB_MASK_VALUE;

		var cyan = (int) (CmykColorModel.CMYK_FACTOR - red - black) / (CmykColorModel.CMYK_FACTOR - black);
		cyan = Math.round(CmykColorModel.ONE_HUNDRED * cyan);
		return (int) cyan;
	}

	public int getMagnetaColor() {

		final var black = getBlackValue();
		final var green = this.rgbColorModel.getGreenValue() / CmykColorModel.RGB_MASK_VALUE;

		return (int) (black != CmykColorModel.CMYK_FACTOR
				? (CmykColorModel.CMYK_FACTOR - green - black) / (CmykColorModel.CMYK_FACTOR - black)
				: 0);
	}

	public int getYellowColor() {

		final var black = (CmykColorModel.CMYK_FACTOR - getMaxOfRgbValue());
		final var blue = this.rgbColorModel.getBlueValue() / CmykColorModel.RGB_MASK_VALUE;

		var yellow = (CmykColorModel.CMYK_FACTOR - blue - black) / (CmykColorModel.CMYK_FACTOR - black);
		yellow = Math.round(CmykColorModel.ONE_HUNDRED * yellow);

		return yellow;
	}

	public int getBlackColor() {

		var black = (CmykColorModel.CMYK_FACTOR - getMaxOfRgbValue()) / CmykColorModel.RGB_MASK_VALUE;
		black = Math.round(CmykColorModel.ONE_HUNDRED * black);

		return black;
	}

	@Override
	public String toString() {

		final String format = "CMYK = (%dC, %dM, %dY, %dK)";

		final var cyan = getCyanColor();
		final var magneta = getMagnetaColor();
		final var yellow = getYellowColor();
		final var black = getBlackColor();

		return String.format(format, cyan, magneta, yellow, black);
	}
}

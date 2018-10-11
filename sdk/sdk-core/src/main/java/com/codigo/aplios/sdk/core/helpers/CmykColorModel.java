package com.codigo.aplios.sdk.color;

public final class CmykColorModel {

	public static CmykColorModel of(int rgbValue) {

		return new CmykColorModel(rgbValue);
	}

	public static CmykColorModel of(int redValue, int greenValue, int blueValue) {

		return new CmykColorModel(redValue, greenValue, blueValue);
	}

	public static CmykColorModel of(String hexColorValue) {

		return new CmykColorModel(hexColorValue);
	}

	private static final int RGB_MASK_VALUE = 255;
	private static final int CMYK_FACTOR = 1;
	private static final int ONE_HUNDRED = 100;

	private RgbColorModel rgbColorModel;

	private CmykColorModel(int rgbValue) {

		rgbColorModel = RgbColorModel.of(rgbValue);
	}

	private CmykColorModel(String hexColorValue) {

		rgbColorModel = RgbColorModel.of(hexColorValue);
	}

	private CmykColorModel(int redValue, int greenValue, int blueValue) {

		rgbColorModel = RgbColorModel.of(redValue, greenValue, blueValue);
	}

	/**
	 * Właściwość określa współczynnik wartość składowej BLACK w notacji CMYK
	 * 
	 * @return Wartość składowej barwy BLACK
	 */
	private double getBlackValue() {

		return CMYK_FACTOR - getMaxOfRgbValue();
	}

	private int getMaxOfRgbValue() {

		return Math.max(rgbColorModel.getRedValue(),
				Math.max(rgbColorModel.getGreenValue(), rgbColorModel.getBlueValue()));
	}

	public int getCyanColor() {

		final var black = getBlackValue();
		final var red = rgbColorModel.getRedValue() / RGB_MASK_VALUE;

		var cyan = (int)(CMYK_FACTOR - red - black) / (CMYK_FACTOR - black);
		cyan = Math.round(ONE_HUNDRED * cyan);
		return (int)cyan;
	}

	public int getMagnetaColor() {

		final var black = getBlackValue();
		final var green = rgbColorModel.getGreenValue() / RGB_MASK_VALUE;

		return (int)(black != CMYK_FACTOR ? (CMYK_FACTOR - green - black) / (CMYK_FACTOR - black) : 0);
	}

	public int getYellowColor() {

		final var black = (CMYK_FACTOR - getMaxOfRgbValue());
		final var blue = rgbColorModel.getBlueValue() / RGB_MASK_VALUE;

		var yellow = (CMYK_FACTOR - blue - black) / (CMYK_FACTOR - black);
		yellow = Math.round(ONE_HUNDRED * yellow);

		return (int)yellow;
	}

	public int getBlackColor() {

		var black = (CMYK_FACTOR - getMaxOfRgbValue()) / RGB_MASK_VALUE;
		black = Math.round(ONE_HUNDRED * black);

		return (int)black;
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

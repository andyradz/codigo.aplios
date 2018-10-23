package com.codigo.aplios.sdk.core.helpers.color;

public final class RgbToCmykColorConverter {

	private static final int	RGB_MASK_VALUE	= 0xFF;
	private static final int	SHIFT_BY_2BYTES	= 16;
	private static final int	SHIFT_BY_1BYTES	= 8;

	private final CmykColorModel cmykModel;

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToCmykColorConverter</code>
	 *
	 * @param rgbValue
	 */
	RgbToCmykColorConverter(final int rgbValue) {

		this((rgbValue >> RgbToCmykColorConverter.SHIFT_BY_2BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE,
				(rgbValue >> RgbToCmykColorConverter.SHIFT_BY_1BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE,
				rgbValue & RgbToCmykColorConverter.RGB_MASK_VALUE);
	}

	RgbToCmykColorConverter(final String hexColorValue) {

		this.cmykModel = CmykColorModel.of(hexColorValue);
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
	RgbToCmykColorConverter(final int redValue, final int greenValue, final int blueValue) {

		if ((redValue < 0) && (redValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		this.cmykModel = CmykColorModel.of(redValue, greenValue, blueValue);
	}

	/**
	 * Właściwość określa współczynnik wartość składowej CYAN w notacji CMYK
	 *
	 * @return Wartość składowej barwy CYAN
	 */
	public double getCyanFactor() {

		return this.cmykModel.getCyanFactor();
	}

	/**
	 * Właściwość określa wartość składowej barwy CYAN w notacji CMYK
	 *
	 * @return Wartość składowej barwy CYAN
	 */
	public int getCyanColor() {

		return this.cmykModel.getCyanColor();
	}

	/**
	 * Właściwość określa współczynnik wartość składowej MAGNETA w notacji CMYK
	 *
	 * @return Wartość składowej barwy MAGNETA
	 */
	public double getMagnetaFactor() {

		return this.cmykModel.getMagnetaFactor();
	}

	/**
	 * Właściwość określa wartość składowej barwy MAGNETA w notacji CMYK
	 *
	 * @return Wartość składowej barwy MAGNETA
	 */
	public int getMagnetaColor() {

		return this.cmykModel.getMagnetaColor();
	}

	/**
	 * Właściwość określa współczynnik wartość składowej YELLOW w notacji CMYK
	 *
	 * @return Wartość składowej barwy YELLOW
	 */
	public double getYellowFactor() {

		return this.cmykModel.getYellowFactor();
	}

	/**
	 * Właściwość określa wartość składowej barwy YELLOW w notacji CMYK
	 *
	 * @return Wartość składowej barwy YELLOW
	 */
	public int getYellowColor() {

		return this.cmykModel.getYellowColor();
	}

	/**
	 * Właściwość określa współczynnik wartość składowej BLACK w notacji CMYK
	 *
	 * @return Wartość składowej barwy BLACK
	 */
	public double getBlackFactor() {

		return this.cmykModel.getBlackFactor();
	}

	/**
	 * Właściwość określa wartość składowej BLACK w notacji CMYK
	 *
	 * @return Wartość składowej barwy BLACK
	 */
	public int getBlackColor() {

		return this.cmykModel.getBlackColor();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
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
package com.codigo.aplios.sdk.core.helpers;

public final class RgbToCmykColorConverter {

	public static RgbToCmykColorConverter of(final int rgbValue) {

		return new RgbToCmykColorConverter(
			rgbValue);
	}

	public static RgbToCmykColorConverter of(final int redValue, final int greenValue, final int blueValue) {

		return new RgbToCmykColorConverter(
			redValue, greenValue, blueValue);
	}

	private static final int	RGB_MASK_VALUE	= 0xFF;
	private static final int	SHIFT_BY_2BYTES	= 16;
	private static final int	SHIFT_BY_1BYTES	= 8;
	private static final double	CMYK_FACTOR		= 1.0;
	private static final double	ONE_HUNDRED		= 100.0;

	private int			rgbColorValue;
	private final int	maxOfRgbValue;

	/**
	 * Właściwość określna składową RED koloru zapisanego w notacji RGB
	 *
	 * @return Wartośc numeryczna barwy RED
	 */
	private int getRedColor() {

		return (this.rgbColorValue >> RgbToCmykColorConverter.SHIFT_BY_2BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określna składową GREEN koloru zapisanego w notacji RGB
	 *
	 * @return Wartośc numeryczna barwy GREEN
	 */
	private int getGreenColor() {

		return (this.rgbColorValue >> RgbToCmykColorConverter.SHIFT_BY_1BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określna składową BLUE koloru zapisanego w notacji RGB
	 *
	 * @return Wartośc numeryczna barwy BLUE
	 */
	private int getBlueColor() {

		return (this.rgbColorValue & RgbToCmykColorConverter.RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToCmykColorConverter</code>
	 *
	 * @param rgbValue
	 */
	private RgbToCmykColorConverter(final int rgbValue) {

		this((rgbValue >> RgbToCmykColorConverter.SHIFT_BY_2BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE,
				(rgbValue >> RgbToCmykColorConverter.SHIFT_BY_1BYTES) & RgbToCmykColorConverter.RGB_MASK_VALUE,
				rgbValue & RgbToCmykColorConverter.RGB_MASK_VALUE);
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
	public RgbToCmykColorConverter(final int redValue, final int greenValue, final int blueValue) {

		if ((redValue < 0) && (redValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RgbToCmykColorConverter.RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		this.rgbColorValue = blueValue;
		this.rgbColorValue |= greenValue << RgbToCmykColorConverter.SHIFT_BY_1BYTES;
		this.rgbColorValue |= redValue << RgbToCmykColorConverter.SHIFT_BY_2BYTES;

		var red = (this.rgbColorValue >> RgbToCmykColorConverter.SHIFT_BY_2BYTES)
				& RgbToCmykColorConverter.RGB_MASK_VALUE;
		var green = (this.rgbColorValue >> RgbToCmykColorConverter.SHIFT_BY_1BYTES)
				& RgbToCmykColorConverter.RGB_MASK_VALUE;
		var blue = this.rgbColorValue & RgbToCmykColorConverter.RGB_MASK_VALUE;

		red /= RgbToCmykColorConverter.RGB_MASK_VALUE;
		green /= RgbToCmykColorConverter.RGB_MASK_VALUE;
		blue /= RgbToCmykColorConverter.RGB_MASK_VALUE;

		this.maxOfRgbValue = Math.max(blue, Math.max(red, green));
	}

	/**
	 * Właściwość określa współczynnik wartość składowej CYAN w notacji CMYK
	 *
	 * @return Wartość składowej barwy CYAN
	 */
	public double getCyanValue() {

		final var black = getBlackValue();
		final var red = getRedColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		return black != RgbToCmykColorConverter.CMYK_FACTOR
				? (RgbToCmykColorConverter.CMYK_FACTOR - red - black) / (RgbToCmykColorConverter.CMYK_FACTOR - black)
				: 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy CYAN w notacji CMYK
	 *
	 * @return Wartość składowej barwy CYAN
	 */
	public int getCyanColor() {

		final var black = getBlackValue();
		final var red = getRedColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		var cyan = (RgbToCmykColorConverter.CMYK_FACTOR - red - black) / (RgbToCmykColorConverter.CMYK_FACTOR - black);
		cyan = Math.round(RgbToCmykColorConverter.ONE_HUNDRED * cyan);
		return (int) cyan;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej MAGNETA w notacji CMYK
	 *
	 * @return Wartość składowej barwy MAGNETA
	 */
	public double getMagnetaValue() {

		final var black = getBlackValue();
		final var green = getGreenColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		return black != RgbToCmykColorConverter.CMYK_FACTOR
				? (RgbToCmykColorConverter.CMYK_FACTOR - green - black) / (RgbToCmykColorConverter.CMYK_FACTOR - black)
				: 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy MAGNETA w notacji CMYK
	 *
	 * @return Wartość składowej barwy MAGNETA
	 */
	public int getMagnetaColor() {

		final var black = getBlackValue();
		final var green = getGreenColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		var magneta = (RgbToCmykColorConverter.CMYK_FACTOR - green - black)
				/ (RgbToCmykColorConverter.CMYK_FACTOR - black);
		magneta = Math.round(RgbToCmykColorConverter.ONE_HUNDRED * magneta);

		return (int) magneta;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej YELLOW w notacji CMYK
	 *
	 * @return Wartość składowej barwy YELLOW
	 */
	public double getYellowValue() {

		final var black = getBlackValue();
		final var blue = getBlueColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		return black != RgbToCmykColorConverter.CMYK_FACTOR
				? (RgbToCmykColorConverter.CMYK_FACTOR - blue - black) / (RgbToCmykColorConverter.CMYK_FACTOR - black)
				: 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy YELLOW w notacji CMYK
	 *
	 * @return Wartość składowej barwy YELLOW
	 */
	public int getYellowColor() {

		final var black = getBlackValue();
		final var blue = getBlueColor() / RgbToCmykColorConverter.RGB_MASK_VALUE;

		var yellow = (RgbToCmykColorConverter.CMYK_FACTOR - blue - black)
				/ (RgbToCmykColorConverter.CMYK_FACTOR - black);
		yellow = Math.round(RgbToCmykColorConverter.ONE_HUNDRED * yellow);

		return (int) yellow;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej BLACK w notacji CMYK
	 *
	 * @return Wartość składowej barwy BLACK
	 */
	public double getBlackValue() {

		return RgbToCmykColorConverter.CMYK_FACTOR - this.maxOfRgbValue;
	}

	/**
	 * Właściwość określa wartość składowej BLACK w notacji CMYK
	 *
	 * @return Wartość składowej barwy BLACK
	 */
	public int getBlackColor() {

		var black = getBlackValue() / RgbToCmykColorConverter.RGB_MASK_VALUE;
		black = Math.round(RgbToCmykColorConverter.ONE_HUNDRED * black);

		return (int) black;
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
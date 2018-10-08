package com.codigo.aplios.sdk.color;

public final class RgbToCmykColorConverter {

	public static RgbToCmykColorConverter of(int rgbValue) {

		return new RgbToCmykColorConverter(rgbValue);
	}

	public static RgbToCmykColorConverter of(int redValue, int greenValue, int blueValue) {

		return new RgbToCmykColorConverter(redValue, greenValue, blueValue);
	}

	private static final int RGB_MASK_VALUE = 0xFF;
	private static final int SHIFT_BY_2BYTES = 16;
	private static final int SHIFT_BY_1BYTES = 8;
	private static final double CMYK_FACTOR = 1.0;
	private static final double ONE_HUNDRED = 100.0;

	private int rgbColorValue;
	private int maxOfRgbValue;

	/**
	 * Właściwość określna składową RED koloru zapisanego w notacji RGB
	 * 
	 * @return Wartośc numeryczna barwy RED
	 */
	private int getRedColor() {

		return (rgbColorValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określna składową GREEN koloru zapisanego w notacji RGB
	 * 
	 * @return Wartośc numeryczna barwy GREEN
	 */
	private int getGreenColor() {

		return (rgbColorValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określna składową BLUE koloru zapisanego w notacji RGB
	 * 
	 * @return Wartośc numeryczna barwy BLUE
	 */
	private int getBlueColor() {

		return (rgbColorValue & RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToCmykColorConverter</code>
	 * 
	 * @param rgbValue
	 */
	private RgbToCmykColorConverter(int rgbValue) {

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
	public RgbToCmykColorConverter(int redValue, int greenValue, int blueValue) {

		if ((redValue < 0) && (redValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > RGB_MASK_VALUE))
			throw new IllegalArgumentException();

		rgbColorValue = blueValue;
		rgbColorValue |= greenValue << SHIFT_BY_1BYTES;
		rgbColorValue |= redValue << SHIFT_BY_2BYTES;

		var red = (rgbColorValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE;
		var green = (rgbColorValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE;
		var blue = rgbColorValue & RGB_MASK_VALUE;

		red /= RGB_MASK_VALUE;
		green /= RGB_MASK_VALUE;
		blue /= RGB_MASK_VALUE;

		maxOfRgbValue = Math.max(blue, Math.max(red, green));
	}

	/**
	 * Właściwość określa współczynnik wartość składowej CYAN w notacji CMYK
	 * 
	 * @return Wartość składowej barwy CYAN
	 */
	public double getCyanValue() {

		final var black = getBlackValue();
		final var red = getRedColor() / RGB_MASK_VALUE;

		return black != CMYK_FACTOR ? (CMYK_FACTOR - red - black) / (CMYK_FACTOR - black) : 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy CYAN w notacji CMYK
	 * 
	 * @return Wartość składowej barwy CYAN
	 */
	public int getCyanColor() {

		final var black = getBlackValue();
		final var red = getRedColor() / RGB_MASK_VALUE;

		var cyan = (CMYK_FACTOR - red - black) / (CMYK_FACTOR - black);
		cyan = Math.round(ONE_HUNDRED * cyan);
		return (int) cyan;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej MAGNETA w notacji CMYK
	 * 
	 * @return Wartość składowej barwy MAGNETA
	 */
	public double getMagnetaValue() {

		final var black = getBlackValue();
		final var green = getGreenColor() / RGB_MASK_VALUE;

		return black != CMYK_FACTOR ? (CMYK_FACTOR - green - black) / (CMYK_FACTOR - black) : 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy MAGNETA w notacji CMYK
	 * 
	 * @return Wartość składowej barwy MAGNETA
	 */
	public int getMagnetaColor() {

		final var black = getBlackValue();
		final var green = getGreenColor() / RGB_MASK_VALUE;

		var magneta = (CMYK_FACTOR - green - black) / (CMYK_FACTOR - black);
		magneta = Math.round(ONE_HUNDRED * magneta);

		return (int) magneta;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej YELLOW w notacji CMYK
	 * 
	 * @return Wartość składowej barwy YELLOW
	 */
	public double getYellowValue() {

		final var black = getBlackValue();
		final var blue = getBlueColor() / RGB_MASK_VALUE;

		return black != CMYK_FACTOR ? (CMYK_FACTOR - blue - black) / (CMYK_FACTOR - black) : 0.0;
	}

	/**
	 * Właściwość określa wartość składowej barwy YELLOW w notacji CMYK
	 * 
	 * @return Wartość składowej barwy YELLOW
	 */
	public int getYellowColor() {

		final var black = getBlackValue();
		final var blue = getBlueColor() / RGB_MASK_VALUE;

		var yellow = (CMYK_FACTOR - blue - black) / (CMYK_FACTOR - black);
		yellow = Math.round(ONE_HUNDRED * yellow);

		return (int) yellow;
	}

	/**
	 * Właściwość określa współczynnik wartość składowej BLACK w notacji CMYK
	 * 
	 * @return Wartość składowej barwy BLACK
	 */
	public double getBlackValue() {

		return CMYK_FACTOR - maxOfRgbValue;
	}

	/**
	 * Właściwość określa wartość składowej BLACK w notacji CMYK
	 * 
	 * @return Wartość składowej barwy BLACK
	 */
	public int getBlackColor() {

		var black = getBlackValue() / RGB_MASK_VALUE;
		black = Math.round(ONE_HUNDRED * black);

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
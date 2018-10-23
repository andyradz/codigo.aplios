package com.codigo.aplios.sdk.core.helpers.color;

import java.util.Objects;

/**
 * Klasa reprezentuje wartości składowe model koloru zapisanego w notacji RGB
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category converter
 *
 */
public final class RgbColorModel {

	/**
	 * Metoda fabrykująca model koloru RGB. Wyznaczanie modelu następuje na podstawie wartości
	 * numerycznej reprezentującej kolor modelu RGB
	 *
	 * @param rgbValue
	 *        Parametr wskazuje wartość koloru w modelu RGB
	 * @return Obiekt modelu koloru RGB
	 */
	public static RgbColorModel of(final int rgbValue) {

		return new RgbColorModel(
			rgbValue);
	}

	/**
	 * Metoda fabrykująca model koloru RGB. Wyznaczanie modelu następuje na podstawie wartości
	 * składowych czerwonej,zielonej i niebieskiej przedstawionej w postlic liczby z zakresu 0..255
	 *
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 * @return Obiekt modelu koloru RGB
	 */
	public static RgbColorModel of(final int redValue, final int greenValue, final int blueValue) {

		return new RgbColorModel(
			redValue, greenValue, blueValue);
	}

	/**
	 * Metoda fabrykująca model koloru RGB. Wyznaczanie modelu następuje na podstawie wartości
	 * heksadecymalnej zapisanej jako ciąg znaków
	 *
	 * @param hexColorValue
	 *        Wartość heksadecymalnazapisana w postaci ciągu znaków
	 * @return Obiekt modelu koloru RGB
	 */
	public static RgbColorModel of(final String hexColorValue) {

		return new RgbColorModel(
			hexColorValue);
	}

	/**
	 * Stała klasy wskazuje maskę maksymalnej wartości składowej barwy modelu RGB
	 */
	private static final int RGB_MASK_VALUE = 0xFF;

	/**
	 * Stała klasy wskazuje wskaznik przesunięcia o dwa bajty
	 */
	private static final int SHIFT_BY_2BYTES = 16;

	/**
	 * Stała klasy wskazuje wskaznik przesunięcia o jeden bajt
	 */
	private static final int SHIFT_BY_1BYTES = 8;

	/**
	 * Atrybut obiketu klasy wskazuje na wartość numeryczną koloru w modelu RGB
	 */
	private int rgbColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbColorModel</code>
	 *
	 * @param rgbValue
	 *        Parametr określa wartość koloru w modelu RGB.
	 */
	private RgbColorModel(final int rgbValue) {

		this((rgbValue >> RgbColorModel.SHIFT_BY_2BYTES) & RgbColorModel.RGB_MASK_VALUE,
				(rgbValue >> RgbColorModel.SHIFT_BY_1BYTES) & RgbColorModel.RGB_MASK_VALUE,
				rgbValue & RgbColorModel.RGB_MASK_VALUE);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbColorModel</code>
	 *
	 * @param hexColorValue
	 *        Parametr określa wartość koloru w notacji RGB. Wartość przekazana w postaci hexadecymalnej
	 */
	private RgbColorModel(final String hexColorValue) {
		this(Integer.decode("#" + hexColorValue));
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbColorModel</code>
	 *
	 * @param redValue
	 *        Parametr określa wartość barwy czerwonej, składowej modelu RGB
	 * @param greenValue
	 *        Parametr określa wartość zielonej, składowej modelu RGB
	 * @param blueValue
	 *        Parametr określa wartość niebieskiej składowej modelu RGB
	 */
	private RgbColorModel(final int redValue, final int greenValue, final int blueValue) {

		this.rgbColorValue = blueValue;
		this.rgbColorValue |= greenValue << RgbColorModel.SHIFT_BY_1BYTES;
		this.rgbColorValue |= redValue << RgbColorModel.SHIFT_BY_2BYTES;
	}

	/**
	 * Właściwość określa składową barwy czerwonej modelu RGB
	 *
	 * @return Wartość numeryczna składowej modelu RGB(0..255)
	 */
	public int getRedValue() {

		return (this.rgbColorValue >> RgbColorModel.SHIFT_BY_2BYTES) & RgbColorModel.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa składową barwy zielonej modelu RGB
	 *
	 * @return Wartość numeryczna składowej modelu RGB(0..255)
	 */
	public int getGreenValue() {

		return (this.rgbColorValue >> RgbColorModel.SHIFT_BY_1BYTES) & RgbColorModel.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa składową barwy niebieskiej modelu RGB
	 *
	 * @return Wartość numeryczna składowej modelu RGB(0..255)
	 */
	public int getBlueValue() {

		return this.rgbColorValue & RgbColorModel.RGB_MASK_VALUE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.rgbColorValue);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof RgbColorModel))
			return false;

		final RgbColorModel other = (RgbColorModel) obj;
		return this.rgbColorValue == other.rgbColorValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		final String format = "RGB = (%d, %d, %d)";

		return String.format(format, getRedValue(), getGreenValue(), getBlueValue());
	}
}

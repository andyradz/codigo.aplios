package com.codigo.aplios.sdk.core.helpers.color;

import java.util.Objects;

/**
 * Klasa reprezentuje mechanizm zamiany wartości koloru RGB do postaci szesnastkowej
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category converter
 *
 */
final class RgbToHexColorConverter {

	/**
	 * Atrybut obiektu wskazuje na wartość koloru RGB zapisanej do wartości szesnastkowej
	 */
	private int hexColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RgbToHexColorConverter</code>
	 *
	 * @param redValue
	 *        Parametr określa wartość składowej barwy czerwonej koloru w systemie RGB
	 * @param greenValue
	 *        Parametr określa wartość składowej barwy zielonej koloru w systemie RGB
	 * @param blueValue
	 *        Parametr określa wartość składowej barwy niebieskiej koloru w systemie RGB
	 */
	public RgbToHexColorConverter(final int redValue, final int greenValue, final int blueValue) {

		if ((redValue < 0) && (redValue > 255))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > 255))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > 255))
			throw new IllegalArgumentException();

		this.hexColorValue = blueValue;
		this.hexColorValue |= greenValue << 8;
		this.hexColorValue |= redValue << 16;
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>RgbToHexColorConverter</code>
	 *
	 * @param rgbValue
	 *        Parametr określa wartość koloru w systemie RGB
	 */
	public RgbToHexColorConverter(final int rgbValue) {

		if ((rgbValue < 0) && (rgbValue > 255))
			throw new IllegalArgumentException();

		this.hexColorValue = rgbValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.hexColorValue);
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

		if (!(obj instanceof RgbToHexColorConverter))
			return false;

		final RgbToHexColorConverter other = (RgbToHexColorConverter) obj;
		return this.hexColorValue == other.hexColorValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return Integer.toHexString(this.hexColorValue)
				.toUpperCase();
	}
}
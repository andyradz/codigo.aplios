package com.codigo.aplios.sdk.color;

import java.util.Objects;

final class HexToRbgColorConverter {

	public static HexToRbgColorConverter of(String hexColorValue) {

		return new HexToRbgColorConverter(hexColorValue);
	}

	private static final int RGB_MASK_VALUE = 0xFF;
	private static final int HEX_VALUE_LENGTH = 6;

	/**
	 * Atrybut obiektu zawiera wartość reprezentująca kolor w systemie RGB
	 */
	private final int rgbColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RGBToHexColorConverter</code>
	 * 
	 * @param hexColorValue
	 *            Parametr wskazuje na wartość tekstową reprezentującą kolor w
	 *            układzie RGB zapisanym w formacie szesnastkowym
	 */
	private HexToRbgColorConverter(String hexColorValue) {

		Objects.requireNonNull(hexColorValue);

		if (hexColorValue.length() != HEX_VALUE_LENGTH)
			throw new IllegalArgumentException();

		this.rgbColorValue = Integer.decode("#" + hexColorValue);
	}

	/**
	 * Właściwość określa wartość barwy czerwonej kodu RGB
	 * 
	 * @return Wartość numeryczna 0-255
	 */
	public int getRedValue() {

		return (rgbColorValue >> 16) & RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa wartość barwy zielonej kodu RGB
	 * 
	 * @return Wartość numeryczna 0-255
	 */
	public int getGreenValue() {

		return (rgbColorValue >> 8) & RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa wartość barwy niebieskiej kodu RGB
	 * 
	 * @return Wartość numeryczna 0-255
	 */
	public int getBlueValue() {

		return (rgbColorValue) & RGB_MASK_VALUE;
	}

	@Override
	public String toString() {

		final String format = "RGB = (%d, %d, %d)";

		return String.format(format, getRedValue(), getGreenValue(), getBlueValue());
	}
}
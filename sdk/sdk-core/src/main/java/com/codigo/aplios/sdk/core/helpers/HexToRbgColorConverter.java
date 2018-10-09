package com.codigo.aplios.sdk.core.helpers;

import java.util.Objects;

final class HexToRbgColorConverter {

	public static HexToRbgColorConverter of(final String hexColorValue) {

		return new HexToRbgColorConverter(
			hexColorValue);
	}

	private static final int	RGB_MASK_VALUE		= 0xFF;
	private static final int	HEX_VALUE_LENGTH	= 6;

	/**
	 * Atrybut obiektu zawiera wartość reprezentująca kolor w systemie RGB
	 */
	private final int rgbColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RGBToHexColorConverter</code>
	 *
	 * @param hexColorValue
	 *        Parametr wskazuje na wartość tekstową reprezentującą kolor w układzie RGB zapisanym w
	 *        formacie szesnastkowym
	 */
	private HexToRbgColorConverter(final String hexColorValue) {

		Objects.requireNonNull(hexColorValue);

		if (hexColorValue.length() != HexToRbgColorConverter.HEX_VALUE_LENGTH)
			throw new IllegalArgumentException();

		this.rgbColorValue = Integer.decode("#" + hexColorValue);
	}

	/**
	 * Właściwość określa wartość barwy czerwonej kodu RGB
	 *
	 * @return Wartość numeryczna 0-255
	 */
	public int getRedValue() {

		return (this.rgbColorValue >> 16) & HexToRbgColorConverter.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa wartość barwy zielonej kodu RGB
	 *
	 * @return Wartość numeryczna 0-255
	 */
	public int getGreenValue() {

		return (this.rgbColorValue >> 8) & HexToRbgColorConverter.RGB_MASK_VALUE;
	}

	/**
	 * Właściwość określa wartość barwy niebieskiej kodu RGB
	 *
	 * @return Wartość numeryczna 0-255
	 */
	public int getBlueValue() {

		return (this.rgbColorValue) & HexToRbgColorConverter.RGB_MASK_VALUE;
	}

	@Override
	public String toString() {

		final String format = "RGB = (%d, %d, %d)";

		return String.format(format, getRedValue(), getGreenValue(), getBlueValue());
	}
}
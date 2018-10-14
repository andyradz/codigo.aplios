package com.codigo.aplios.sdk.core.helpers;

import java.util.Objects;

final class HexToRbgColorConverter {

	public static HexToRbgColorConverter of(final String hexColorValue) {

		return new HexToRbgColorConverter(
			hexColorValue);
	}

	private static final int HEX_VALUE_LENGTH = 6;

	/**
	 * Atrybut obiektu zawiera wartość reprezentująca kolor w systemie RGB
	 */
	private final RgbColorModel rgbColorModel;

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

		this.rgbColorModel = RgbColorModel.of(hexColorValue);
	}

	/**
	 * Właściwość określa wartość barwy czerwonej kodu RGB
	 *
	 * @return Wartość numeryczna 0-255
	 */
	public RgbColorModel getRgbColorModel() {

		return this.rgbColorModel;
	}

	@Override
	public String toString() {

		return this.rgbColorModel.toString();
	}
}
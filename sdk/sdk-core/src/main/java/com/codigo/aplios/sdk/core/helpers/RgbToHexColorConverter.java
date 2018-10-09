package com.codigo.aplios.sdk.core.helpers;

final class RgbToHexColorConverter {

	private int hexColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RgbToHexColorConverter</code>
	 *
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
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

	public RgbToHexColorConverter(final int rgbValue) {

		this.hexColorValue = rgbValue;
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
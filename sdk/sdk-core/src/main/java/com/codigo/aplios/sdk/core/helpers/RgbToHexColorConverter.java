package com.codigo.aplios.sdk.color;

final class RgbToHexColorConverter {

	private int hexColorValue;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>RgbToHexColorConverter</code>
	 * 
	 * @param redValue
	 * @param greenValue
	 * @param blueValue
	 */
	public RgbToHexColorConverter(int redValue, int greenValue, int blueValue) {

		if ((redValue < 0) && (redValue > 255))
			throw new IllegalArgumentException();

		if ((greenValue < 0) && (greenValue > 255))
			throw new IllegalArgumentException();

		if ((blueValue < 0) && (blueValue > 255))
			throw new IllegalArgumentException();

		hexColorValue = blueValue;
		hexColorValue |= greenValue << 8;
		hexColorValue |= redValue << 16;
	}

	public RgbToHexColorConverter(int rgbValue) {

		hexColorValue = rgbValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return Integer.toHexString(hexColorValue)
				.toUpperCase();
	}
}
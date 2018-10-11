package com.codigo.aplios.sdk.color;

public final class RgbColorModel {

	public static RgbColorModel of(int rgbValue) {

		return new RgbColorModel(rgbValue);
	}

	public static RgbColorModel of(int redValue, int greenValue, int blueValue) {

		return new RgbColorModel(redValue, greenValue, blueValue);
	}

	public static RgbColorModel of(String hexColorValue) {

		return new RgbColorModel(hexColorValue);
	}

	private static final int RGB_MASK_VALUE = 0xFF;
	private static final int SHIFT_BY_2BYTES = 16;
	private static final int SHIFT_BY_1BYTES = 8;

	private int rgbColorValue;

	private RgbColorModel(int rgbValue) {

		this((rgbValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE, (rgbValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE,
				rgbValue & RGB_MASK_VALUE);
	}

	private RgbColorModel(String hexColorValue) {
		this(Integer.decode("#" + hexColorValue));
	}

	private RgbColorModel(int redValue, int greenValue, int blueValue) {

		rgbColorValue = blueValue;
		rgbColorValue |= greenValue << SHIFT_BY_1BYTES;
		rgbColorValue |= redValue << SHIFT_BY_2BYTES;
	}

	public int getRedValue() {

		return (rgbColorValue >> SHIFT_BY_2BYTES) & RGB_MASK_VALUE;
	}

	public int getGreenValue() {

		return (rgbColorValue >> SHIFT_BY_1BYTES) & RGB_MASK_VALUE;
	}

	public int getBlueValue() {

		return rgbColorValue & RGB_MASK_VALUE;
	}

	@Override
	public String toString() {

		final String format = "RGB = (%d, %d, %d)";

		return String.format(format, getRedValue(), getGreenValue(), getBlueValue());
	}
}

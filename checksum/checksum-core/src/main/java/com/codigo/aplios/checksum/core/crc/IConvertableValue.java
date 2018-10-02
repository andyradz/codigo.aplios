package com.codigo.aplios.checksum.core.crc;

//http://www.algorytm.org/algorytmy-arytmetyczne/zamiana-z-i-na-system-dziesietny.html

@FunctionalInterface
public interface IConvertableValue<V extends Number> {

	public static void main(final String[] args) {
		final String value = BinaryConvertValueFactory.instance()
				// new BinaryFormatValue(
				// 8))
				.convert(-10L);

		System.out.println(value);
	}

	String convert(V value);
}

@FunctionalInterface
interface IFormatableValue<V> {
	String format(V value);
}

class BinaryConvertValueFactory {
	public static IConvertableValue<Long> instance() {
		return new BinaryConvertValue()::convert;
	}

	public static IConvertableValue<Long> instance(final IFormatableValue<String> format) {
		return new BinaryConvertValue(
			format)::convert;
	}
}

class BinaryFormatValue implements IFormatableValue<String> {

	private final StringBuilder	formatValue;
	private final int			numberOfGroups;

	public BinaryFormatValue(final int numberOfGroups) {
		this.formatValue = new StringBuilder();
		this.numberOfGroups = numberOfGroups;
	}

	@Override
	public String format(final String value) {

		for (int charIndex = value.length() - 1; charIndex >= 0; --charIndex) {
			final char charAt = value.charAt(charIndex);
			this.formatValue.append(charAt);
			if ((charIndex != 0) && (0 == (charIndex % this.numberOfGroups)))
				this.formatValue.append(" ");

		}

		return this.formatValue.reverse()
				.toString();
	}
}

final class BinaryConvertValue implements IConvertableValue<Long> {

	private final StringBuilder			convertValue;
	private IFormatableValue<String>	formatValue;

	public BinaryConvertValue() {
		this.convertValue = new StringBuilder();
		this.formatValue = (value) -> value;
	}

	public BinaryConvertValue(final IFormatableValue<String> format) {
		this();
		this.formatValue = format;
	}

	@Override
	public String convert(final Long value) {
		this.convertValue.setLength(0);
		calculate(value);
		return this.formatValue.format(this.convertValue.toString());
	}

	private void calculate(final Long value) {
		String bitValue = "";

		for (int bitNumber = Long.SIZE - 1; bitNumber >= 0; --bitNumber) {
			// TODO: w zlaeżnosssic od znaku wartości odwrotna kolejnisc
			bitValue = (1 == ((value >> bitNumber) & 1)) ? "1" : "0";
			this.convertValue.append(bitValue);
		}
	}
}

class HexaadecimalFormatValue implements IConvertableValue<Long> {

	@Override
	public String convert(final Long value) {

		return null;
	}

}

package com.codigo.aplios.checksum.core.crc;

import java.util.zip.Checksum;

/*
 * Known CRC algorihtms
 */
// http://www.sunshine2k.de/coding/javascript/crc/crc_js.html
// TODO: zrobić to na podstawie template metod albo parametrów
public abstract class AbstractCrc implements Checksum {

	public static void main(final String[] args) {

		AbstractCrc.longToBinary(Byte.MAX_VALUE, 8);
	}

	protected int[] lookupTable;

	// -----------------------------------------------------------------------------------------------------------------
	protected ICrcParameter crcParameters;

	// -----------------------------------------------------------------------------------------------------------------
	protected int value;

	// -----------------------------------------------------------------------------------------------------------------
	public AbstractCrc(final ICrcParametrizable parametrized) {

		this.lookupTable = new int[256];
		this.crcParameters = parametrized.getParameters();
		buildLookupTable();
	}

	public final int[] getLookupTable() {

		return this.lookupTable;
		// return new Iterator<Integer>() {
		//
		// private int index = 0;
		//
		// @Override
		// public boolean hasNext() {
		// return this.index < AbstractCrc.this.lookupTable.length;
		// }
		//
		// @Override
		// public Integer next() {
		// return AbstractCrc.this.lookupTable[this.index++];
		// }
		// };

	}

	// -----------------------------------------------------------------------------------------------------------------
	private void buildLookupTable() {

		final int msbMask = 0x01 << (this.crcParameters.getLength() - 1), intShift = evalBitShift();

		for (int divident = 0; divident < 256; divident++) {
			int currByte = (divident << (this.crcParameters.getLength() - 8)) & intShift;
			for (int bit = 0; bit < 8; bit++)
				if (0 != (currByte & msbMask)) {
					currByte <<= 1;
					currByte ^= this.crcParameters.getPolynomial();
				}
				else
					currByte <<= 1;
			this.lookupTable[divident] = currByte & intShift;
			final String result = String.format("0x%08x", this.lookupTable[divident])
					.toUpperCase();
			if (0 == (divident % 8))
				System.out.println();

			System.out.print(result + " ");
		}
		System.out.println();
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void update(final byte[] buffer) {

		this.update(buffer, 0, buffer.length);
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void update(final int b) {

		this.update(new byte[] { (byte) b }, 0, 1);
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void update(final byte[] arg0, final int arg1, final int arg2) {

		int crc = this.crcParameters.getInitialValue();
		for (final byte element : arg0) {

			int curByte = element & 0xFF;

			if (this.crcParameters.isInputReflected())
				curByte = (int) AbstractCrc.reverseBits(curByte, this.crcParameters.getLength());

			/*
			 * update the MSB of crc value with next input byte
			 */
			crc = (crc ^ (curByte << (this.crcParameters.getLength() - 8))) & evalBitShift();
			/*
			 * this MSB byte value is the index into the lookup table
			 */
			final int pos = (crc >> (this.crcParameters.getLength() - 8)) & 0xFF;
			/*
			 * shift out this index
			 */
			crc = (crc << 8) & evalBitShift();
			/*
			 * XOR-in remainder from lookup table using the calculated index
			 */
			crc = (crc ^ this.lookupTable[pos]) & evalBitShift();
		}

		this.value = (crc ^ this.crcParameters.getFinalXorValue()) & evalBitShift();
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public long getValue() {

		return this.value;
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void reset() {

		this.value = this.crcParameters.getInitialValue();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private int evalBitShift() {

		// TODO: dodać jako element klasy tą maskę
		switch (this.crcParameters.getLength()) {
		case 0x8:
			return 0xFF;

		case 0x10:
			return 0xFFFF;

		case 0x20:
			return 0xFFFFFFFF;

		default:
			return 0x00;
		}
	}

	/**
	 * Metoda wykonuje zamianę pozycji poszczególnych bitów w wartości <code>value</code>. Zamiana jest
	 * wykonywana na sposób "odbicia lustrzanego". Najstarsze pozycje bitów są zamieniane z najmłodszymi
	 * pozycjami bitów. Patrz na przykład poniżej: <code>1011(2)->11(10) => 1101(2)->13(10)</code>
	 *
	 * @param value
	 *        Liczba dla której mamy wykonać odbicie lustrzane bitów
	 * @param bitCount
	 *        Ilość bitów podlegająca zamianie
	 * @return Wartość numeryczna
	 */
	// TODO: przenieść funkcję do dykowanej klasy obsługi np. NumberRoutines
	private static long reverseBits(final long value, final int bitCount) {

		long reflection = 0L;
		for (int bitNumber = 0; bitNumber < bitCount; ++bitNumber)
			if (0L != (value & (1L << bitNumber)))
				reflection |= (1L << (bitCount - 1 - bitNumber)) & 0xFF;

		return reflection;
	}

	/**
	 * Metoda wykonuje transformację wartości liczbowej na jej reprezentacje w systemie dwójkowym
	 *
	 * @param number
	 *        Wartość liczbowa
	 * @param numberOfGroups
	 *        Ilość grup reprezentacji wartości binarnej
	 * @return Wartość liczbowa przedstawiona w systemie binarnym (dwójkowym)
	 */
	public static String longToBinary(final long number, final int numberOfGroups) {

		final StringBuilder bitsOutput = new StringBuilder();
		final int sizeOfLong = Long.SIZE - 1;

		for (int bitNumber = sizeOfLong; bitNumber >= 0; --bitNumber) {
			long val = (((number) & (1L << bitNumber)));
			val = val != 0L ? 1L : 0L;

			bitsOutput.append(val);

			if (0 == (bitNumber % numberOfGroups))
				bitsOutput.append(" ");
		}

		return bitsOutput.toString();
	}

}

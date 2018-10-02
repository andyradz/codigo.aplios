package com.codigo.aplios.checksum.core.crc;

/**
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category class
 */
final class CrcParameter implements ICrcParameter {

	/**
	 * Atrybut obiektu określa długość generowanej sumy kontrolnej wyrażonej w bitach
	 */
	private final int length;

	/**
	 * Atrybut obiektu określa nazwę dla sumy kontrolnej
	 */
	private final String name;

	/**
	 * Atrybut obiektu określa wartość wielomianu sumy kontrolnej
	 */
	private final int polynomial;

	/**
	 * Atrybut obiektu określa wartość początkową sumy kontrolnej
	 */
	private final int initialValue;

	/**
	 * Atrybut obiektu określa wartość finalną sumy kontrolnej
	 */
	private final int finalXorValue;

	/**
	 * Atrybut obiektu określa czy bajty danych wejściowe mają być odczytywane w odwrotnej kolejności
	 */
	private final boolean inputReflected;

	/**
	 * Atrybut obiektu określa czy bajty danych wyjściowych mają być odczytywane w odwrotnej kolejności
	 */
	private final boolean resultReflected;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>CrcParameter</code>
	 *
	 * @param length
	 *        Długość sumy kontrolnej wyrażona w bitach
	 * @param name
	 *        Nazwa dla sumy kontrolnej
	 * @param polynomial
	 *        Wartość wielomianu sumy kontrolnej
	 * @param initialValue
	 *        Wartość początkowa sumy kontrolnej
	 * @param finalXorValue
	 *        Wartość finalna operatora xor sumy kontrolnej
	 * @param inputReflected
	 *        Flaga wskazuje czy bajty danych wejściowych odczytywać w odwrotnej kolejności
	 * @param resultReflected
	 *        Flaga wskazuje czy bajty danych wyjściowych odczytywać w odwrotnej kolejności
	 */
	public CrcParameter(final int length, final String name, final int polynomial, final int initialValue,
			final int finalXorValue, final boolean inputReflected, final boolean resultReflected) {

		this.length = length;
		this.name = name;
		this.polynomial = polynomial;
		this.initialValue = initialValue;
		this.finalXorValue = finalXorValue;
		this.inputReflected = inputReflected;
		this.resultReflected = resultReflected;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#getLength()
	 */
	@Override
	public int getLength() {

		return this.length;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#getName()
	 */
	@Override
	public String getName() {

		return this.name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#getPolynomial()
	 */
	@Override
	public int getPolynomial() {

		return this.polynomial;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#getInitialValue()
	 */
	@Override
	public int getInitialValue() {

		return this.initialValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#getFinalXorValue()
	 */
	@Override
	public int getFinalXorValue() {

		return this.finalXorValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#isInputReflected()
	 */
	@Override
	public boolean isInputReflected() {

		return this.inputReflected;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParameter#isResultReflected()
	 */
	@Override
	public boolean isResultReflected() {

		return this.resultReflected;
	}

}

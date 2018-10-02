package com.codigo.aplios.checksum.core.crc;

/**
 * Interfejs deklaruje kontrakt definujący parametry dla wyznaczania sumy kontrolnej przez algorytm
 * CRC.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category interface
 */
interface ICrcParameter {

	/**
	 * Właściwość wskauje długość algorytmu sumy kontrolnej wyrażonej w bitach
	 *
	 * @return Długość sumy kontrolnej, wartość numeryczna
	 */
	int getLength();

	/**
	 * Właściwość określa nazwę algorytmu sumy kontrolnej
	 *
	 * @return Nazwa sumy kontrolnej, ciąg znaków
	 */
	String getName();

	/**
	 * Właściwość określa wartość wielomianu sumy kontrolnej
	 *
	 * @return Wielomian sumy kontrolnej, wartość numeryczna
	 */
	int getPolynomial();

	/**
	 * Właściwość określa wartość początkową sumy kontrolnej
	 *
	 * @return Wartość początkowa sumy kontrolnej, wartość numeryczna
	 */
	int getInitialValue();

	/**
	 * Właściwość określa wartość końcową dla operatora xor sumy kontrolnej
	 *
	 * @return Wartość końcowa xor, wartość numeryczna
	 */
	int getFinalXorValue();

	/**
	 * Właściwość wskazuję flagę, króra decyduje czy blok bajtów wejściowych ma być czytany w odwrotnej
	 * kolejności
	 *
	 * @return Flaga odczytu danych wejściowych, wartość logiczna
	 */
	boolean isInputReflected();

	/**
	 * Właściwość wskazuję flagę, króra decyduje czy blok bajtów wyjściowych ma być czytany w odwrotnej
	 * kolejności
	 *
	 * @return Flaga odczytu danych wyjściowych, wartość logiczna
	 */
	boolean isResultReflected();

}

package com.codigo.aplios.checksum.core.crc;

/**
 * Interfejs deklaruje kontrakt definujący metodę dostarczającą parametry do wyznaczenia sumy
 * kontrolnej CRC.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category interface
 */
interface ICrcParametrizable {

	/**
	 * Właściwość określa wartości atrybutów parametru sumy kontrolnej
	 *
	 * @return Wartośći atrybutów parametru CRC, referencja do obiektu
	 */
	ICrcParameter getParameters();
}
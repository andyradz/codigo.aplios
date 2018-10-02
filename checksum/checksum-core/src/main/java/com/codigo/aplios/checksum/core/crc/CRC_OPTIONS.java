package com.codigo.aplios.checksum.core.crc;

import java.util.EnumSet;
import java.util.Set;

/**
 * Typ wyliczeniowy określa zbiór znaczników dostępnych wariantów wykonania sumy kontrolnej CRC.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category enumeration
 */
enum CRC_OPTIONS {

	/**
	 * Atrybut obiektu zawiera zbiór wariantów wykonania sumy kontrolnej CRC8
	 */
	CRC8(EnumSet.allOf(CRC8_OPTIONS.class)),

	/**
	 * Atrybut obiektu zawiera zbiór wariantów wykonania sumy kontrolnej CRC16
	 */
	CRC16(EnumSet.allOf(CRC16_OPTIONS.class)),

	/**
	 * Atrybut obiektu zawiera zbiór wariantów wykonania sumy kontrolnej CRC32
	 */
	CRC32(EnumSet.allOf(CRC32_OPTIONS.class));

	/**
	 * Atrybut obiekty zawiera zbiór wariantów wykonania sumy kontrolnej
	 */
	private final Set<? extends ICrcParametrizable> crcOptions;

	/**
	 * Podstawowy konstruktor obiektu klasy<code>CRC_OPTIONS</code>
	 *
	 * @param crcOptions
	 *        Zbiór wariantów wykonania sumy kontrolnej
	 */
	private CRC_OPTIONS(final EnumSet<? extends ICrcParametrizable> crcOptions) {

		this.crcOptions = crcOptions;
	}

	/**
	 * Własciwość zawiera zbiór wariantów wykonania dla konkretnej sumy kontrolnej CRC. Konkretna suma
	 * kontrolna pogrupowana jest według długości algorytmu.
	 *
	 * @return Zbiór wariantów sumy kontrolnej CRC
	 */
	public Set<? extends ICrcParametrizable> getOptions() {

		return this.crcOptions;
	}
}

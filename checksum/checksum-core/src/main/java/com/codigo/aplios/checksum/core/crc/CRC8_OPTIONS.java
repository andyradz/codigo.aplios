package com.codigo.aplios.checksum.core.crc;

import java.util.EnumMap;

//https://github.com/meetanthony/crccsharp/blob/master/CrcStdParams.cs

/**
 * Typ wyliczeniowy określa znaczniki dla dostępnych wariantów wykonania sumy kontrolnej CRC8.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category enumeration
 *
 */
public enum CRC8_OPTIONS implements ICrcParametrizable {
	CRC8,
	CRC8_SAE_J1850,
	CRC8_SAE_J1850_ZERO,
	CRC8_8H2F,
	CRC8_CDMA2000,
	CRC8_DARC,
	CRC8_DVB_S2,
	CRC8_EBU,
	CRC8_ICODE,
	CRC8_ITU,
	CRC8_MAXIM,
	CRC8_ROHC,
	CRC8_WCDMA;

	/**
	 * Atrybut obiektu określa kolekcję obiektów typu <code>ICrcParameter</code> gdzie kluczem jest typ
	 * <code>CRC8_OPTIONS</code>
	 */
	private static final EnumMap<CRC8_OPTIONS, ICrcParameter> crcOptions;

	/**
	 * Statyczny inicjalizator klasy <code>CRC8_MODE</code>
	 */
	static {
		crcOptions = new EnumMap<>(
			CRC8_OPTIONS.class);

		// ...CRC8
		CRC8_OPTIONS.crcOptions.put(CRC8, new CrcParameter(
			8, "CRC8", 0x07, 0x00, 0x00, false, false));

		// ...CRC8_SAE_J1850
		CRC8_OPTIONS.crcOptions.put(CRC8_SAE_J1850, new CrcParameter(
			8, "CRC8_SAE_J1850", 0x1D, 0xFF, 0xFF, false, false));

		// ...CRC8_SAE_J1850_ZERO
		CRC8_OPTIONS.crcOptions.put(CRC8_SAE_J1850_ZERO, new CrcParameter(
			8, "CRC8_SAE_J1850_ZERO", 0x1D, 0x00, 0x00, false, false));

		// ...CRC8_8H2F
		CRC8_OPTIONS.crcOptions.put(CRC8_8H2F, new CrcParameter(
			8, "CRC8_8H2F", 0x2F, 0xFF, 0xFF, false, false));

		// ... CRC8_CDMA2000
		CRC8_OPTIONS.crcOptions.put(CRC8_CDMA2000, new CrcParameter(
			8, "CRC8_CDMA2000", 0x9B, 0xFF, 0x00, false, false));

		// ... CRC8_DARC
		CRC8_OPTIONS.crcOptions.put(CRC8_DARC, new CrcParameter(
			8, "CRC8_DARC", 0x39, 0x00, 0x00, true, true));

		// ...CRC8_DVB_S2
		CRC8_OPTIONS.crcOptions.put(CRC8_DVB_S2, new CrcParameter(
			8, "CRC8_DVB_S2", 0xD5, 0x00, 0x00, false, false));

		// ... CRC8_EBU
		CRC8_OPTIONS.crcOptions.put(CRC8_EBU, new CrcParameter(
			8, "CRC8_EBU", 0x1D, 0xFF, 0x00, true, true));

		// ...CRC8_ICODE
		CRC8_OPTIONS.crcOptions.put(CRC8_ICODE, new CrcParameter(
			8, "CRC8_ICODE", 0x1D, 0xFD, 0x00, false, false));

		// ... CRC8_ITU
		CRC8_OPTIONS.crcOptions.put(CRC8_ITU, new CrcParameter(
			8, "CRC8_ITU", 0x07, 0x00, 0x55, false, false));

		// ... CRC8_MAXIM
		CRC8_OPTIONS.crcOptions.put(CRC8_MAXIM, new CrcParameter(
			8, "CRC8_MAXIM", 0x31, 0x00, 0x00, true, true));

		// ...CRC8_ROHC
		CRC8_OPTIONS.crcOptions.put(CRC8_ROHC, new CrcParameter(
			8, "CRC8_ROHC", 0x07, 0xFF, 0x00, true, true));

		// ...CRC8_WCDMA
		CRC8_OPTIONS.crcOptions.put(CRC8_WCDMA, new CrcParameter(
			8, "CRC8_WCDMA", 0x9B, 0x00, 0x00, true, true));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParametrizable#getParameters()
	 */
	@Override
	public ICrcParameter getParameters() {

		return CRC8_OPTIONS.crcOptions.get(this);
	}
}

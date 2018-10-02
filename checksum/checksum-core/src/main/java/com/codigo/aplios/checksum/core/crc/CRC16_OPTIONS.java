package com.codigo.aplios.checksum.core.crc;

import java.util.EnumMap;

/**
 * Typ wyliczeniowy określa znaczniki dla dostępnych wariantów wykonania sumy kontrolnej CRC16.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category enumeration
 */
enum CRC16_OPTIONS implements ICrcParametrizable {
	CRC16_CCIT_ZERO,
	CRC16_ARC,
	CRC16_AUG_CCITT,
	CRC16_BUYPASS,
	CRC16_CCITT_FALSE,
	CRC16_CDMA2000,
	CRC16_DDS_110,
	CRC16_DECT_R,
	CRC16_DECT_X,
	CRC16_DNP,
	CRC16_EN_13757,
	CRC16_GENIBUS,
	CRC16_MAXIM,
	CRC16_MCRF4XX,
	CRC16_RIELLO,
	CRC16_T10_DIF,
	CRC16_TELEDISK,
	CRC16_TMS37157,
	CRC16_USB,
	CRC16_A,
	CRC16_KERMIT,
	CRC16_MODBUS,
	CRC16_X_25,
	CRC16_XMODEM;

	/**
	 * Atrybut obiektu określa kolekcję obiektów typu <code>ICrcParameter</code> gdzie kluczem jest typ
	 * <code>CRC16_OPTIONS</code>
	 */
	private static final EnumMap<CRC16_OPTIONS, CrcParameter> crcOptions;

	/**
	 * Statyczny inicjalizator klasy <code>CRC16_OPTIONS</code>
	 */
	static {
		crcOptions = new EnumMap<>(
			CRC16_OPTIONS.class);

		// ...CRC16_CCIT_ZERO
		CRC16_OPTIONS.crcOptions.put(
				CRC16_CCIT_ZERO,
				new CrcParameter(
					16, "CRC16_CCIT_ZERO", 0x1021, 0x0000, 0x0000, false, false));

		// ...CRC16_ARC
		CRC16_OPTIONS.crcOptions.put(
				CRC16_ARC,
				new CrcParameter(
					16, "CRC16_ARC", 0x8005, 0x0000, 0x0000, true, true));

		// ... CRC16_AUG_CCITT
		CRC16_OPTIONS.crcOptions.put(
				CRC16_AUG_CCITT,
				new CrcParameter(
					16, "CRC16_AUG_CCITT", 0x1021, 0x1D0F, 0x0000, false, false));

		// ...CRC16_BUYPASS
		CRC16_OPTIONS.crcOptions.put(
				CRC16_BUYPASS,
				new CrcParameter(
					16, "CRC16_BUYPASS", 0x8005, 0x0000, 0x0000, false, false));

		// ...CRC16_CCITT_FALSE
		CRC16_OPTIONS.crcOptions.put(
				CRC16_CCITT_FALSE,
				new CrcParameter(
					16, "CRC16_CCITT_FALSE", 0x1021, 0xFFFF, 0x0000, false, false));

		// ... CRC16_CDMA2000
		CRC16_OPTIONS.crcOptions.put(
				CRC16_CDMA2000,
				new CrcParameter(
					16, "CRC16_CDMA2000", 0xC867, 0xFFFF, 0x0000, false, false));

		// ... CRC16_DDS_110
		CRC16_OPTIONS.crcOptions.put(
				CRC16_DDS_110,
				new CrcParameter(
					16, "CRC16_DDS_110", 0x8005, 0x800D, 0x0000, false, false));

		// ...CRC16_DECT_R
		CRC16_OPTIONS.crcOptions.put(
				CRC16_DECT_R,
				new CrcParameter(
					16, "CRC16_DECT_R", 0x0589, 0x0000, 0x0001, false, false));

		// ...CRC16_DECT_X
		CRC16_OPTIONS.crcOptions.put(
				CRC16_DECT_X,
				new CrcParameter(
					16, "CRC16_DECT_X", 0x0589, 0x0000, 0x0000, false, false));

		// ...CRC16_DNP
		CRC16_OPTIONS.crcOptions.put(
				CRC16_DNP,
				new CrcParameter(
					16, "CRC16_DNP", 0x3D65, 0x0000, 0xFFFF, true, true));

		// ... CRC16_EN_13757
		CRC16_OPTIONS.crcOptions.put(
				CRC16_EN_13757,
				new CrcParameter(
					16, "CRC16_EN_13757", 0x3D65, 0x0000, 0xFFFF, false, false));

		// ... CRC16_GENIBUS
		CRC16_OPTIONS.crcOptions.put(
				CRC16_GENIBUS,
				new CrcParameter(
					16, "CRC16_GENIBUS", 0x1021, 0xFFFF, 0xFFFF, false, false));

		// ... CRC16_MAXIM
		CRC16_OPTIONS.crcOptions.put(
				CRC16_MAXIM,
				new CrcParameter(
					16, "CRC16_MAXIM", 0x8005, 0x0000, 0xFFFF, true, true));

		// ... CRC16_MCRF4XX
		CRC16_OPTIONS.crcOptions.put(
				CRC16_MCRF4XX,
				new CrcParameter(
					16, "CRC16_MCRF4XX", 0x1021, 0xFFFF, 0x0000, true, true));

		// ... CRC16_GENIBUS
		CRC16_OPTIONS.crcOptions.put(
				CRC16_GENIBUS,
				new CrcParameter(
					16, "CRC16_RIELLO", 0x1021, 0xB2AA, 0x0000, true, true));

		// ... CRC16_RIELLO
		CRC16_OPTIONS.crcOptions.put(
				CRC16_RIELLO,
				new CrcParameter(
					16, "CRC16_T10_DIF", 0x8BB7, 0x0000, 0x0000, false, false));

		// ... CRC16_TELEDISK
		CRC16_OPTIONS.crcOptions.put(
				CRC16_TELEDISK,
				new CrcParameter(
					16, "CRC16_TELEDISK", 0xA097, 0x0000, 0x0000, false, false));

		// ... CRC16_TMS37157
		CRC16_OPTIONS.crcOptions.put(
				CRC16_TMS37157,
				new CrcParameter(
					16, "CRC16_TMS37157", 0x1021, 0x89EC, 0x0000, true, true));

		// ... CRC16_USB
		CRC16_OPTIONS.crcOptions.put(
				CRC16_USB,
				new CrcParameter(
					16, "CRC16_USB", 0x8005, 0xFFFF, 0xFFFF, true, true));

		// ... CRC16_A
		CRC16_OPTIONS.crcOptions.put(
				CRC16_A,
				new CrcParameter(
					16, "CRC16_A", 0x1021, 0xC6C6, 0x0000, true, true));

		// ... CRC16_KERMIT
		CRC16_OPTIONS.crcOptions.put(
				CRC16_KERMIT,
				new CrcParameter(
					16, "CRC16_KERMIT", 0x1021, 0x0000, 0x0000, true, true));

		// ... CRC16_MODBUS
		CRC16_OPTIONS.crcOptions.put(
				CRC16_MODBUS,
				new CrcParameter(
					16, "CRC16_MODBUS", 0x8005, 0xFFFF, 0x0000, true, true));

		// ... CRC16_X_25
		CRC16_OPTIONS.crcOptions.put(
				CRC16_X_25,
				new CrcParameter(
					16, "CRC16_X_25", 0x1021, 0xFFFF, 0xFFFF, true, true));

		// ... CRC16_XMODEM
		CRC16_OPTIONS.crcOptions.put(
				CRC16_XMODEM,
				new CrcParameter(
					16, "CRC16_XMODEM", 0x1021, 0x0000, 0x0000, false, false));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParametrizable#getParameters()
	 */
	@Override
	public ICrcParameter getParameters() {

		return CRC16_OPTIONS.crcOptions.get(this);
	}
}

package com.codigo.aplios.checksum.core.crc;

import java.util.EnumMap;

/**
 * Typ wyliczeniowy określa znaczniki dla dostępnych wariantów wykonania sumy kontrolnej CRC32.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category enumeration
 */
enum CRC32_OPTIONS implements ICrcParametrizable {
	CRC32,
	CRC32_BZIP2,
	CRC32_C,
	CRC32_D,
	CRC32_MPEG2,
	CRC32_POSIX,
	CRC32_Q,
	CRC32_JAMCRC,
	CRC32_XFER;

	/**
	 * Atrybut obiektu określa kolekcję obiektów typu <code>ICrcParameter</code> gdzie kluczem jest typ
	 * <code>CRC8_OPTIONS</code>
	 */
	private static final EnumMap<CRC32_OPTIONS, CrcParameter> crcOptions;

	/**
	 * Statyczny inicjalizator klasy <code>CRC32_OPTIONS</code>
	 */
	static {
		crcOptions = new EnumMap<>(
			CRC32_OPTIONS.class);

		// ...CRC32
		CRC32_OPTIONS.crcOptions.put(
				CRC32,
				new CrcParameter(
					32, "CRC32", 0x04C11DB7, 0xFFFFFFFF, 0xFFFFFFFF, true, true));

		// ...CRC32_BZIP2
		CRC32_OPTIONS.crcOptions.put(
				CRC32_BZIP2,
				new CrcParameter(
					32, "CRC32_BZIP2", 0x04C11DB7, 0xFFFFFFFF, 0xFFFFFFFF, false, false));

		// ...CRC32_C
		CRC32_OPTIONS.crcOptions.put(
				CRC32_C,
				new CrcParameter(
					32, "CRC32_C", 0x1EDC6F41, 0xFFFFFFFF, 0xFFFFFFFF, true, true));

		// ...CRC32_D
		CRC32_OPTIONS.crcOptions.put(
				CRC32_D,
				new CrcParameter(
					32, "CRC32_D", 0xA833982B, 0xFFFFFFFF, 0xFFFFFFFF, true, true));

		// ...CRC32_MPEG2
		CRC32_OPTIONS.crcOptions.put(
				CRC32_MPEG2,
				new CrcParameter(
					32, "CRC32_MPEG2", 0x04C11DB7, 0xFFFFFFFF, 0x00000000, false, false));

		// ...CRC32_POSIX
		CRC32_OPTIONS.crcOptions.put(
				CRC32_POSIX,
				new CrcParameter(
					32, "CRC32_POSIX", 0x04C11DB7, 0x00000000, 0xFFFFFFFF, false, false));

		// ...CRC32_Q
		CRC32_OPTIONS.crcOptions.put(
				CRC32_Q,
				new CrcParameter(
					32, "CRC32_Q", 0x814141AB, 0x00000000, 0x00000000, false, false));

		// ...CRC32_JAMCRC
		CRC32_OPTIONS.crcOptions.put(
				CRC32_JAMCRC,
				new CrcParameter(
					32, "CRC32_JAMCRC", 0x04C11DB7, 0xFFFFFFFF, 0x00000000, true, true));

		// ... CRC32_XFER
		CRC32_OPTIONS.crcOptions.put(
				CRC32_XFER,
				new CrcParameter(
					32, "CRC32_XFER", 0x000000AF, 0x00000000, 0x00000000, false, false));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.checksum.core.crc.ICrcParametrizable#getParameters()
	 */
	@Override
	public ICrcParameter getParameters() {

		return CRC32_OPTIONS.crcOptions.get(this);
	}
}

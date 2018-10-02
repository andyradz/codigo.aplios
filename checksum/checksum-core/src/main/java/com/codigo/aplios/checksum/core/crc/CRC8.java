package com.codigo.aplios.checksum.core.crc;

public final class CRC8 extends AbstractCrc {

	public CRC8(final ICrcParametrizable parameters) {
		// tu musimy sprawdzić czy typ pochodzi od CRC8
		super(parameters);
		if (!parameters.getClass()
				.equals(CRC8_OPTIONS.class))
			throw new IllegalArgumentException();
	}
}

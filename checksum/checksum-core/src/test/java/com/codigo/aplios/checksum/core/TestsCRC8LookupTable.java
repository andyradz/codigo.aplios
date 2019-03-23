package com.codigo.aplios.checksum.core;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.checksum.core.crc.CRC8;
import com.codigo.aplios.checksum.core.crc.CRC8_OPTIONS;


public class TestsCRC8LookupTable {

	private int[]	crcLookupTable;
	private CRC8	crc8;

	@BeforeEach
	public void initializeClass() {
		this.crc8 = new CRC8(
			CRC8_OPTIONS.CRC8);
		this.crcLookupTable = this.crc8.getLookupTable();

	}

	@Test
	public void testLookTableElementsCount() {

		// assert
		MatcherAssert.assertThat(this.crcLookupTable.length, CoreMatchers.equalTo(256));
	}

	@Test
	public void testLookTableElementAt1() {

		// assert
		MatcherAssert.assertThat(this.crcLookupTable[0], CoreMatchers.equalTo(0x00));
	}

	@Test
	public void testLookTableElementAt2() {

		// assert
		MatcherAssert.assertThat(this.crcLookupTable[1], CoreMatchers.equalTo(0x07));
	}

	@Test
	public void testLookTableElementAt3() {

		// assert
		MatcherAssert.assertThat(this.crcLookupTable[2], CoreMatchers.equalTo(0x0e));
	}

}

package com.codigo.aplios.sdk.core;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.helpers.EnumOperator;

/**
 * Klasa testuje metody pomocnicze helpera typu wyliczeniowego.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 */
// TODO: przerobi na hamercast
public class EnumHelperTests {

	private static enum Colors {

		WHITE,
		BLACK,
		BROWN,
		YELLOW,
		ORANGE,
		VIOLET,
		GREEN,
		PINK

	}

	private static enum Empty {
	}

	@Test
	public void sholudReturnEightEnumItems() {

		final List<Enum<?>> items = EnumOperator.getItems(Empty.class);
		Assertions.assertEquals(EnumHelperTests.Empty.values().length, items.size());
	}

	@Test
	public void shouldReturnEightEnumItemsName() {

		final List<String> enumItemsName = EnumOperator.getNames(Colors.class);
		Assertions.assertEquals(EnumHelperTests.Colors.values().length, enumItemsName.size());
	}

	@Test
	public void shouldReturnWhiteEnumItemName() {

		final String enumItemName = EnumOperator.getFirstName(Colors.class);
		Assertions.assertEquals("WHITE", enumItemName.toUpperCase());
	}

	@Test
	public void sholudReturnZeroEnumItems() {

		final List<Enum<?>> items = EnumOperator.getItems(Empty.class);
		Assertions.assertEquals(EnumHelperTests.Empty.values().length, items.size());
	}

	@Test
	public void shouldReturnPinkEnumItemName() {

		final String enumItemName = EnumOperator.getLastName(Colors.class);
		Assertions.assertEquals("PINK", enumItemName.toUpperCase());
	}

	@Test
	public void sholudReturnOrdinalSum() {

		final long ordinalSum = EnumOperator.getOrdinalSum(Colors.class);
		Assertions.assertEquals(28, ordinalSum);
	}

	@Test
	public void sholudReturnOrdinalSumOfEmpty() {

		final long ordinalSum = EnumOperator.getOrdinalSum(Empty.class);
		Assertions.assertEquals(0, ordinalSum);
	}

	@Test
	public void sholudReturnOrdinalAvg() {

		final double ordinalEvg = EnumOperator.getOrdinalAvg(Colors.class);
		Assertions.assertEquals(3.5, ordinalEvg);
	}

	@Test
	public void sholudReturnOrdinalAvgOfEmpty() {

		final double ordinalEvg = EnumOperator.getOrdinalAvg(Empty.class);
		Assertions.assertEquals(0.0, ordinalEvg);
	}

	@Test
	public void sholudReturnOrdinalMul() {

		final double ordinalMul = EnumOperator.getOrdinalMul(Colors.class);
		Assertions.assertEquals(5040.0, ordinalMul);
	}

	@Test
	public void sholudReturnOrdinalMulOfEmpty() {

		final double ordinalMul = EnumOperator.getOrdinalMul(Empty.class);
		Assertions.assertEquals(-1.0, ordinalMul);
	}

	@Test
	public void sholudReturnOrdinalFirstZero() {

		final int ordinalFirst = EnumOperator.getFirstOrdinal(Colors.class);
		Assertions.assertEquals(0, ordinalFirst);
	}

	@Test
	public void sholudReturnOrdinalLastSeven() {

		final int ordinalLast = EnumOperator.getLastOrdinal(Colors.class);
		Assertions.assertEquals(7, ordinalLast);
	}

	@Test
	public void sholudReturnOrdinalFirstEmptyOne() {

		final int ordinalFirst = EnumOperator.getFirstOrdinal(Empty.class);
		Assertions.assertEquals(-1, ordinalFirst);
	}

	@Test
	public void sholudReturnOrdinalLastEmptyOne() {

		final int ordinalLast = EnumOperator.getLastOrdinal(Empty.class);
		Assertions.assertEquals(-1, ordinalLast);
	}

	@Test
	public void sholudReturnLengthEight() {

		final long length = EnumOperator.getLength(Colors.class);
		Assertions.assertEquals(8, length);
	}

	@Test
	public void sholudReturnLengthEmptyZero() {

		final long length = EnumOperator.getLength(Empty.class);
		Assertions.assertEquals(0, length);
	}

}

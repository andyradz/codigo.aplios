package com.codigo.aplios.sdk.core.array;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class TestsDoubleArrayIterator {

	final Double[] array = { 0.9, 99.2, 09.3, 232343.09, 2321.0, 1231.2, 12D, .09, 12D, .0923D, .3D, 4.5D };

	@Test
	public void testShouldCount12_Of() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.of(this.array);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCount(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(this.array.length, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount1_OfRangeFirst_To_First() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(this.array, 0, 0);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(1, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount1_OfRangeLast_To_Last() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(this.array, 11, 11);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(1, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount9_OfRangeOne_To_Nine() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(this.array, 1, 9);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(9, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount1_OfCount_One() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofCount(this.array, 1);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(1, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount1_OfFrst_Ten() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofFirst(this.array, 10);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(1, CoreMatchers.is(itemsCount));
	}

	@Test
	public void testShouldCount6_OfLast_Five() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofLast(this.array, 5);
		System.out.println(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		MatcherAssert.assertThat(iterator.getCountFromRange(), CoreMatchers.is(itemsCount));
		MatcherAssert.assertThat(6, CoreMatchers.is(itemsCount));
	}
}

package com.codigo.aplios.data.sort;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.codigo.aplios.core.junit.attributes.Repeat;
import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;

public class SorterMergeTests extends SorterIntegerBase {

	@Test
	@Repeat(value = SorterBase.REPEAT_TIMES)
	public void shouldSortNumbersAscending() {

		// arrange
		this.sortable = new SorterFactory.Builder<Integer>(
			SortingAlgorithm.MERGESORT).setComparator((item1, item2) -> Integer.compare(item1, item2))
					.build();

		// act
		this.sortable.getSorter()
				.sort(this.data);

		// assert
		MatcherAssert.assertThat(this.data[0], CoreMatchers.equalTo(SorterIntegerBase.MIN_ELEMENT));
		MatcherAssert.assertThat(this.data[this.data.length - 1], CoreMatchers.equalTo(SorterIntegerBase.MAX_ELEMENT));
	}

	@Test
	@Repeat(value = SorterBase.REPEAT_TIMES)
	public void shouldSortNumberDescending() {

		// arrange
		this.sortable = new SorterFactory.Builder<Integer>(
			SortingAlgorithm.MERGESORT).setComparator((item1, item2) -> Integer.compare(item1, item2))
					.setSorterOrder(ISortable.SortingOrder.DESCENDING)
					.build();

		// act
		this.sortable.getSorter()
				.sort(this.data);

		// assert
		MatcherAssert.assertThat(this.data[0], CoreMatchers.equalTo(SorterIntegerBase.MAX_ELEMENT));
		MatcherAssert.assertThat(this.data[this.data.length - 1], CoreMatchers.equalTo(SorterIntegerBase.MIN_ELEMENT));
	}

}

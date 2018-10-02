package com.codigo.aplios.data.sort;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.codigo.aplios.core.junit.attributes.Repeat;
import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;

public class SorterBubbleRealTests extends SorterRealBase {

	@Test
	@Repeat(value = SorterBase.REPEAT_TIMES)
	public void shouldSortNumbersAscending() {

		// arrange
		this.sortable = new SorterFactory.Builder<Double>(
			SortingAlgorithm.BUBBLESORT).setComparator((item1, item2) -> Double.compare(item1, item2))
					.build();

		// act
		this.sortable.getSorter()
				.sort(this.data);

		// assert
		MatcherAssert.assertThat(this.data[0], CoreMatchers.equalTo(SorterRealBase.MIN_ELEMENT));
		MatcherAssert.assertThat(this.data[this.data.length - 1], CoreMatchers.equalTo(SorterRealBase.MAX_ELEMENT));
	}

	@Test
	@Repeat(value = SorterBase.REPEAT_TIMES)
	public void shouldSortNumberDescending() {

		// arrange
		this.sortable = new SorterFactory.Builder<Double>(
			SortingAlgorithm.BUBBLESORT).setComparator((item1, item2) -> Double.compare(item1, item2))
					.setSorterOrder(ISortable.SortingOrder.DESCENDING)
					.build();

		// act
		this.sortable.getSorter()
				.sort(this.data);

		// assert
		MatcherAssert.assertThat(this.data[0], CoreMatchers.equalTo(SorterRealBase.MAX_ELEMENT));
		MatcherAssert.assertThat(this.data[this.data.length - 1], CoreMatchers.equalTo(SorterRealBase.MIN_ELEMENT));
	}

}

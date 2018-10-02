package com.codigo.aplios.data.sort;

import com.codigo.aplios.core.junit.attributes.Repeat;
import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class SorterInsertRealTests
        extends SorterRealBase {

    @Test
    @Repeat(value = SorterBase.REPEAT_TIMES)
    public void shouldSortNumbersAscending() {

        // arrangeT
        this.sortable = new SorterFactory.Builder<Double>(SortingAlgorithm.INSERTSORT)
                .setComparator((item1, item2) -> Double.compare(item1, item2))
                .build();

        // act
        this.sortable.getSorter()
                .sort(this.data);

        // assert
        assertThat(this.data[0], equalTo(SorterRealBase.MIN_ELEMENT));
        assertThat(this.data[this.data.length - 1], equalTo(SorterRealBase.MAX_ELEMENT));
    }

    @Test
    @Repeat(value = SorterBase.REPEAT_TIMES)
    public void shouldSortNumberDescending() {

        // arrange
        this.sortable = new SorterFactory.Builder<Double>(SortingAlgorithm.INSERTSORT)
                .setComparator((item1, item2) -> Double.compare(item1, item2))
                .setSorterOrder(ISortable.SortingOrder.DESCENDING)
                .build();

        // act
        this.sortable.getSorter()
                .sort(this.data);

        // assert
        assertThat(this.data[0], equalTo(SorterRealBase.MAX_ELEMENT));
        assertThat(this.data[this.data.length - 1], equalTo(SorterRealBase.MIN_ELEMENT));
    }

}

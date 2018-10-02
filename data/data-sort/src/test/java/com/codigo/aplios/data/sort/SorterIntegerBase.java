package com.codigo.aplios.data.sort;

import java.util.stream.Stream;
import org.junit.After;
import org.junit.Before;

public abstract class SorterIntegerBase
        extends SorterBase {

    protected static final int MIN_ELEMENT;

    protected static final int MAX_ELEMENT;

    protected Integer[] data;

    protected SorterFactory<Integer> sortable;

    static {
        MIN_ELEMENT = -912;
        MAX_ELEMENT = 1_000_000;
    }

    @Before
    public void fillArray() {
        this.data = new Integer[]{
            3, 231, 332, 10, -2, -912, -0, +0, 11, 23, 23, -12, -1, -6, -5, 221, 10, 12, 209, 20, 0, 3, 2, 23, 12,
            23, 34, 45, 3, 42, 1, 7, 2, 12, 9, 4, 8, 1, 22, 50, 34, 1, 2, 3, 4, 5, 32, -99, 12, -9, -0, 12, 82, 12,
            22, 11, 22, 55, 0, 9, 3, 1, 2, 13, 3, 1, 32, 89, 1, 12, 1, 21, 3, 99, 12, 12, 11, 222, 0, 88, 997,
            1_000, 43, 1, 0, 8, 2, 1, 2, 1, 34, 23, 12, 1_000, 100, 99, 98, 11, 1, 1, 111, 232, 12, 23_123, 12_121,
            999_999, 4_444, 21_111, 21_212, 212_121, 8723, 121, -0, -98, 1_000_000, 998};
    }

    @After
    public void printEmptyLine() {
        if (SorterBase.SHOW_LOG) {
            Stream.of(this.data)
                    .forEach(e -> System.out.printf("|%4d", e));
            System.out.println();
        }
    }

}

package com.codigo.aplios.data.sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Klasa realizuje mechanizm sortowania sposobem HeapSort.
 *
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
public final class HeapSort<T>
        extends AbstractSorter<T> {

    public HeapSort(final Comparator<T> comp) {

        super(comp);
    }

    @Override
    public void sort(final List<T> data) {

    }

    @Override
    public void sort(final Stream<T> data) {

    }

    @Override
    public void sort(final T[] data) {

        // TODO Auto-generated method stub
    }

}

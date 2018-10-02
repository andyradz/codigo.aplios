package com.codigo.aplios.data.sort;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa implementuje mechanizm sortowania. Sortowanie wykonywanie jest na podstawie algorytmu scalania.
 *
 * @author andrzej.radziszewski
 * @category ordering
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T>
 *            Generyczny typ elementów kolekcji
 */
public final class MergeSorter<T> extends AbstractSorter<T> {

    /**
     * Kolekcja danych w postaci tablicy jednowymiarowej
     */
    private T[] data;

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     *                   Mechanizm porównymania obiektów
     */
    public MergeSorter(final Comparator<T> comparator) {

        super(comparator);
    }

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     *                    Mechanizm porównymania obiektów
     * @param sortingMode
     *                    Kolejność sortowania elementów
     */
    public MergeSorter(final Comparator<T> comparator, final SortingOrder sortingMode) {

        super(comparator,
                sortingMode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.xglobic.data.sorting.ISortable#sort(java.util.List)
     */
    @Override
    public void sort(final List<T> data) {

        this.setData((T[])data.toArray());
        this.mergeSort(0, data.size() - 1);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.xglobic.data.sorting.ISortable#sort(java.util.stream.Stream)
     */
    @Override
    public void sort(final Stream<T> data) {

        this.sort(data.collect(Collectors.toList()));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.xglobic.data.sorting.ISortable#sort(java.lang.Object[])
     */
    @Override
    public void sort(final T[] data) {

        this.setData(data);
        this.mergeSort(0, data.length - 1);
    }

    /**
     * Wlasciwosc przypisuje generyczna kolkecje lementów do posortowania
     *
     * @param data
     *             Generyczna kolekcja elementów
     */
    private void setData(final T[] data) {

        this.data = data;
    }

    /**
     * Metoda wykonuje podzial kolekcji elemntów na mniejsze zbiory z zastosowaniem rekurencji
     *
     * @param firstIndex
     *                   Indeks poczatku zakresu
     * @param lastIndex
     *                   Indeks końca zakresu
     */
    private void mergeSort(final int firstIndex, final int lastIndex) {

        if (firstIndex < lastIndex) {
            final int middle = firstIndex + (lastIndex - firstIndex) / 2;
            this.mergeSort(firstIndex, middle);
            this.mergeSort(middle + 1, lastIndex);
            this.merge(firstIndex, middle, lastIndex);
        }
    }

    /**
     * Metoda wykonuje scalenie elementów z dwóch zbiorów danych
     *
     * @param firstIndex
     *                    Indeks początku zakresu
     * @param middleIndex
     *                    Indeks środka zakresu
     * @param lastIndex
     *                    Indeks końca zakresu
     */
    private void merge(final int firstIndex, final int middleIndex, final int lastIndex) {

        final T[] helper = (T[])Array.newInstance(Object.class, this.data.length);

        for (int idx = firstIndex; idx <= lastIndex; idx++)
            helper[idx] = this.data[idx];

        int i = firstIndex;
        int j = middleIndex + 1;
        int k = firstIndex;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middleIndex && j <= lastIndex)
            if (!this.sortMode.apply(helper[i], helper[j]))
                this.data[k++] = helper[i++];
            else
                this.data[k++] = helper[j++];

        // Copy the rest of the left side of the array into the target array
        while (i <= middleIndex)
            this.data[k++] = helper[i++];
    }
}

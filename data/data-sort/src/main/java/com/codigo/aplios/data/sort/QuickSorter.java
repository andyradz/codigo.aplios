package com.codigo.aplios.data.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa realizuje mechanizm sortowania kolekcji danych. Mechanizm korzysta z algorytmu sortowania QuickSort.
 *
 * @author andrzej.radziszewski
 *
 * @category ordering
 * @version 1.0.0.0
 * @since 2017
 * @param <T>
 * Generyczny typ elementów kolekcji
 */
public final class QuickSorter<T>
        extends AbstractSorter<T> {

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Mechanizm porównywania obiektów
     */
    public QuickSorter(final Comparator<T> comparator) {

        super(comparator);
    }

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Mechanizm porównymania obiektów
     * @param sortingMode
     * Kolejność sortowania elementów
     */
    public QuickSorter(final Comparator<T> comparator, final SortingOrder sortingMode) {

        super(comparator,
                sortingMode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.allmarks.system.sort.AbstractSort#sort(java.util.List)
     */
    @Override
    public void sort(final List<T> data) {

        if (data.size() < 2)
            return;

        this.sort(data, 0, data.size() - 1);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.util.stream.Stream)
     */
    @Override
    public void sort(final Stream<T> data) {

        this.sort(data.collect(Collectors.toList()));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.lang.Object[])
     */
    @Override
    public void sort(final T[] data) {

        if (data.length < 2)
            return;

        this.sort(data, 0, data.length - 1);
    }

    /**
     * Metoda wykonuje sortowanie tablicy obiektów, z zakresie wyznaczanym przez minimalny i maksymalny index tablicy
     *
     * @param data
     * Kolekcja danych
     * @param lbound
     * Minimalny indeks zakresu tablicy
     * @param ubound
     * Maksymalny indeks zakresu tablicy
     */
    private void sort(final T[] data, final int lbound, final int ubound) {

        int i = (lbound + ubound) / 2;
        int j = lbound;

        final T pivot = data[i];
        data[i] = data[ubound];

        for (i = lbound; i < ubound; i++)
            if (!this.sortMode.apply(data[i], pivot)) {
                this.swapper.swap(data, i, j);
                j++;
            }

        data[ubound] = data[j];
        data[j] = pivot;

        if (lbound < j - 1)
            this.sort(data, lbound, j - 1);

        if (j + 1 < ubound)
            this.sort(data, j + 1, ubound);
    }

    /**
     * Metoda wykonuje sortowanie kolekcji obiektów, w zakresie wyznaczanym przez minimalny i maksymalny index kolekcji
     *
     * @param data
     * Kolekcja danych
     * @param lbound
     * Minimalny indeks zakresu tablicy
     * @param ubound
     * Maksymalny indeks zakresu tablicy
     */
    private void sort(final List<T> data, final int lbound, final int ubound) {

        int i = (lbound + ubound) / 2;
        int j = lbound;

        final T pivot = data.get(i);
        data.set(i, data.get(ubound));

        for (i = lbound; i < ubound; i++)
            if (!this.sortMode.apply(data.get(i), pivot)) {
                Collections.swap(data, i, j);
                j++;
            }

        data.set(ubound, data.get(j));
        data.set(j, pivot);

        if (lbound < j - 1)
            this.sort(data, lbound, j - 1);

        if (j + 1 < ubound)
            this.sort(data, j + 1, ubound);
    }

}

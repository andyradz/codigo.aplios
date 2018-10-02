package com.codigo.aplios.data.sort;

import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;
import com.codigo.aplios.data.sort.ISortable.SortingOrder;
import java.util.Comparator;

/**
 * Klasa realizuje mechanizm budowania kontekstu dla procesu sortowania kolekcji danych. Umożliwia ustawienie
 * konfiguracji do wykonania sortowani danych.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category ordering
 *
 * @param <T> Generyczny typ elementów kolekcji
 */
/**
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
public final class SorterFactory<T> {

    private SortingOrder sorterOrder;

    private ISortable<T> sorter;

    public static <T> Builder<T> builder() {

        return new SorterFactory.Builder<>(SortingAlgorithm.QUICKSORT);
    }

    /**
     * @param <T>
     * @param sorterAlgorithm
     * Znacznik wskazuje algorytm sortowania
     * @return Obiekt buildera kontekstu sortowania
     */
    public static <T> Builder<T> builder(final SortingAlgorithm sortAlgorithm) {

        return new SorterFactory.Builder<>(sortAlgorithm);
    }

    /**
     * Klasa realizuje mechanizm budowania konfiguracji procesu sortowania danych.
     *
     * @author andrzej.radziszewski
     * @category ordering
     *
     * @param <T>
     * Generyczny typ elementów kolekcji
     */
    public static class Builder<T> {

        /**
         * Atrybut zawiera kontekst sortowania
         */
        private SorterFactory<T> sorterContext;

        /**
         * Atrybut wskazuje algorytm sortowania
         */
        private SortingAlgorithm sortAlgorithm;

        /**
         * Atrybut wskazuje porządek sortowania danych
         */
        private SortingOrder sorterOrder;

        /**
         * Atrybut zawiera implementacje konkretnego sortera danych
         */
        private ISortable<T> sorter;

        /**
         * Atrybut zawiera mechanizm porównywania danych kolekcji
         */
        private Comparator<T> comparator;

        /**
         * Podstawowy konstruktor obiektu klasy
         *
         * @param sorter
         * Algorytm sortowania
         */
        public Builder(final SortingAlgorithm sortAlgorithm) {

            this.sorterContext = new SorterFactory<>();
            this.sortAlgorithm = sortAlgorithm;
            this.sorterOrder = SortingOrder.ASCENDING;
            this.comparator = (a, b) -> {
                if (a == b)
                    return 0;
                return -1;
            };
        }

        /**
         * Metoda ustawia algorytm sortowania
         *
         * @param sorterType
         * Algorytm sortowania
         * @return Budowniczy kontekstu sortowania
         */
        public Builder<T> setSorterAlgorithm(final SortingAlgorithm sortAlgorithm) {

            this.sortAlgorithm = sortAlgorithm;
            return this;
        }

        /**
         * Metoda ustawia typ porządkowania elmentów kolekcji
         *
         * @param sortOrder
         * Typ porządkowania
         * @return Budowniczy kontekstu sortowania
         */
        public Builder<T> setSorterOrder(final SortingOrder sortOrder) {

            this.sorterOrder = sortOrder;
            return this;
        }

        /**
         * Metoda ustawia wykonawce porównywania danych
         *
         * @param comparator
         * Wykonawca porównywania
         * @return Budowniczy kontekstu sortowania
         */
        public Builder<T> setComparator(final Comparator<T> comparator) {

            this.comparator = comparator;
            return this;
        }

        /**
         * Metoda buduje poszczególne elementy kontekstu w calośc
         *
         * @return Budowniczy kontekstu sortowania
         */
        public SorterFactory<T> build() {

            this.sorterContext.sorterOrder = this.sorterOrder;
            switch (this.sortAlgorithm) {
                case BUBBLESORT:
                    this.sorter = new BubbleSorter<>(this.comparator,
                            this.sorterContext.sorterOrder);
                    break;
                case INSERTSORT:
                    this.sorter = new InsertionSorter<>(this.comparator,
                            this.sorterContext.sorterOrder);
                    break;
                case QUICKSORT:
                    this.sorter = new QuickSorter<>(this.comparator,
                            this.sorterContext.sorterOrder);
                    break;
                case MERGESORT:
                    this.sorter = new MergeSorter<>(this.comparator,
                            this.sorterContext.sorterOrder);
                    break;
                default:
                    this.sorter = new QuickSorter<>(this.comparator,
                            this.sorterContext.sorterOrder);
                    break;
            }
            this.sorterContext.sorter = this.sorter;
            return this.sorterContext;
        }

    }

    /**
     * Metoda wskazuje wykonawce sortowania danych.
     *
     * @return Wykonawca sortowania
     */
    public ISortable<T> getSorter() {

        return this.sorter;
    }

}

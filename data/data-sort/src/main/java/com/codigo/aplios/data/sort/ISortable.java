package com.codigo.aplios.data.sort;

import java.util.List;
import java.util.stream.Stream;

/**
 * Interfejs definuje zestaw metod sortujących wybrane typy kolekcji danych. Sortowanie elementów w kolekcji będzie
 * odbywało się przez przestawianie elementów na określonych pozycjach kolekcji bez potrzeby tworzenia kolekcji
 * pomocniczych (bez użycia kopiowania).
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category ordering
 * @since 2017
 * @param <T>
 * Generyczny typ elementów kolekcji
 */
public interface ISortable<T> {

    /**
     * Rodzaje algorytmów sortujących
     */
    enum SortingAlgorithm {

        /**
         * Znacznik sortowania bąbelkowego
         */
        BUBBLESORT,
        /**
         * Znacznik sortowania przez wstawianie
         */
        INSERTSORT,
        /**
         * Znacznik sortowania mieszanego
         */
        COCTAILSORT,
        /**
         * Znacznik sortowania przez kopcowanie
         */
        MERGESORT,
        /**
         * Znacznik sortowania szybkiego
         */
        QUICKSORT

    }

    /**
     * Tryb sortowania danych rosnący lub malejący
     */
    enum SortingOrder {

        /**
         * Tryb sortowanie - malejący
         */
        ASCENDING,
        /**
         * Tryb sortowania - rosnący
         */
        DESCENDING;

    }

    /**
     * Metoda realizuje sortowanie elementów kolekcji zgodnie z zastosowanym algorytmem sortowania
     *
     * @param data
     * Kolekcja elementów w postaci tablicy
     */
    void sort(final T[] data);

    /**
     * Wersja metody przeciążonej {@link #sort(Object[]) }
     *
     * @param data
     * Kolekcja elementów w postaci listy
     */
    void sort(final List<T> data);

    /**
     * Wersja metody przeciążonej {@link #sort(Object[]) }
     *
     * @param data
     * Kolekcja elementów w postaci strumienia danych
     */
    void sort(final Stream<T> data);

}

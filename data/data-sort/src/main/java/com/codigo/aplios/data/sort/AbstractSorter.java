package com.codigo.aplios.data.sort;

import com.codigo.aplios.core.compare.CompareResult;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Klasa implementuje wspólny zakres funkcjonalny dla klas implementujących
 * mechanizm sortowania.
 *
 * @author andrzej.radziszewski
 * @category ordering
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T>
 * Generyczny typ elmentów kolekcji
 */
abstract class AbstractSorter<T>
        implements ISortable<T> {

    /**
     * Znacznik dekrementacji wartości o jeden
     */
    protected static final int ONE = 1;

    /**
     * Mechanizm wykonujący zamianę wartości na konkretnych pozycjach kolekcji
     */
    protected final Swapable<T> swapper;

    /**
     * Atrybut zawiera obiekt wykonujący porównanie dwóch obiektów
     */
    protected final Comparator<T> comparator;

    /**
     * Atrybut zawiera znacznik sposobu sortowania elementów kolekcji
     */
    protected final SortingOrder sortingMode;

    /**
     * Atrybut zawiera funktor odpowiedzialny za wykonanie porówania obiektów
     */
    protected BiFunction<T, T, Boolean> sortMode;

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Implementacja procedury porównywania obiektów
     */
    public AbstractSorter(final Comparator<T> comparator) {

        this(comparator,
                SortingOrder.ASCENDING);
    }

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Implementacja procedury porównywania obiektów
     * @param sortingMode
     * Tryb sortowania elementów kolekcji
     */
    public AbstractSorter(final Comparator<T> comparator, final SortingOrder sortingMode) {

        this.swapper = (items, sourcePos, targetPos) -> {
            final T tmp = items[sourcePos];
            items[sourcePos] = items[targetPos];
            items[targetPos] = tmp;
        };

        if (Objects.isNull(comparator))
            throw new NullPointerException("Pod wskazaną referencją nie ma instancji objektu!");

        this.comparator = comparator;
        this.sortingMode = sortingMode;

        switch (sortingMode) {
            case ASCENDING:
                this.sortMode = (w, e) -> {
                    return CompareResult.EQUALS.result() < comparator.compare(w, e);
                };
                break;
            case DESCENDING:
                this.sortMode = (w, e) -> {
                    return CompareResult.EQUALS.result() > comparator.compare(w, e);
                };
                break;
            default:
                this.sortMode = (w, e) -> {
                    return CompareResult.EQUALS.result() < comparator.compare(w, e);
                };
        }
    }

}

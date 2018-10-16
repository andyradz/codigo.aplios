package com.codigo.aplios.sdk.core.array;

/**
 * @author andrzej.radziszewski
 *
 */
public class ArrayIteratorFactory {

	/**
	 * Procedura wytwarza iterator kolekcji elementów tablicy, na podstawie przekazanej kolekcji
	 * elementów
	 *
	 * @param array
	 *        Kolekcja elementów tablicy
	 * @return Iterator elementów kolekcji
	 */
	public static <E> ArrayIterator<E> of(final E[] array) {
		return new ArrayIterator<>(
			array);
	}

	/**
	 * Procedura wytwarza iterator kolekcji elementów tablicy, na podstawie przekazanej kolekcji
	 * elementów, z ograniczeniem ilość elementów prezentowanych przez iterator. Ograniczenie określone
	 * na podstawie ilości elementów kolekcji
	 *
	 * @param array
	 *        Kolekcja elementów tablicy
	 * @param count
	 * @return Iterator elementów kolekcji
	 */
	public static <E> ArrayIterator<E> ofCount(final E[] array, final int count) {
		return new ArrayIterator<>(
			array, count);
	}

	/**
	 * Procedura wytwarza iterator kolekcji elementów tablicy, na podstawie przekazanej kolekcji
	 * elementów, z ograniczeniem zakresu elementów prezentowanych przez iterator. Ograniczenie
	 * określone na podstawie indeksu początku i końca elementów kolekcji
	 *
	 * @param array
	 *        Kolekcja elementów tablicy
	 * @param firstIndex
	 *        Indeks początku zakresu elementów kolekcji
	 * @param lastIndex
	 *        Indeks końca zakresu elementów kolekcji
	 * @return Iterator elementów kolekcji
	 */
	public static <E> ArrayIterator<E> ofRange(final E[] array, final int firstIndex, final int lastIndex) {
		return new ArrayIterator<>(
			array, firstIndex, lastIndex);
	}

	/**
	 * Procedura wytwarza iterator kolekcji elementów tablicy, na podstawie przekazanej kolekcji
	 * elementów, z ograniczeniem zakresu elementów prezentowanych przez iterator. Ograniczenie
	 * określone na podstawie indeksu początku elementów kolekcji. Indeks końca zakresu wyznaczony z
	 * pojemności kolekcji
	 *
	 * @param array
	 *        Kolekcja elementów tablicy
	 * @param firstIndex
	 *        Indeks początku zakresu elementów kolekcji
	 * @return Iterator elementów kolekcji
	 */
	public static <E> ArrayIterator<E> ofFirst(final E[] array, final int firstIndex) {
		return new ArrayIterator<>(
			array, firstIndex, firstIndex);
	}

	/**
	 * Procedura wytwarza iterator kolekcji elementów tablicy, na podstawie przekazanej kolekcji
	 * elementów, z ograniczeniem zakresu elementów prezentowanych przez iterator. Ograniczenie
	 * określone na podstawie indeksu końca elementów kolekcji. Indeks początku zakresu wyznaczony z
	 * bazowego indeksu kolekcji
	 *
	 * @param array
	 *        Kolekcja elementów tablicy
	 * @param lastIndex
	 * @return Iterator elementów kolekcji
	 */
	public static <E> ArrayIterator<E> ofLast(final E[] array, final int lastIndex) {
		return new ArrayIterator<>(
			array, ArrayIterable.ZERO_BASED_INDEX, lastIndex);
	}

}

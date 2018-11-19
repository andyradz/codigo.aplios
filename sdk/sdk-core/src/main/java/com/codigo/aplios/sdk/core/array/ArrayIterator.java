package com.codigo.aplios.sdk.core.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Klasa realizuje mechanizm iterowania po generycznej tablicy elementów typu <code><E></code>.
 * Mechanizm umożliwia iterowanie po wszystkich elementach kolekcji lub z tylko z zakresu wskazanego
 * przez indeks początku i końca.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category iterator
 *
 * @param <E>
 *        Genertyczny parametr typu elementów kolekcji
 */
public final class ArrayIterator<E> implements ArrayIterable<E>, Iterable<E> {

	/**
	 * Atrybut obiektu zawiera kolekcję generyczną elementów typu <code><E></code>
	 */
	private final E[] array;

	/**
	 * Atrybut obiektu przedstawia początkowy indeks zakresu kolekcji
	 */
	private final int beginIndex;

	/**
	 * Atrybut obiektu przedstawia końcowy indeks zakresu kolekcji
	 */
	private final int lastIndex;

	/**
	 * Atrybut obiektu przedstawia bieżący indeks w kolekcji
	 */
	private volatile int currentIndex;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>ArrayIterator</code>
	 *
	 * @param array
	 *        Tablica generycznych elementów
	 */
	ArrayIterator(final E[] array) {

		this(array, ArrayIterable.ZERO_BASED_INDEX, array.length - 1);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>ArrayIterator</code>
	 *
	 * @param array
	 *        Tablica generycznych elementów
	 * @param count
	 *        Ilość elementów przekazanych do iteracji
	 */

	ArrayIterator(final E[] array, final int count) {

		this(array, ArrayIterable.ZERO_BASED_INDEX, count - 1);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>ArrayIterator</code>
	 *
	 * @param array
	 *        Tablica generycznych elementów
	 * @param beginIndex
	 *        Początkowy indeks zakresy interatora
	 * @param lastIndex
	 *        Końcowy indeks zakresu interatora
	 */
	ArrayIterator(final E[] array, final int beginIndex, final int lastIndex) {

		validateParameters(array, beginIndex, lastIndex);

		this.array = array;
		this.beginIndex = beginIndex;
		this.lastIndex = lastIndex;
		this.currentIndex = this.beginIndex;
	}

	/**
	 * Procedura wykonuje walidację wartości parametrów wejściowych
	 *
	 * @param array
	 *        Tablica generycznych elementów
	 * @param beginIndex
	 *        Początkowy indeks zakresy interatora
	 * @param lastIndex
	 *        Końcowy indeks zakresu interatora
	 */
	private void validateParameters(final E[] array, final int beginIndex, final int lastIndex) {

		Objects.requireNonNull(array);

		if (ArrayIterable.EMPTY_TABLE == array.length)
			throw new IllegalArgumentException(
				"Kolekcja elementów jest pusta!");

		if (ArrayIterable.EMPTY_TABLE > beginIndex)
			throw new IllegalArgumentException(
				"Indeks początku nie może być mniejszy od zera!");

		if (lastIndex >= array.length)
			throw new IllegalArgumentException(
				"Indeks końcowy nie może być większy niż rozmiar tablicy!");

		if (lastIndex < beginIndex)
			throw new IllegalArgumentException(
				"Indeks końcowy nie może być mniejszy niż indeks początkowy!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {

		return this.currentIndex <= this.lastIndex;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#next()
	 */
	@Override
	public synchronized E next() {

		if (!hasNext())
			throw new NoSuchElementException();

		return this.array[this.currentIndex++];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see helpers.array.iterator.ArrayIterable#firstIndex()
	 */
	@Override
	public int getBeginIndex() {

		return this.beginIndex;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see helpers.array.iterator.ArrayIterable#currentIndex()
	 */
	@Override
	public int getCurrentIndex() {

		return this.currentIndex;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see helpers.array.iterator.ArrayIterable#lastIndex()
	 */
	@Override
	public int getLastIndex() {

		return this.lastIndex;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see helpers.array.iterator.ArrayIterable#count()
	 */
	@Override
	public int getCount() {

		return this.array.length;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see helpers.array.iterator.ArrayIterable#getCountFromRange()
	 */
	@Override
	public int getCountFromRange() {

		return (this.lastIndex - this.beginIndex) + 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + Arrays.hashCode(this.array);
		result = (prime * result) + this.beginIndex;
		result = (prime * result) + this.currentIndex;
		result = (prime * result) + this.lastIndex;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.sdk.core.array.ArrayIterable#reset()
	 */
	@Override
	public synchronized void reset() {

		this.currentIndex = this.beginIndex;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {

		return ArrayIteratorFactory.ofRange(this.array, this.beginIndex, this.lastIndex);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (Objects.isNull(obj))
			return false;

		if (!(obj instanceof ArrayIterator))
			return false;

		final ArrayIterator<?> other = ArrayIterator.class.cast(obj);
		if (!Arrays.equals(this.array, other.array))
			return false;

		if (this.beginIndex != other.beginIndex)
			return false;

		if (this.currentIndex != other.currentIndex)
			return false;

		if (this.lastIndex != other.lastIndex)
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "ArrayIterator [array=" + Arrays.toString(this.array) + ", beginIndex=" + this.beginIndex
				+ ", lastIndex=" + this.lastIndex + ", currentIndex=" + this.currentIndex + "]";
	}
}

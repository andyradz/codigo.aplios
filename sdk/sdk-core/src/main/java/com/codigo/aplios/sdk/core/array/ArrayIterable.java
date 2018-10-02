package com.codigo.aplios.sdk.core.array;

import java.util.Iterator;

import com.codigo.aplios.sdk.core.constants.Default;

/**
 * Interfejs deklaruje kontrakt opisujący mechanizm iterowania po elementach kolekcji typu tablica
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2018
 * @category iterator
 *
 * @param <E>
 *        Parametr typu generycznego, który określa typ elementów kolekcji
 */
public interface ArrayIterable<E> extends Iterator<E> {

	/**
	 * Stała klasy określa rozmiar pustej tablicy
	 */
	static final int EMPTY_TABLE = Default.of(int.class);

	/**
	 * Stała klasy określa początkowy numer indeksu tablicy
	 */
	static final int ZERO_BASED_INDEX = Default.of(int.class);

	/**
	 * Właściwość określa indeks początkowego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks pierwszego elementu zakresu kolekcji
	 */
	int getBeginIndex();

	/**
	 * Właściwość określa indeks bieżącego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks bieżącego elementu zakresu kolekcji
	 */
	int getCurrentIndex();

	/**
	 * Właściwość określa indeks ostatniego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks ostatniego elementu zakresu kolekcji
	 */
	int getLastIndex();

	/**
	 * Właściwość określa ilość elementów iteratora kolekcji
	 *
	 * @return Ilość elementów kolekcji
	 */
	int getCount();

	/**
	 * Właściwość określa ilość elementów iteratora z zakresu kolekcji
	 *
	 * @return Ilość elementów z zakresu kolekcji
	 */
	int getCountFromRange();

	/**
	 * Metoda wykonuje restart iteracji kolekcji. Iteracja rozpoczyna się od indeksu początkowego
	 */
	void reset();
}

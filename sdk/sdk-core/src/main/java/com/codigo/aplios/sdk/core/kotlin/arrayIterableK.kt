package com.codigo.aplios.sdk.core.kotlin

public interface ArrayIterableK<out E> : Iterator<E> {

	/**
	 * Właściwość określa indeks początkowego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks pierwszego elementu zakresu kolekcji
	 */
	fun beginIndex(): Int;

	/**
	 * Właściwość określa indeks bieżącego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks bieżącego elementu zakresu kolekcji
	 */
	fun currentIndex(): Int;

	/**
	 * Właściwość określa indeks ostatniego elementu zakresu iteratora kolekcji
	 *
	 * @return Indeks ostatniego elementu zakresu kolekcji
	 */
	fun lastIndex(): Int;

	/**
	 * Właściwość określa ilość elementów iteratora kolekcji
	 *
	 * @return Ilość elementów kolekcji
	 */
	fun count(): Int;

	/**
	 * Właściwość określa ilość elementów iteratora z zakresu kolekcji
	 *
	 * @return Ilość elementów z zakresu kolekcji
	 */
	fun countFromRange(): Int;

	/**
	 * Metoda wykonuje restart iteracji kolekcji. Iteracja rozpoczyna się od indeksu początkowego
	 */
	fun reset();
}
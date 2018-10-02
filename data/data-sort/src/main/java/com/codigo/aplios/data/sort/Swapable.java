package com.codigo.aplios.data.sort;

/**
 * Interfejs deklaruje metode opdowiedzialną za zamiane elemenów na wskazanych pozycjach
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category ordering
 *
 * @param <T>
 *        Generyczny typ elementów kolekcji
 */
@FunctionalInterface
public interface Swapable<T> {

	/**
	 * @param items
	 *        Kolekcja elmentów w postaci tablicy
	 * @param sourcePos
	 *        Pozycja żródowa elementu w kolekcji
	 * @param targetPos
	 *        Pozycja docelowa elementu w kolekcji
	 */
	void swap(T[] items, int sourcePos, int targetPos);
}
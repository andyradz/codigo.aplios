package com.codigo.aplios.data.core.paging;

import java.util.Iterator;

import org.junit.runner.manipulation.Sortable;

//import org.junit.runner.manipulation.Sortable;

/**
 * Interfejs deklaruje kontrakt związany ze stronicowanie danych dowolnej kolekcji danych.
 * Implementacja interfejsu dostarcza stronę z danymi, która wynika z odpowiedniego podziału danych
 * na strony.
 *
 * @author andrzej.radziszewski
 *
 * @param <E>
 *        Generyczny typ danych
 */

/*
 * Doda grupowanie danych na stronie po konkretnych polach. Grupowanie istalana na interfejście
 * który jest przekazywany do strony.
 */
public interface IPageable<E> extends Iterable<E> {

	/**
	 * Właściwość określa numer indeksu strony danych z kolekcji stron. Bazowy indeks strony posiada
	 * wartośc 0.
	 *
	 * @return Bieżący indeks strony danych.
	 */
	long getPageIndex();

	/**
	 * Właściwość określa numer strony z kolekcji stron. Bazowy numer strony posiada wartośc 1.
	 *
	 * @return Bieżący numer strony danych.
	 */
	long getPageNumer();

	/**
	 * Właściwość określa rozmiar strony danych. Rozmiar określa maksymalną ilość pozycji danych jakie
	 * mogą by wyświetlane na stronie danych.
	 *
	 * @return Maksymalny rozmiar strony danych.
	 */
	long getPageSize();

	/**
	 * Właściwość określa ilość pozycji danych dostępnych na stronie. Z założenia wynika, że nie może by
	 * dostępna strona, która nie posiada pozycji danych.
	 *
	 * @return Ilość pozycji danych dostępnych na stronie.
	 */
	long getPageCount();

	/**
	 * Właściwość określa czy strona jest pusta, nie posiada żadanych pozycji danych.
	 *
	 * @return Flaga z informacją czy strona nie posiada pozycji danych.
	 */
	boolean isEmpty();

	/**
	 * Czy strona ma wypłenione wszystkie pozycje na stronie tj. max == ilośc wierszy
	 *
	 * @return
	 */
	boolean isFullFilled();

	/**
	 * Właściwość określa czy bieżąca strona danych jest pierwszą stroną w kolekcji stron.
	 *
	 * @return Flaga z inforacją czy pierwsza strona danych
	 */
	boolean isFirst();

	/**
	 * Właściwość określa czy bieżąca strona danych jest ostatnią stroną w kolekcji stron.
	 *
	 * @return Flaga z inforacją czy pierwsza strona danych
	 */
	boolean isLast();

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	Iterator<E> iterator();

	Sortable getSorter();

	/**
	 * Właściwość określa czy strona ma być pokazywana w kolekcji stron. Można ustalić aby wskazana
	 * strona nie była dostępna w kolkcji stron.
	 *
	 * @return Flaga z informcją czy strona widoczna
	 */
	boolean isVisible();
}

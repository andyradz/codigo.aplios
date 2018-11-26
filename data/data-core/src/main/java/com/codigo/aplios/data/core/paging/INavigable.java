/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package com.codigo.aplios.data.core.paging;

/**
 * Iterfejs powinien obsługiwac kolekcję którą można podzieli na strony na bazie
 * indeksów lub stream
 * 
 * @author andrzej.radziszewski
 */
public interface INavigable<E> extends Iterable<E> {

	IPageable<E> getCurrentPage();

	IPageable<E> getNextPage();

	IPageable<E> getPriorPage();

	IPageable<E> getFirstPage();

	IPageable<E> getLastPage();

	IPageable<E> getPageByIndex(long position);

	// Pobiera wszystkie strony kolekcji
	IPageable<E> getAllPages();

	boolean hasNextPage();

	boolean hasPriorPage();

	long getPageCount();
}

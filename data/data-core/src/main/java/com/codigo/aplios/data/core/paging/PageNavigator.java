/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package com.codigo.aplios.data.core.paging;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Dodać builder do navigator tj.
 */
public class PageNavigator<E> implements INavigable<E> {

	public static <E> PageNavigator<E> fromList(Collection<E> data, long pageSize) {
		return new PageNavigator<>(data, pageSize);
	}

	private static final long MIN_PAGE_INDEX = 0;
	private static final long MIN_PAGE_NUMBER = 1;
	private static final long MIN_PAGE_SIZE = 1;
	private static final long MAX_PAGE_SIZE = 100_000;
	private static final long DEFAULT_PAGE_SIZE = 1_000;

	/**
	 * Atrybut obiektu reprezentuje rozmiar strony na pozycje danych.
	 */
	private final long pageSize;

	/**
	 * Atrybut obiektu reprezentuje ilośc wszystkich dostępnych stron
	 */
	private final long totalPageCount;

	/**
	 * Atrybut obiektu reprezentuje numer bieżacego indeksu strony. Numeracja
	 * rozpoczyna się od wartości 0
	 */
	private long currenPageIndex;

	/**
	 * Atrybut obiektu reprezentuje numer bieżacej strony. Numeracja rozpoczyna się
	 * od wartości 1
	 */
	private long currenPageNumber;

	private Collection<E> data;

	private Stream<E> getPageSubData() {
		return this.data.stream()
			.skip(currenPageIndex * this.pageSize)
			.limit(this.pageSize);
	}

	private long getPageNumber() {

		return this.currenPageIndex + 1;
	}

	private void incrementPageIndex() {

		// if ((this.currenPageIndex + 1) < this.getPageCount())
		this.currenPageIndex++;
	}

	private void decrementPageIndex() {
		this.currenPageIndex--;
	}

	/**
	 * Domyślny konstruktor obiektu klasy <code>PageNavigator<E></code>
	 * 
	 * @param data
	 */
	public PageNavigator(Collection<E> data) {
		this(data, DEFAULT_PAGE_SIZE);
	}

	/**
	 * Domyślny konstruktor obiektu klasy <code>PageNavigator<E></code>
	 * 
	 * @param data
	 * @param pageSize
	 */
	public PageNavigator(Collection<E> data, long pageSize) {

		this.data = data;
		this.pageSize = pageSize;
		totalPageCount = (long) Math.ceil(this.data.stream()
			.count() / (double) pageSize);
		this.currenPageIndex = MIN_PAGE_INDEX;
		this.currenPageNumber = MIN_PAGE_NUMBER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codigo.aplios.data.core.paging.INavigable#getCurrentPage()
	 */
	@Override
	public IPageable<E> getCurrentPage() {

		PageResult<E> result = new PageResult<>(this.getPageSubData()
			// .map(element -> new PageElement<E>(1))
			.collect(Collectors.toList()), getPageNumber());

		// incrementPageIndex();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {

		return this.getPageSubData()
			.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codigo.aplios.data.core.paging.INavigable#getNextPage()
	 */
	@Override
	public IPageable<E> getNextPage() {

		PageResult<E> result = new PageResult<>(this.getPageSubData()
			// .map(element -> new PageElement<E>(1))
			.collect(Collectors.toList()), getPageNumber());

		incrementPageIndex();

		return result;
	}

	@Override
	public IPageable<E> getPriorPage() {

		PageResult<E> result = new PageResult<>(this.getPageSubData()
			// .map(element -> new PageElement<E>(1))
			.collect(Collectors.toList()), currenPageIndex);

		decrementPageIndex();

		return result;
	}

	@Override
	public IPageable<E> getFirstPage() {

		currenPageIndex = 0;

		return new PageResult<>(this.getPageSubData()
			// .map(element -> new PageElement<E>(1))
			.collect(Collectors.toList()), currenPageIndex);
	}

	@Override
	public IPageable<E> getLastPage() {
		return null;
	}

	@Override
	public IPageable<E> getPageByIndex(long position) {
		return null;
	}

	@Override
	public IPageable<E> getAllPages() {

		return new PageResult<>(this.data.stream()
			// .map(element -> new PageElement<E>(1))
			.collect(Collectors.toList()), 1);
	}

	@Override
	public boolean hasPriorPage() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codigo.aplios.data.core.paging.INavigable#hasNextPage()
	 */
	@Override
	public boolean hasNextPage() {

		return ((this.currenPageIndex * this.pageSize)
				/ this.pageSize) < this.totalPageCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codigo.aplios.data.core.paging.INavigable#getTotalCount()
	 */
	@Override
	public long getPageCount() {

		return this.totalPageCount;
	}
}
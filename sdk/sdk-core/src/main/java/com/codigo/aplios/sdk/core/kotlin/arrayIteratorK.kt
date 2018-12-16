package com.codigo.aplios.sdk.core.kotlin

import com.codigo.aplios.sdk.core.kotlin.ArrayIterableK


class arrayIteratorK<E> : ArrayIterableK<E> {

	/**
	 * Atrybut obiektu zawiera kolekcję generyczną elementów typu <code><E></code>
	 */
	var array: Array<E> 

	/**
	 * Atrybut obiektu przedstawia początkowy indeks zakresu kolekcji
	 */
	var beginIndex: Int = 0;

	/**
	 * Atrybut obiektu przedstawia końcowy indeks zakresu kolekcji
	 */
	var lastIndex: Int = 0;

	/**
	 * Atrybut obiektu przedstawia bieżący indeks w kolekcji
	 */
	var currentIndex: Int = 0;


	constructor(array: Array<E>) : this(array, 0, array.size - 1) {
	}


	constructor(array: Array<E>, count: Int) : this(array, 0, count - 1) {

	}


	constructor(array: Array<E>, beginIndex: Int, lastIndex: Int) {

		this.array = array;
		this.beginIndex = beginIndex;
		this.lastIndex = lastIndex;
		this.currentIndex = this.beginIndex;
	}

	override fun lastIndex(): Int {

		return this.lastIndex;
	}

	override fun count(): Int {

		return this.array.size;
	}

	override fun countFromRange(): Int {

		return (this.lastIndex - this.beginIndex) + 1;
	}

	override fun reset() {

		this.currentIndex = this.beginIndex;
	}

	override fun hasNext(): Boolean {

		return this.currentIndex <= this.lastIndex;
	}

	override fun next(): E {

		if (!hasNext())
			throw NoSuchElementException()

		return this.array.get(currentIndex++);
	}


	override fun beginIndex(): Int {

		return this.beginIndex;
	}

	override fun currentIndex(): Int {

		return this.currentIndex;
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.data.sort;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;

/**
 * Wzorzec prostej fabryki obiektów klasy <code>ISortable</code>
 *
 * @author andrzej.radziszewski
 */
public class SorterFactory1 {

	private final @NonNull SortingAlgorithm algorithm;

	public SorterFactory1(@NonNull final SortingAlgorithm algorithm) {

		this.algorithm = algorithm;
	}

	public ISortable<?> build() {

		@NonNull
		final SortingAlgorithm algorithm = this.algorithm;

		if (algorithm == SortingAlgorithm.INSERTSORT)
			return new InsertionSorter<String>(
				null);

		else
			return new QuickSorter<String>(
				null);
	}
}

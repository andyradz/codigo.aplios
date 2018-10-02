/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.data.sort;

import com.codigo.aplios.data.sort.ISortable.SortingAlgorithm;
import static com.codigo.aplios.data.sort.ISortable.SortingAlgorithm.INSERTSORT;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Wzorzec prostej fabryki obiektów klasy <code>ISortable</code>
 *
 * @author andrzej.radziszewski
 */
public class SorterFactory1 {

    private final @NonNull
    SortingAlgorithm algorithm;

    public SorterFactory1(@NonNull SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public ISortable build() {
        ISortable sortable;

        switch (this.algorithm) {
            case INSERTSORT:
                return new InsertionSorter(null);

            default:
                return new QuickSorter(null);
        }
    }

}

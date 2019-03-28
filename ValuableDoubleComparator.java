package com.codigo.aplios.group.sdk.core.value;

import java.util.Comparator;

/**
 * Klasa komparatora dedykowana typowi <code>Double</code> z której korzysta
 * klasa <code>Valuable</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 *
 * @category comparator
 */
public class ValuableDoubleComparator implements Comparator<Valuable<Double>> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Valuable<Double> m1, final Valuable<Double> m2) {

		return m1.get()
			.compareTo(m2.get());
	}
}

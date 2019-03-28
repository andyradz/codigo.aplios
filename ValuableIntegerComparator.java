package com.codigo.aplios.group.sdk.core.value;

import java.util.Comparator;

/**
 * Klasa komparatora dedykowana typowi <code>Integer</code> z której korzysta
 * klasa <code>Valuable</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 *
 * @category comparator
 */
public final class ValuableIntegerComparator implements Comparator<Valuable<Integer>> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Valuable<Integer> m1, final Valuable<Integer> m2) {

		return m1.get()
			.compareTo(m2.get());
	}
}
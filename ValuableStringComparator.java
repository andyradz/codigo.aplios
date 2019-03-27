package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;

/**
 * Klasa komparatora dedykowana typowi <code>String</code> z której korzysta
 * klasa <code>Valuable</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 *
 * @category comparator
 */
public class ValuableStringComparator implements Comparator<Valuable<String>> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Valuable<String> m1, final Valuable<String> m2) {

		return m1.get()
			.compareTo(m2.get());
	}
}
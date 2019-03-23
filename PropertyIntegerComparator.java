package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;

public final class PropertyIntegerComparator implements Comparator<Property<Integer>> {

	@Override
	public int compare(final Property<Integer> m1, final Property<Integer> m2) {

		return m1.get()
			.compareTo(m2.get());
	}
}
package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;

public class PropertyStringComparator implements Comparator<Property<String>> {
	@Override
	public int compare(final Property<String> m1, final Property<String> m2) {

		return m1.compareTo(m2);
	}
}
package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;

public final class PropertyBooleanComparator implements Comparator<Property<Boolean>> {

	@Override
	public int compare(final Property<Boolean> m1, final Property<Boolean> m2) {

		return m1.compareTo(m2);
	}
}
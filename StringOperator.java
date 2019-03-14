package com.codigo.aplios.group.timeline.common.helper;

import java.util.Objects;

public final class StringOperator {

	private StringOperator() {

	}

	public static boolean isNullOrEmpty(final String string) {

		return (!(Objects.isNull(string)) && !(string.isEmpty() || string.isBlank()));
	}
}

package com.codigo.aplios.gui.control;

import java.util.EnumSet;

/**
 *
 * @author JHS
 */
public class EnumOperator<T> implements IFilterOperator<T> {

	public static final EnumSet<Type> VALID_TYPES = EnumSet.of(Type.NONE, Type.EQUALS);

	private final IFilterOperator.Type	type;
	private final T						value;

	public EnumOperator(final IFilterOperator.Type type, final T value) {

		this.type = type;
		this.value = value;
	}

	@Override
	public IFilterOperator.Type getType() {

		return this.type;
	}

	@Override
	public T getValue() {

		return this.value;
	}

}

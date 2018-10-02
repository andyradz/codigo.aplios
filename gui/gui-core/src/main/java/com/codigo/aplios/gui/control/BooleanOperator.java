package com.codigo.aplios.gui.control;

import java.util.EnumSet;

/**
 *
 * @author JHS
 */
public class BooleanOperator implements IFilterOperator<Boolean> {

	public static final EnumSet<Type> VALID_TYPES = EnumSet.of(Type.NONE, Type.TRUE, Type.FALSE);

	private final IFilterOperator.Type	type;
	private final Boolean				value;

	public BooleanOperator(final IFilterOperator.Type type, final Boolean value) {

		this.type = type;
		this.value = value;
	}

	@Override
	public IFilterOperator.Type getType() {

		return this.type;
	}

	@Override
	public Boolean getValue() {

		return this.value;
	}
}

package com.codigo.aplios.gui.control;

import java.util.EnumSet;

/**
 *
 * @author JHS
 */
public class NumberOperator<T extends Number>
        implements IFilterOperator<T> {

    public static final EnumSet<Type> VALID_TYPES = EnumSet.of(Type.NONE, Type.EQUALS, Type.NOTEQUALS, Type.GREATERTHAN,
            Type.GREATERTHANEQUALS, Type.LESSTHAN, Type.LESSTHANEQUALS);

    private final IFilterOperator.Type type;

    private final T value;

    public NumberOperator(final IFilterOperator.Type type, final T value) {

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

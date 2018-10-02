package com.codigo.aplios.gui.control;

import java.util.EnumSet;

/**
 *
 * @author JHS
 */
public class StringOperator
        implements IFilterOperator<String> {

    public static final EnumSet<Type> VALID_TYPES = EnumSet.of(Type.NONE, Type.EQUALS, Type.NOTEQUALS, Type.CONTAINS,
            Type.STARTSWITH, Type.ENDSWITH);

    private final IFilterOperator.Type type;

    private final String value;

    public StringOperator(final IFilterOperator.Type type, final String value) {

        this.type = type;
        this.value = value;
    }

    @Override
    public IFilterOperator.Type getType() {

        return this.type;
    }

    @Override
    public String getValue() {

        return this.value;
    }

}

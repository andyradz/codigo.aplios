package com.codigo.aplios.gui.control;

import java.util.Date;
import java.util.EnumSet;

/**
 *
 * @author JHS
 */
public class DateOperator
        implements IFilterOperator<Date> {

    public static final EnumSet<Type> VALID_TYPES = EnumSet.of(Type.NONE, Type.EQUALS, Type.NOTEQUALS, Type.AFTER,
            Type.AFTERON, Type.BEFORE, Type.BEFOREON);

    private final IFilterOperator.Type type;

    private final Date value;

    public DateOperator(final IFilterOperator.Type type, final Date value) {

        this.type = type;
        this.value = value;
    }

    @Override
    public IFilterOperator.Type getType() {

        return this.type;
    }

    @Override
    public Date getValue() {

        return this.value;
    }

}

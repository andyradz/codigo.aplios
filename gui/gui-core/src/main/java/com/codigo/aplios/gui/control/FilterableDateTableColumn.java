package com.codigo.aplios.gui.control;

/**
 *
 * @author JHS
 */
public class FilterableDateTableColumn<S, T>
        extends AbstractFilterableTableColumn<S, T, DateOperator, DateFilterEditor> {

    public FilterableDateTableColumn() {

        this("");
    }

    public FilterableDateTableColumn(final String text) {

        super(text, new DateFilterEditor(text));
    }

    public FilterableDateTableColumn(final String text, final String dateFormat) {

        super(text, new DateFilterEditor(text, dateFormat));
    }

}

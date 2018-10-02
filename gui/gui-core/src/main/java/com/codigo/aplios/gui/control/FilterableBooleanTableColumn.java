package com.codigo.aplios.gui.control;

/**
 *
 * @author JHS
 */
public class FilterableBooleanTableColumn<S, T>
        extends AbstractFilterableTableColumn<S, T, BooleanOperator, BooleanFilterEditor> {

	public FilterableBooleanTableColumn() {

		this("");
	}

	public FilterableBooleanTableColumn(final String text) {

		super(text, new BooleanFilterEditor(text));
	}
}
package com.codigo.aplios.gui.control;

public class FilterableStringTableColumn<S, T>
        extends AbstractFilterableTableColumn<S, T, StringOperator, TextFilterEditor> {
	public FilterableStringTableColumn() {

		this("");
	}

	public FilterableStringTableColumn(final String text) {

		super(text, new TextFilterEditor(text));
	}
}

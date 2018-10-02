package com.codigo.aplios.gui.control;

public class FilterableIntegerTableColumn<S, T> extends AbstractFilterableNumberTableColumn<S, Integer> {

	public FilterableIntegerTableColumn() {

		this("");
	}

	public FilterableIntegerTableColumn(final String text) {

		super(text, Integer.class);
	}

}

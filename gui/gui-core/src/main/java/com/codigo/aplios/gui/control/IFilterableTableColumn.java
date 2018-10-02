package com.codigo.aplios.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author jhsheets@gmail.com
 */
public interface IFilterableTableColumn<R extends IFilterOperator<?>, M extends IFilterEditor<R>> {
	/**
	 * Note: this method can return {@link IFilterOperator.Type.NONE}'s.
	 * Use {@link #isFiltered()} to determine if there is actually a filter
	 * applied to this column
	 *
	 * @return All applied filters.
	 */
	public ObservableList<R> getFilters();

	/**
	 * @return Property indicating if this column has filters applied
	 */
	public BooleanProperty filteredProperty();

	/**
	 * @return If this column has filters applied
	 */
	public boolean isFiltered();
}
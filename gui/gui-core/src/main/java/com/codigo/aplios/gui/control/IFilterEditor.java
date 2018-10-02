package com.codigo.aplios.gui.control;

import javafx.beans.property.BooleanProperty;

/**
 * A graphical interface used to change filters
 *
 * @author JHS
 */
public interface IFilterEditor<R extends IFilterOperator<?>> {

	/**
	 * @return The user entered filters
	 * @throws Exception
	 */
	abstract public R[] getFilters() throws Exception;

	/**
	 * Cancel filter editing
	 */
	abstract public void cancel();

	/**
	 * Set the filter to the saved
	 *
	 * @return If the filter was successfully saved
	 * @throws Exception
	 */
	abstract public boolean save() throws Exception;

	/**
	 * Clears the filter back to its default state. If successful, the menu should
	 * hide.
	 *
	 * @return If the filter was successfully cleared
	 * @throws Exception
	 */
	abstract public boolean clear() throws Exception;

	/**
	 * @return The menu used to change the filter
	 */
	abstract public FilterMenuPopup getFilterMenu();

	/**
	 * @return Property identifying if there is a filter set
	 */
	abstract public BooleanProperty filteredProperty();

	/**
	 * @return If there is currently a filter set
	 */
	abstract public boolean isFiltered();
}

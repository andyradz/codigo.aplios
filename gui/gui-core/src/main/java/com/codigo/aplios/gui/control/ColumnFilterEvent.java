package com.codigo.aplios.gui.control;

import java.util.List;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.TableView;

/**
 * An event that is fired when an {@link AbstractFilterableTableColumn} has its
 * filter changed
 *
 * @author JHS
 */
public class ColumnFilterEvent<S, T, R extends IFilterOperator<?>, M extends IFilterEditor<R>> extends Event {

	private static final long serialVersionUID = 4680995104566602041L;

	/**
	 * An event indicating that the filter has changed
	 */
	public static final EventType<ColumnFilterEvent<?, ?, ?, ?>> FILTER_CHANGED_EVENT = new EventType<>(
	        Event.ANY, "FILTER_CHANGED");

	private final List<R> filter;

	private final AbstractFilterableTableColumn<S, T, R, M> sourceColumn;

	public ColumnFilterEvent(final TableView<S> table, final AbstractFilterableTableColumn<S, T, R, M> sourceColumn,
	        final List<R> filter) {

		super(table, Event.NULL_SOURCE_TARGET, ColumnFilterEvent.FILTER_CHANGED_EVENT);

		if (table == null)
			throw new NullPointerException("TableView can not be null");

		this.filter = filter;
		this.sourceColumn = sourceColumn;
	}

	/**
	 * @return Any and all filters applied to the column
	 */
	public List<R> getFilters() {

		return this.filter;
	}

	/**
	 * @return The {@link AbstractFilterableTableColumn} which had its filter
	 *         changed
	 */
	public AbstractFilterableTableColumn<S, T, R, M> sourceColumn() {

		return this.sourceColumn;
	}
}

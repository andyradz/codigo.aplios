package com.codigo.aplios.gui.control;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link TableView} that identifies any
 * {@link AbstractFilterableTableColumn}'s added to it, and fires a single event
 * when any of them have their filters changed. <br/>
 * <br/>
 * To listen for changes the table's filters, register a
 * {@link ColumnFilterEvent#FILTER_CHANGED_EVENT} with
 * {@link #addEventFilter(javafx.event.EventType, javafx.event.EventHandler) }
 * or
 * {@link #addEventHandler(javafx.event.EventType, javafx.event.EventHandler) }
 *
 * @author JHS
 */
public class FilteredTableView<S>
        extends TableView<S> {

    private static final Logger logger = LoggerFactory.getLogger(FilteredTableView.class);

    /**
     * List of filterable columns with a filter applied
     */
    private ObservableList<AbstractFilterableTableColumn<?, ?, ?, ?>> filteredColumns;

    public FilteredTableView(final ObservableList<S> ol) {

        this();
        super.setItems(ol);
    }

    public FilteredTableView() {

        super();

        this.filteredColumns = FXCollections.observableArrayList();

        // Execute the filteringChanged runnable
        // And, if a column has a filter on it, make sure that column is in our
        // filteredColumns list
        final EventHandler<ColumnFilterEvent<?, ?, ?, ?>> columnFilteredEventHandler = event -> {

            // Keep track of which TableColumn's are currently filtered
            final AbstractFilterableTableColumn<?, ?, ?, ?> col = event.sourceColumn();

            if ((col.isFiltered() == true) && (FilteredTableView.this.filteredColumns.contains(col) == false)) {
                FilteredTableView.this.filteredColumns.add(col);
                FilteredTableView.logger.debug(String.format("Filter added on column: %s", col.getText()));
            } else if ((col.isFiltered() == false) && (FilteredTableView.this.filteredColumns.contains(col) == true)) {
                FilteredTableView.this.filteredColumns.remove(col);
                FilteredTableView.logger.debug(String.format("Filter removed on column: %s", col.getText()));
            }

            // Forward event
            FilteredTableView.this.fireEvent(event);
        };

        // Make sure any filterable columns on this table have the
        // columnFilterEventHandler
        this.getColumns()
                .addListener((ListChangeListener<TableColumn<?, ?>>)change ->
                {
                    change.next();// must advance to next change, for whatever reason...
                    // Drag-n-dropping a column fires a remove and an add.
                    if (change.wasRemoved())
                        for (final TableColumn<?, ?> col1 : change.getAddedSubList())
                            if (col1 instanceof AbstractFilterableTableColumn) {
                                FilteredTableView.logger.debug(String
                                        .format("No longer listening for filter changes on column: %s", col1.getText()));
                                final AbstractFilterableTableColumn<?, ?, ?, ?> fcol1 =
                                        (AbstractFilterableTableColumn<?, ?, ?, ?>)col1;
                                fcol1.removeEventHandler(ColumnFilterEvent.FILTER_CHANGED_EVENT,
                                        columnFilteredEventHandler);
                            }
                    if (change.wasAdded())
                        for (final TableColumn<?, ?> col2 : change.getAddedSubList())
                            if (col2 instanceof AbstractFilterableTableColumn) {
                                FilteredTableView.logger
                                        .debug(String.format("Now listening for filter changes on column: %s", col2.getText()));
                                final AbstractFilterableTableColumn<?, ?, ?, ?> fcol2 =
                                        (AbstractFilterableTableColumn<?, ?, ?, ?>)col2;
                                fcol2.addEventHandler(ColumnFilterEvent.FILTER_CHANGED_EVENT, columnFilteredEventHandler);
                            }
                });
    }

    /**
     * @return Observable list containing any
     * {@link AbstractFilterableTableColumn}'s that have a filter applied
     */
    public ObservableList<AbstractFilterableTableColumn<?, ?, ?, ?>> getFilteredColumns() {

        return this.filteredColumns;
    }

}

package com.codigo.aplios.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Note: we hijack the ContextMenu to display the filter selection dialog. Do
 * not set the ContextMenu anywhere else.
 *
 * @author JHS
 */
public class AbstractFilterableTableColumn<S, T, R extends IFilterOperator<?>, M extends IFilterEditor<R>>
		extends TableColumn<S, T> implements IFilterableTableColumn<R, M> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractFilterableTableColumn.class);

	private final M filterEditor;

	private final ObservableList<R> filterResults;

	public AbstractFilterableTableColumn(final String name, final M filterEditor) {

		super(name);

		this.filterEditor = filterEditor;
		this.filterResults = FXCollections.observableArrayList();

		// Keep the popup menu's title sync'd with the column title
		filterEditor.getFilterMenu().titleProperty().bind(AbstractFilterableTableColumn.this.textProperty());

		final FilterMenuButton filterMnuButton = new FilterMenuButton(filterEditor.getFilterMenu());
		filterMnuButton.activeProperty().bind(filterEditor.filteredProperty());
		// Display a button on the column to show the menu
		this.setGraphic(filterMnuButton);

		// I'd love to do this, but you have to set the content to GRAPHIC_ONLY, but
		// there's
		// no way to do that as the header skin is part of the table, not the column
		// final Label lbl = new Label();
		// lbl.textProperty().bind(this.textProperty());
		// final BorderPane pane = new BorderPane();
		// pane.setLeft(filterTrigger);
		// pane.setCenter(lbl);
		// setGraphic(pane);
		filterEditor.getFilterMenu().setResetEvent(t -> {

			try {
				if (filterEditor.clear()) {
					AbstractFilterableTableColumn.this.filterResults.setAll(filterEditor.getFilters());

					final ColumnFilterEvent<S, T, R, M> e = new ColumnFilterEvent<>(
							AbstractFilterableTableColumn.this.getTableView(), AbstractFilterableTableColumn.this,
							AbstractFilterableTableColumn.this.getFilters());

					Event.fireEvent(AbstractFilterableTableColumn.this, e);
				}
				filterEditor.getFilterMenu().hide();
			} catch (final Exception ex) {
				AbstractFilterableTableColumn.logger.error(String.format("Error clearing filter on column: %s",
						AbstractFilterableTableColumn.this.getText()), ex);
			}
		});

		filterEditor.getFilterMenu().setSaveEvent(t -> {

			try {
				if (filterEditor.save()) {
					AbstractFilterableTableColumn.this.filterResults.setAll(filterEditor.getFilters());

					final ColumnFilterEvent<S, T, R, M> e = new ColumnFilterEvent<>(
							AbstractFilterableTableColumn.this.getTableView(), AbstractFilterableTableColumn.this,
							AbstractFilterableTableColumn.this.getFilters());

					Event.fireEvent(AbstractFilterableTableColumn.this, e);
				}
				filterEditor.getFilterMenu().hide();
			} catch (final Exception ex) {
				AbstractFilterableTableColumn.logger.error(String.format("Error saving filter on column: %s",
						AbstractFilterableTableColumn.this.getText()), ex);
			}
		});
	}

	protected M getFilterEditor() {

		return this.filterEditor;
	}

	@Override
	public ObservableList<R> getFilters() {

		return this.filterResults;
	}

	@Override
	public final BooleanProperty filteredProperty() {

		return this.filterEditor.filteredProperty();
	}

	@Override
	public boolean isFiltered() {

		return this.filterEditor.isFiltered();
	}

	// public void setFilters(R filters) {
	// // TODO
	// }
	// public boolean isFilterable() {
	// // TODO
	// }
	// public void setFilterable(boolean filterable) {
	// // TODO
	// }
	// public SimpleBooleanProperty filterableProperty() {
	// // TODO
	// }
}

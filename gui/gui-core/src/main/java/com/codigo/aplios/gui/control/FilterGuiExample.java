package com.codigo.aplios.gui.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FilterGuiExample extends Application {

	public enum ExampleType {
		Option1,
		Option2,
		Option3,
		Option4,
		Option5,
		Option6,
		Option7,
		Option8,
		Option9,
		Option10

	};

	private FilteredTableView<ExampleItem> filteredTable;

	private FilterableIntegerTableColumn<ExampleItem, Integer> idColumn;

	private FilterableStringTableColumn<ExampleItem, String> valColumn;

	private FilterableEnumTableColumn<ExampleItem, ExampleType> typeColumn;

	private FilterableDateTableColumn<ExampleItem, Date> dateColumn;

	private FilterableBooleanTableColumn<ExampleItem, Boolean> boolColumn;

	public static void main(final String[] args) {

		Application.launch(args);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage) {

		// The FilteredTableView makes it easier to receive notifications of changes to
		// the column filters
		this.filteredTable = new FilteredTableView<>(this.createItems());

		// Allow an Integer or Integer range as a filter(s)
		this.idColumn = new FilterableIntegerTableColumn<>("ID");
		this.idColumn.setPrefWidth(50);
		this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));

		// Allow for free-form text input as a filter
		this.valColumn = new FilterableStringTableColumn<>("Value");
		this.valColumn.setPrefWidth(90);
		this.valColumn.setCellValueFactory(new PropertyValueFactory("val"));

		// Allow a set of array or enum values to be used selected from
		// Don't let the name fool you, you can pass in any type of object you'd like
		// The toString() method will be called, and used to display the filter menus
		this.typeColumn = new FilterableEnumTableColumn<>("Type", ExampleType.values());
		this.typeColumn.showToggleAll(true);
		this.typeColumn.selectByDefault(true);
		this.typeColumn.setPrefWidth(90);
		this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));

		// Allow a Date or Date range as a filter(s), with an optional date format
		this.dateColumn = new FilterableDateTableColumn<>("Date", "yyyy-MM-dd HH:mm:ss");
		this.dateColumn.setPrefWidth(130);
		this.dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		this.dateColumn.setCellFactory(param -> new TableCell<ExampleItem, Date>() {

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			@Override
			public void updateItem(final Date item, final boolean empty) {

				super.updateItem(item, empty);
				if (!this.isEmpty())
					this.setText(this.sdf.format(item));
				else
					this.setText(null);
			}

		});

		// Allow a Boolean as a filter
		this.boolColumn = new FilterableBooleanTableColumn<>("Bool");
		this.boolColumn.setPrefWidth(70);
		this.boolColumn.setCellValueFactory(new PropertyValueFactory("bool"));

		this.filteredTable.getColumns()
		    .setAll(this.idColumn, this.valColumn, this.typeColumn, this.dateColumn, this.boolColumn);

		// Listen for changes to the table's filters
		this.filteredTable.addEventHandler(ColumnFilterEvent.FILTER_CHANGED_EVENT,
		        (final ColumnFilterEvent<?, ?, ?, ?> t) ->
				{

			        System.out.println("Filtered column count: "
			                + FilterGuiExample.this.filteredTable.getFilteredColumns()
			                    .size());
			        System.out.println("Filtering changed on column: "
			                + t.sourceColumn()
			                    .getText());
			        System.out.println("Current filters on column "
			                + t.sourceColumn()
			                    .getText()
			                + " are:");
			        final ObservableList<? extends IFilterOperator<?>> filters = t.sourceColumn()
			            .getFilters();
			        filters.forEach((filter) -> {
				        System.out.println("  Type=" + filter.getType() + ", Value=" + filter.getValue());
			        });

			        FilterGuiExample.this.applyFilters();
		        });

		final BorderPane pane = new BorderPane(this.filteredTable);
		pane.setTop(new Label("Filtering only works on the ID column in this demo."));
		primaryStage.setScene(new Scene(pane, 600, 200));
		primaryStage.show();
	}

	public void applyFilters() {

		// Filter the data...
		final ObservableList<ExampleItem> newData = this.createItems();
		this.filterIdColumn(newData);
		this.filterValColumn(newData);
		this.filterTypeCol(newData);
		this.filterDateCol(newData);
		this.filterBoolCol(newData);
		// Display the filtered results
		this.filteredTable.getItems()
		    .setAll(newData);
	}

	public void filterIdColumn(final ObservableList<ExampleItem> newData) {

		// Here's an example of how you could filter the ID column
		final List<ExampleItem> remove = new ArrayList<>();
		final ObservableList<NumberOperator<Integer>> filters = this.idColumn.getFilters();
		filters.forEach((filter) -> {
			for (final ExampleItem item : newData)
				if (null != filter.getType()) // Note: not all Type's are supported for each Operator.
					// Look at the validTypes() method to see what types are available.
					switch (filter.getType()) {
						case EQUALS:
							if (item.getId() != filter.getValue())
								remove.add(item);
						break;
						case NOTEQUALS:
							if (item.getId() == filter.getValue())
								remove.add(item);
						break;
						case GREATERTHAN:
							if (item.getId() <= filter.getValue())
								remove.add(item);
						break;
						case GREATERTHANEQUALS:
							if (item.getId() < filter.getValue())
								remove.add(item);
						break;
						case LESSTHAN:
							if (item.getId() >= filter.getValue())
								remove.add(item);
						break;
						case LESSTHANEQUALS:
							if (item.getId() > filter.getValue())
								remove.add(item);
						break;
						default:
						break;
					}
		});

		newData.removeAll(remove);
	}

	public void filterValColumn(final ObservableList<ExampleItem> newData) {

		// Here's an example of how you could filter the ID column
		final List<ExampleItem> remove = new ArrayList<>();
		final ObservableList<StringOperator> filters = this.valColumn.getFilters();
		filters.forEach((filter) -> {
			for (final ExampleItem item : newData)
				if (null != filter.getType()) // Note: not all Type's are supported for each Operator.
					// Look at the validTypes() method to see what types are available.
					switch (filter.getType()) {
						case EQUALS:
							if (!(item.getVal()
							    .equals(filter.getValue())))
								remove.add(item);
						break;
						case NOTEQUALS:
							if (item.getVal()
							    .equals(filter.getValue()))
								remove.add(item);
						break;
						case CONTAINS:
							if (!(item.getVal()
							    .contains(filter.getValue())))
								remove.add(item);
						break;

						case STARTSWITH:
							if (!(item.getVal()
							    .startsWith(filter.getValue())))
								remove.add(item);
						break;

						case ENDSWITH:
							if (!(item.getVal()
							    .endsWith(filter.getValue())))
								remove.add(item);
						break;
						default:
						break;
					}
		});

		newData.removeAll(remove);
	}

	public void filterTypeCol(final ObservableList<ExampleItem> newData) {

		// ... implement
	}

	public void filterDateCol(final ObservableList<ExampleItem> newData) {

		// ... implement
	}

	public void filterBoolCol(final ObservableList<ExampleItem> newData) {

		// ... implement
	}

	@SuppressWarnings("deprecation")
	public ObservableList<ExampleItem> createItems() {

		// Create some dummy date to display
		return FXCollections.observableArrayList(
		        new ExampleItem(1, "Foo", ExampleType.Option1, new Date(113, 0, 20), true),
		        new ExampleItem(2, "Bar", ExampleType.Option2, new Date(112, 3, 11), false),
		        new ExampleItem(3, "Baz", ExampleType.Option3, new Date(113, 5, 29), false),
		        new ExampleItem(4, "Lorem", ExampleType.Option2, new Date(111, 2, 1), true),
		        new ExampleItem(5, "Ipsum", ExampleType.Option3, new Date(113, 1, 1), true),
		        new ExampleItem(6, "Wookie", ExampleType.Option3, new Date(112, 11, 31), false),
		        new ExampleItem(7, "Foo", ExampleType.Option1, new Date(113, 0, 20), true),
		        new ExampleItem(8, "Bar", ExampleType.Option2, new Date(112, 3, 11), false),
		        new ExampleItem(9, "Baz", ExampleType.Option3, new Date(113, 5, 29), false),
		        new ExampleItem(10, "Lorem", ExampleType.Option2, new Date(111, 2, 1), true),
		        new ExampleItem(11, "Ipsum", ExampleType.Option3, new Date(113, 1, 1), true),
		        new ExampleItem(12, "Wookie", ExampleType.Option3, new Date(112, 11, 31), false),
		        new ExampleItem(13, "Foo", ExampleType.Option1, new Date(113, 0, 20), true),
		        new ExampleItem(14, "Bar", ExampleType.Option2, new Date(112, 3, 11), false),
		        new ExampleItem(15, "Baz", ExampleType.Option3, new Date(113, 5, 29), false),
		        new ExampleItem(16, "Lorem", ExampleType.Option2, new Date(111, 2, 1), true),
		        new ExampleItem(17, "Ipsum", ExampleType.Option3, new Date(113, 1, 1), true),
		        new ExampleItem(18, "Wookie", ExampleType.Option3, new Date(112, 11, 31), false),
		        new ExampleItem(19, "Foo", ExampleType.Option1, new Date(113, 0, 20), true),
		        new ExampleItem(20, "Bar", ExampleType.Option2, new Date(112, 3, 11), false),
		        new ExampleItem(21, "Baz", ExampleType.Option3, new Date(113, 5, 29), false),
		        new ExampleItem(22, "Lorem", ExampleType.Option2, new Date(111, 2, 1), true),
		        new ExampleItem(23, "Ipsum", ExampleType.Option3, new Date(113, 1, 1), true),
		        new ExampleItem(24, "Wookie", ExampleType.Option3, new Date(112, 11, 31), false));
	}

	static public class ExampleItem {

		private final SimpleIntegerProperty id;

		private final SimpleStringProperty val;

		private final SimpleObjectProperty<ExampleType> type;

		private final SimpleObjectProperty<Date> date;

		private final SimpleBooleanProperty bool;

		public ExampleItem(final int id, final String val, final ExampleType type, final Date date,
		        final boolean bool) {

			this.id = new SimpleIntegerProperty(id);
			this.val = new SimpleStringProperty(val);
			this.type = new SimpleObjectProperty<>(type);
			this.date = new SimpleObjectProperty<>(date);
			this.bool = new SimpleBooleanProperty(bool);
		}

		public int getId() {

			return this.id.get();
		}

		public void setId(final int id) {

			this.id.set(id);
		}

		public SimpleIntegerProperty idProperty() {

			return this.id;
		}

		public String getVal() {

			return this.val.get();
		}

		public void setVal(final String val) {

			this.val.set(val);
		}

		public SimpleStringProperty valProperty() {

			return this.val;
		}

		public ExampleType getType() {

			return this.type.get();
		}

		public void setId(final ExampleType type) {

			this.type.set(type);
		}

		public SimpleObjectProperty<ExampleType> typeProperty() {

			return this.type;
		}

		public Date getDate() {

			return this.date.get();
		}

		public void setDate(final Date val) {

			this.date.set(val);
		}

		public SimpleObjectProperty<Date> dateProperty() {

			return this.date;
		}

		public Boolean getBool() {

			return this.bool.get();
		}

		public void setBool(final boolean bool) {

			this.bool.set(bool);
		}

		public SimpleBooleanProperty boolProperty() {

			return this.bool;
		}

	}
}

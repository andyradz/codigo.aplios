package com.codigo.aplios.gui.control.grid;

import java.text.Format;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Table with a summary table. The summary table is a 2nd table which is synchronized with the
 * primary table.
 *
 * TODO: + always show vertical bars for both the primary and the summary table, otherweise the
 * width of both tables wouldn't be the same + hide the horizontal scrollbar of the summary table
 *
 */

public class SummaryTableDemo extends Application {

	private final TableView<Data>		mainTable	= new TableView<>();
	private final TableView<SumData>	sumTable	= new TableView<>();

	private final ObservableList<Data> data = FXCollections.observableArrayList(new Data(
		LocalDate.of(2015, Month.JANUARY, 10), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 11), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 12), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 13), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 14), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 15), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 16), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 17), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 18), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 19), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 20), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 21), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 22), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 23), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 24), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 25), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 26), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 27), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 28), 10.0, 20.0, 30.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 29), 40.0, 50.0, 60.0),
			new Data(
				LocalDate.of(2015, Month.JANUARY, 30), 10.0, 20.0, 30.0)

	);

	// TODO: calculate values
	private final ObservableList<SumData> sumData = FXCollections.observableArrayList(new SumData(
		"Sum", 0.0, 0.0, 0.0),
			new SumData(
				"Min", 0.0, 0.0, 0.0),
			new SumData(
				"Max", 0.0, 0.0, 0.0));

	final HBox hb = new HBox();

	public static void main(final String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) {

		final Scene scene = new Scene(
			new Group());

		// load css
		scene.getStylesheets()
				.addAll(SummaryTableDemo.class.getResource("/application1.css")
						.toExternalForm());

		stage.setTitle("Table View Sample");
		stage.setWidth(400);
		stage.setHeight(550);

		// setup table columns
		setupMainTableColumns();
		setupSumTableColumns();

		// fill tables with data
		this.mainTable.setItems(this.data);
		this.sumTable.setItems(this.sumData);

		// set dimensions
		this.sumTable.setPrefHeight(90);

		// bind/sync tables
		for (int i = 0; i < this.mainTable.getColumns()
				.size(); i++) {

			final TableColumn<Data, ?> mainColumn = this.mainTable.getColumns()
					.get(i);
			final TableColumn<SumData, ?> sumColumn = this.sumTable.getColumns()
					.get(i);

			// sync column widths
			sumColumn.prefWidthProperty()
					.bind(mainColumn.widthProperty());

			// sync visibility
			sumColumn.visibleProperty()
					.bind(mainColumn.visibleProperty());

		}

		// allow changing of column visibility
		this.mainTable.setTableMenuButtonVisible(true);

		// hide header (variation of jewelsea's solution:
		// http://stackoverflow.com/questions/12324464/how-to-javafx-hide-background-header-of-a-tableview)
		this.sumTable.getStyleClass()
				.add("tableview-header-hidden");

		// hide horizontal scrollbar via styles
		this.sumTable.getStyleClass()
				.add("sumtable");

		// create container
		final BorderPane bp = new BorderPane();
		bp.setCenter(this.mainTable);
		bp.setBottom(this.sumTable);

		// fit content
		bp.prefWidthProperty()
				.bind(scene.widthProperty());
		bp.prefHeightProperty()
				.bind(scene.heightProperty());

		((Group) scene.getRoot()).getChildren()
				.addAll(bp);

		stage.setScene(scene);
		stage.show();

		// synchronize scrollbars (must happen after table was made visible)
		final ScrollBar mainTableHorizontalScrollBar = findScrollBar(this.mainTable, Orientation.HORIZONTAL);
		final ScrollBar sumTableHorizontalScrollBar = findScrollBar(this.sumTable, Orientation.HORIZONTAL);
		mainTableHorizontalScrollBar.valueProperty()
				.bindBidirectional(sumTableHorizontalScrollBar.valueProperty());

	}

	/**
	 * Primary table column mapping.
	 */
	private void setupMainTableColumns() {

		final TableColumn<Data, LocalDate> dateCol = new TableColumn<>(
			"Date");
		dateCol.setPrefWidth(120);
		dateCol.setCellValueFactory(new PropertyValueFactory<>(
			"date"));

		final TableColumn<Data, Double> value1Col = new TableColumn<>(
			"Value 1");
		value1Col.setPrefWidth(90);
		value1Col.setCellValueFactory(new PropertyValueFactory<>(
			"value1"));
		value1Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		final TableColumn<Data, Double> value2Col = new TableColumn<>(
			"Value 2");
		value2Col.setPrefWidth(90);
		value2Col.setCellValueFactory(new PropertyValueFactory<>(
			"value2"));
		value2Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		final TableColumn<Data, Double> value3Col = new TableColumn<>(
			"Value 3");
		value3Col.setPrefWidth(90);
		value3Col.setCellValueFactory(new PropertyValueFactory<>(
			"value3"));
		value3Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		this.mainTable.getColumns()
				.addAll(dateCol, value1Col, value2Col, value3Col);

	}

	/**
	 * Summary table column mapping.
	 */
	private void setupSumTableColumns() {

		final TableColumn<SumData, String> textCol = new TableColumn<>(
			"Text");
		textCol.setCellValueFactory(new PropertyValueFactory<>(
			"text"));

		final TableColumn<SumData, Double> value1Col = new TableColumn<>(
			"Value 1");
		value1Col.setCellValueFactory(new PropertyValueFactory<>(
			"value1"));
		value1Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		final TableColumn<SumData, Double> value2Col = new TableColumn<>(
			"Value 2");
		value2Col.setCellValueFactory(new PropertyValueFactory<>(
			"value2"));
		value2Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		final TableColumn<SumData, Double> value3Col = new TableColumn<>(
			"Value 3");
		value3Col.setCellValueFactory(new PropertyValueFactory<>(
			"value3"));
		value3Col.setCellFactory(new FormattedTableCellFactory<>(
			TextAlignment.RIGHT));

		this.sumTable.getColumns()
				.addAll(textCol, value1Col, value2Col, value3Col);

	}

	/**
	 * Find the horizontal scrollbar of the given table.
	 *
	 * @param table
	 * @return
	 */
	private ScrollBar findScrollBar(final TableView<?> table, final Orientation orientation) {

		// this would be the preferred solution, but it doesn't work. it always gives
		// back the vertical scrollbar
		// return (ScrollBar) table.lookup(".scroll-bar:horizontal");
		//
		// => we have to search all scrollbars and return the one with the proper
		// orientation

		final Set<Node> set = table.lookupAll(".scroll-bar");
		for (final Node node : set) {
			final ScrollBar bar = (ScrollBar) node;
			if (bar.getOrientation() == orientation)
				return bar;
		}

		return null;

	}

	/**
	 * Data for primary table rows.
	 */
	public static class Data {

		private final ObjectProperty<LocalDate>	date;
		private final SimpleDoubleProperty		value1;
		private final SimpleDoubleProperty		value2;
		private final SimpleDoubleProperty		value3;

		public Data(final LocalDate date, final double value1, final double value2, final double value3) {

			this.date = new SimpleObjectProperty<>(
				date);

			this.value1 = new SimpleDoubleProperty(
				value1);
			this.value2 = new SimpleDoubleProperty(
				value2);
			this.value3 = new SimpleDoubleProperty(
				value3);
		}

		public final ObjectProperty<LocalDate> dateProperty() {

			return this.date;
		}

		public final LocalDate getDate() {

			return dateProperty().get();
		}

		public final void setDate(final LocalDate date) {

			dateProperty().set(date);
		}

		public final SimpleDoubleProperty value1Property() {

			return this.value1;
		}

		public final double getValue1() {

			return value1Property().get();
		}

		public final void setValue1(final double value1) {

			value1Property().set(value1);
		}

		public final SimpleDoubleProperty value2Property() {

			return this.value2;
		}

		public final double getValue2() {

			return value2Property().get();
		}

		public final void setValue2(final double value2) {

			value2Property().set(value2);
		}

		public final SimpleDoubleProperty value3Property() {

			return this.value3;
		}

		public final double getValue3() {

			return value3Property().get();
		}

		public final void setValue3(final double value3) {

			value3Property().set(value3);
		}

	}

	/**
	 * Data for summary table rows.
	 */
	public static class SumData {

		private final SimpleStringProperty	text;
		private final SimpleDoubleProperty	value1;
		private final SimpleDoubleProperty	value2;
		private final SimpleDoubleProperty	value3;

		public SumData(final String text, final double value1, final double value2, final double value3) {

			this.text = new SimpleStringProperty(
				text);

			this.value1 = new SimpleDoubleProperty(
				value1);
			this.value2 = new SimpleDoubleProperty(
				value2);
			this.value3 = new SimpleDoubleProperty(
				value3);
		}

		public final SimpleStringProperty textProperty() {

			return this.text;
		}

		public final java.lang.String getText() {

			return textProperty().get();
		}

		public final void setText(final java.lang.String text) {

			textProperty().set(text);
		}

		public final SimpleDoubleProperty value1Property() {

			return this.value1;
		}

		public final double getValue1() {

			return value1Property().get();
		}

		public final void setValue1(final double value1) {

			value1Property().set(value1);
		}

		public final SimpleDoubleProperty value2Property() {

			return this.value2;
		}

		public final double getValue2() {

			return value2Property().get();
		}

		public final void setValue2(final double value2) {

			value2Property().set(value2);
		}

		public final SimpleDoubleProperty value3Property() {

			return this.value3;
		}

		public final double getValue3() {

			return value3Property().get();
		}

		public final void setValue3(final double value3) {

			value3Property().set(value3);
		}

	}

	/**
	 * Formatter for table cells: allows you to align table cell values left/right/center
	 *
	 * Example for alignment form
	 * http://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm
	 *
	 * @param <S>
	 * @param <T>
	 */
	public static class FormattedTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

		private TextAlignment	alignment	= TextAlignment.LEFT;
		private Format			format;

		public FormattedTableCellFactory() {

		}

		public FormattedTableCellFactory(final TextAlignment alignment) {

			this.alignment = alignment;
		}

		public TextAlignment getAlignment() {

			return this.alignment;
		}

		public void setAlignment(final TextAlignment alignment) {

			this.alignment = alignment;
		}

		public Format getFormat() {

			return this.format;
		}

		public void setFormat(final Format format) {

			this.format = format;
		}

		@Override
		@SuppressWarnings("unchecked")
		public TableCell<S, T> call(final TableColumn<S, T> p) {

			final TableCell<S, T> cell = new TableCell<>() {

				@Override
				public void updateItem(final Object item, final boolean empty) {

					if (item == getItem())
						return;
					super.updateItem((T) item, empty);
					if (item == null) {
						super.setText(null);
						super.setGraphic(null);
					}
					else if (FormattedTableCellFactory.this.format != null)
						super.setText(FormattedTableCellFactory.this.format.format(item));
					else if (item instanceof Node) {
						super.setText(null);
						super.setGraphic((Node) item);
					}
					else {
						super.setText(item.toString());
						super.setGraphic(null);
					}
				}
			};
			cell.setTextAlignment(this.alignment);
			switch (this.alignment) {
			case CENTER:
				cell.setAlignment(Pos.CENTER);
				break;
			case RIGHT:
				cell.setAlignment(Pos.CENTER_RIGHT);
				break;
			default:
				cell.setAlignment(Pos.CENTER_LEFT);
				break;
			}
			return cell;
		}
	}

}

package com.codigo.aplios.gui.control.grid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class KeepTrackOfTableCells extends Application {

	private Map<TableColumn<Item, ?>, Map<Number, TableCell<Item, ?>>> cells;

	private final NumberFormat integerFormat = new DecimalFormat(
		"###,###.#####");

	@Override
	public void start(final Stage primaryStage) {

		this.cells = new HashMap<>();

		final TableView<Item> table = new TableView<>();
		table.getColumns()
				.add(this.column("Item", Item::nameProperty));

		table.getColumns()
				.add(this.column("Value", Item::valueProperty));

		final TableColumn<Item, Number> amountColumn = this.column("Amount", Item::amountProperty);
		// amountColumn.setCellFactory(tc -> new TableCell<Item, Number>() {
		//
		// @Override
		// protected void updateItem(final Number value, final boolean empty) {
		//
		// super.updateItem(value, empty);
		// if (value == null || empty) {
		// this.setText("");
		// }
		// else {
		// this.setText(KeepTrackOfTableCells.this.integerFormat.format(value));
		// }
		// }
		// });

		table.getColumns()
				.add(amountColumn);

		final Random rng = new Random();
		for (int i = 1; i <= 10000; i++)
			table.getItems()
					.add(new Item(
						"Item " + i, rng.nextInt(500), 23983123.09221));

		final TextField indexField = new TextField();
		final TextFormatter<Integer> formatter = new TextFormatter<>(
			new StringConverter<Integer>() {

				@Override
				public String toString(final Integer object) {

					return object.toString();
				}

				@Override
				public Integer fromString(final String string) {

					if (string.isEmpty())
						return 0;
					return Integer.valueOf(string);
				}
			}, 0, (final Change c) -> c.getText()
					.matches("\\d*") ? c : null);
		indexField.setTextFormatter(formatter);
		final RadioButton name = new RadioButton(
			"Item column");
		final RadioButton value = new RadioButton(
			"Value column");
		final RadioButton amount = new RadioButton(
			"Amount column");
		final ToggleGroup tg = new ToggleGroup();
		tg.getToggles()
				.addAll(name, value, amount);
		name.setSelected(true);
		final Button search = new Button(
			"Find");

		final GridPane controls = new GridPane();
		controls.setVgap(5);
		controls.setHgap(5);
		controls.add(new Label(
			"Row (1-based):"), 0, 0);
		controls.add(indexField, 1, 0);
		controls.add(name, 0, 2, 2, 1);
		controls.add(value, 0, 3, 2, 1);
		controls.add(amount, 0, 4, 2, 1);
		controls.add(search, 0, 5);

		final Pane root = new Pane();
		final BorderPane ui = new BorderPane();
		BorderPane.setAlignment(controls, Pos.CENTER);
		BorderPane.setMargin(controls, new Insets(
			5));
		BorderPane.setMargin(table, new Insets(
			5));
		ui.setTop(controls);
		ui.setCenter(table);

		root.getChildren()
				.add(ui);

		search.setOnAction(e -> {

			table.getSelectionModel()
					.selectLast();

			table.scrollTo(table.getItems()
					.size() - 1);

			TableColumn<Item, ?> col = null;
			if (name.isSelected())
				col = table.getColumns()
						.get(0);
			else if (value.isSelected())
				col = table.getColumns()
						.get(1);
			else if (amount.isSelected())
				col = table.getColumns()
						.get(2);
			else
				return;

			final int index = formatter.getValue() - 1;
			final TableCell<Item, ?> cell = this.cells.get(col)
					.get(index);
			if (cell != null) {
				final Bounds bounds = root.sceneToLocal(cell.localToScene(cell.getBoundsInLocal()));
				final double x = (bounds.getMinX() + bounds.getMaxX()) / 2;
				final double y = (bounds.getMinY() + bounds.getMaxY()) / 2;
				final Circle c = new Circle(
					0, 0, 5, Color.BLUE);
				root.getChildren()
						.add(c);
				final Timeline tm = new Timeline(
					new KeyFrame(
						Duration.seconds(1), new KeyValue(
							c.centerXProperty(), x),
						new KeyValue(
							c.centerYProperty(), y)),
					new KeyFrame(
						Duration.seconds(2), evt -> root.getChildren()
								.remove(c)));

				tm.setOnFinished(evt -> {
					search.fire();
				});
				tm.play();
			}
		});

		final Scene scene = new Scene(
			root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private <T> TableColumn<Item, T> column(final String title, final Function<Item, ObservableValue<T>> property) {

		final TableColumn<Item, T> col = new TableColumn<>(
			title);
		col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
		this.cells.put(col, new HashMap<Number, TableCell<Item, ?>>());
		col.setCellFactory(tc -> {
			final TableCell<Item, T> cell = new TableCell<>() {

				@Override
				protected void updateItem(final T item, final boolean empty) {

					super.updateItem(item, empty);
					if (empty)
						setText(null);
					else {
						if (item instanceof DoubleProperty)
							setText(KeepTrackOfTableCells.this.integerFormat.format(item));
						setText(item.toString());
					}
				}
			};
			cell.indexProperty()
					.addListener((obs, oldIndex, newIndex) -> {
						if ((oldIndex != null) && (oldIndex.intValue() != -1))
							this.cells.get(col)
									.remove(oldIndex);
						if ((newIndex != null) && (newIndex.intValue() != -1))
							this.cells.get(col)
									.put(newIndex, cell);
					});
			return cell;
		});
		// if (property instanceof DoubleProperty) {
		col.setSortable(false);
		// }
		return col;
	}

	public static class Item {

		private final StringProperty		name	= new SimpleStringProperty();
		private final IntegerProperty		value	= new SimpleIntegerProperty();
		private final SimpleDoubleProperty	amount	= new SimpleDoubleProperty();

		public Item(final String name, final int value, final double amount) {

			setName(name);
			setValue(value);
			setAmount(amount);
		}

		public StringProperty nameProperty() {

			return this.name;
		}

		public final String getName() {

			return nameProperty().get();
		}

		public final void setName(final String name) {

			nameProperty().set(name);
		}

		public IntegerProperty valueProperty() {

			return this.value;
		}

		public final int getValue() {

			return valueProperty().get();
		}

		public final void setValue(final int value) {

			valueProperty().set(value);
		}

		public DoubleProperty amountProperty() {

			return this.amount;
		}

		public final int getAmount() {

			return valueProperty().get();
		}

		public final void setAmount(final double value) {

			amountProperty().set(value);
		}

	}

	public static void main(final String[] args) {

		Application.launch(args);
	}
}
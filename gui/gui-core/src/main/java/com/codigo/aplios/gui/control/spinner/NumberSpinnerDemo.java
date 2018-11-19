package com.codigo.aplios.gui.control.spinner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NumberSpinnerDemo extends Application {

	/**
	 * @param args
	 *        the command line arguments
	 */
	public static void main(final String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {

		primaryStage.setTitle("JavaFX Spinner Demo");
		final GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(
			1, 1, 1, 1));
		final NumberSpinner defaultSpinner = new NumberSpinner();

		final NumberSpinner decimalFormat = new NumberSpinner(
			BigDecimal.ZERO, new BigDecimal(
				"0.05"),
			new DecimalFormat(
				"#,##0.00"));
		final NumberSpinner percent = new NumberSpinner(
			BigDecimal.ZERO, new BigDecimal(
				"0.01"),
			NumberFormat.getPercentInstance());
		final NumberSpinner localizedCurrency = new NumberSpinner(
			BigDecimal.ZERO, new BigDecimal(
				"0.01"),
			NumberFormat.getCurrencyInstance(Locale.getDefault()));
		root.addRow(1, new Label(
			"default"), defaultSpinner);
		root.addRow(2, new Label(
			"custom decimal format"), decimalFormat);
		root.addRow(3, new Label(
			"percent"), percent);
		root.addRow(4, new Label(
			"localized currency"), localizedCurrency);

		final Button button = new Button(
			"Dump layout bounds");
		button.setOnAction(arg0 -> defaultSpinner.dumpSizes());
		root.addRow(5, new Label(), button);

		final Scene scene = new Scene(
			root);
		final String path = NumberSpinnerDemo.class.getResource("number_spinner.css")
				.toExternalForm();
		System.out.println("path=" + path);
		scene.getStylesheets()
				.add(path);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}

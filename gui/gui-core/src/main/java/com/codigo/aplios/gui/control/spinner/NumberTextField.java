package com.codigo.aplios.gui.control.spinner;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * Textfield implementation that accepts formatted number and stores them in a BigDecimal property
 * The user input is formatted when the focus is lost or the user hits RETURN.
 *
 * @author Thomas Bolz
 */
public class NumberTextField extends TextField {

	private final NumberFormat					nf;
	private final ObjectProperty<BigDecimal>	number	= new SimpleObjectProperty<>();

	public final BigDecimal getNumber() {

		return this.number.get();
	}

	public final void setNumber(final BigDecimal value) {

		this.number.set(value);
	}

	public ObjectProperty<BigDecimal> numberProperty() {

		return this.number;
	}

	public NumberTextField() {

		this(BigDecimal.ZERO);
	}

	public NumberTextField(final BigDecimal value) {

		this(value, NumberFormat.getInstance());
		initHandlers();
	}

	public NumberTextField(final BigDecimal value, final NumberFormat nf) {

		super();
		this.nf = nf;
		setEditable(false);
		initHandlers();
		setNumber(value);
	}

	private void initHandlers() {

		final EventHandler<ActionEvent> handler = (input) -> parseAndFormatInput();

		// try to parse when focus is lost or RETURN is hit
		setOnAction(handler);

		focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {

			if (!newValue.booleanValue())
				parseAndFormatInput();
		});

		// Set text in field if BigDecimal property is changed from outside.
		numberProperty().addListener((ChangeListener<BigDecimal>) (obserable, oldValue,
				newValue) -> setText(NumberTextField.this.nf.format(newValue)));
	}

	/**
	 * Tries to parse the user input to a number according to the provided NumberFormat
	 */
	private void parseAndFormatInput() {

		try {

			final String input = getText();

			if ((input == null) || (input.length() == 0))
				return;

			final Number parsedNumber = this.nf.parse(input);
			final BigDecimal newValue = new BigDecimal(
				parsedNumber.toString());
			setNumber(newValue);
			selectAll();

		}
		catch (final ParseException ex) {
			// If parsing fails keep old number
			setText(this.nf.format(this.number.get()));
		}
	}
}
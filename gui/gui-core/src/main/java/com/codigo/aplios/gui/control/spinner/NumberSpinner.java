package com.codigo.aplios.gui.control.spinner;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JSpinner;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * JavaFX Control that behaves like a {@link JSpinner} known in Swing. The number in the textfield
 * can be incremented or decremented by a configurable stepWidth using the arrow buttons in the
 * control or the up and down arrow keys.
 *
 * @author Thomas Bolz
 */
public class NumberSpinner extends HBox {

	public static final String					ARROW				= "NumberSpinnerArrow";
	public static final String					NUMBER_FIELD		= "NumberField";
	public static final String					NUMBER_SPINNER		= "NumberSpinner";
	public static final String					SPINNER_BUTTON_UP	= "SpinnerButtonUp";
	public static final String					SPINNER_BUTTON_DOWN	= "SpinnerButtonDown";
	private final String						BUTTONS_BOX			= "ButtonsBox";
	private final NumberTextField				numberField;
	private final ObjectProperty<BigDecimal>	stepWitdhProperty	= new SimpleObjectProperty<>();
	private final double						ARROW_SIZE			= 4;
	private final Button						incrementButton;
	private final Button						decrementButton;
	private final NumberBinding					buttonHeight;
	private final NumberBinding					spacing;

	public NumberSpinner() {

		this(BigDecimal.ZERO, BigDecimal.ONE);
	}

	public NumberSpinner(final BigDecimal value, final BigDecimal stepWidth) {

		this(value, stepWidth, NumberFormat.getInstance());
	}

	public NumberSpinner(final BigDecimal value, final BigDecimal stepWidth, final NumberFormat nf) {

		super();
		setId(NumberSpinner.NUMBER_SPINNER);
		this.stepWitdhProperty.set(stepWidth);

		// TextField
		this.numberField = new NumberTextField(
			value, nf);
		this.numberField.setId(NumberSpinner.NUMBER_FIELD);

		// Enable arrow keys for dec/inc
		this.numberField.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

			if (keyEvent.getCode() == KeyCode.DOWN) {
				decrement();
				keyEvent.consume();
			}
			if (keyEvent.getCode() == KeyCode.UP) {
				increment();
				keyEvent.consume();
			}
		});

		// Painting the up and down arrows
		final Path arrowUp = new Path();
		arrowUp.setId(NumberSpinner.ARROW);
		arrowUp.getElements()
				.addAll(new MoveTo(
					-this.ARROW_SIZE, 0),
						new LineTo(
							this.ARROW_SIZE, 0),
						new LineTo(
							0, -this.ARROW_SIZE),
						new LineTo(
							-this.ARROW_SIZE, 0));
		// mouse clicks should be forwarded to the underlying button
		arrowUp.setMouseTransparent(true);

		final Path arrowDown = new Path();
		arrowDown.setId(NumberSpinner.ARROW);
		arrowDown.getElements()
				.addAll(new MoveTo(
					-this.ARROW_SIZE, 0),
						new LineTo(
							this.ARROW_SIZE, 0),
						new LineTo(
							0, this.ARROW_SIZE),
						new LineTo(
							-this.ARROW_SIZE, 0));
		arrowDown.setMouseTransparent(true);

		// the spinner buttons scale with the textfield size
		// TODO: the following approach leads to the desired result, but it is
		// not fully understood why and obviously it is not quite elegant
		this.buttonHeight = this.numberField.heightProperty()
				.subtract(3)
				.divide(2);
		// give unused space in the buttons VBox to the incrementBUtton
		this.spacing = this.numberField.heightProperty()
				.subtract(2)
				.subtract(this.buttonHeight.multiply(2));

		// inc/dec buttons
		final VBox buttons = new VBox();
		buttons.setId(this.BUTTONS_BOX);
		this.incrementButton = new Button();
		this.incrementButton.setId(NumberSpinner.SPINNER_BUTTON_UP);
		this.incrementButton.prefWidthProperty()
				.bind(this.numberField.heightProperty());
		this.incrementButton.minWidthProperty()
				.bind(this.numberField.heightProperty());
		this.incrementButton.maxHeightProperty()
				.bind(this.buttonHeight.add(this.spacing));
		this.incrementButton.prefHeightProperty()
				.bind(this.buttonHeight.add(this.spacing));
		this.incrementButton.minHeightProperty()
				.bind(this.buttonHeight.add(this.spacing));
		this.incrementButton.setFocusTraversable(false);
		this.incrementButton.setOnAction(ae -> {

			increment();
			ae.consume();
		});

		// Paint arrow path on button using a StackPane
		final StackPane incPane = new StackPane();
		incPane.getChildren()
				.addAll(this.incrementButton, arrowUp);
		incPane.setAlignment(Pos.CENTER);

		this.decrementButton = new Button();
		this.decrementButton.setId(NumberSpinner.SPINNER_BUTTON_DOWN);
		this.decrementButton.prefWidthProperty()
				.bind(this.numberField.heightProperty());
		this.decrementButton.minWidthProperty()
				.bind(this.numberField.heightProperty());
		this.decrementButton.maxHeightProperty()
				.bind(this.buttonHeight);
		this.decrementButton.prefHeightProperty()
				.bind(this.buttonHeight);
		this.decrementButton.minHeightProperty()
				.bind(this.buttonHeight);

		this.decrementButton.setFocusTraversable(false);
		this.decrementButton.setOnAction(ae -> {

			decrement();
			ae.consume();
		});

		final StackPane decPane = new StackPane();
		decPane.getChildren()
				.addAll(this.decrementButton, arrowDown);
		decPane.setAlignment(Pos.CENTER);

		buttons.getChildren()
				.addAll(incPane, decPane);
		getChildren().addAll(this.numberField, buttons);
	}

	/**
	 * increment number value by stepWidth
	 */
	private void increment() {

		BigDecimal value = this.numberField.getNumber();
		value = value.add(this.stepWitdhProperty.get());
		this.numberField.setNumber(value);
	}

	/**
	 * decrement number value by stepWidth
	 */
	private void decrement() {

		BigDecimal value = this.numberField.getNumber();
		value = value.subtract(this.stepWitdhProperty.get());
		this.numberField.setNumber(value);
	}

	public final void setNumber(final BigDecimal value) {

		this.numberField.setNumber(value);
	}

	public ObjectProperty<BigDecimal> numberProperty() {

		return this.numberField.numberProperty();
	}

	public final BigDecimal getNumber() {

		return this.numberField.getNumber();
	}

	// debugging layout bounds
	public void dumpSizes() {

		System.out.println("numberField (layout)=" + this.numberField.getLayoutBounds());
		System.out.println("buttonInc (layout)=" + this.incrementButton.getLayoutBounds());
		System.out.println("buttonDec (layout)=" + this.decrementButton.getLayoutBounds());
		System.out.println("binding=" + this.buttonHeight.toString());
		System.out.println("spacing=" + this.spacing.toString());
	}
}
package com.codigo.aplios.gui.control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

/**
 * @author Christian Schudt
 */
public class DatePicker extends HBox {

	private static final String CSS_DATE_PICKER_VALID = "datepicker-valid";

	private static final String CSS_DATE_PICKER_INVALID = "datepicker-invalid";

	/**
	 * Initializes the date picker with the default locale.
	 */
	public DatePicker() {

		this(Locale.getDefault());
	}

	private Timer timer;

	/**
	 * Initializes the date picker with the given locale.
	 *
	 * @param locale
	 *        The locale.
	 */
	public DatePicker(final Locale locale) {

		this.calendarView = new CalendarView(
			locale);

		this.textField = new TextField();
		this.locale.set(locale);

		this.calendarView.setEffect(new DropShadow());

		// Use the same locale.
		this.calendarView.localeProperty()
				.bind(localeProperty());

		// Bind the current date of the calendar view with the selected date, so that
		// the calendar shows up with the same month as in the text field.
		this.calendarView.currentDateProperty()
				.bind(selectedDateProperty());

		// When the user selects a date in the calendar view, hide it.
		this.calendarView.selectedDateProperty()
				.addListener((InvalidationListener) observable -> {
					DatePicker.this.selectedDate.set(DatePicker.this.calendarView.selectedDateProperty()
							.get());
					DatePicker.this.hidePopup();
				});

		// Let the prompt text property listen to locale or date format changes.
		this.textField.promptTextProperty()
				.bind(new StringBinding() {

					{
						super.bind(DatePicker.this.localeProperty(), DatePicker.this.promptTextProperty(),
								DatePicker.this.dateFormatProperty());
					}

					@Override
					protected String computeValue() {

						// First check, if there is a custom prompt text.
						if (DatePicker.this.promptTextProperty()
								.get() != null)
							return DatePicker.this.promptTextProperty()
									.get();

						// If not, use the the date format's pattern.
						final DateFormat dateFormat = DatePicker.this.getActualDateFormat();
						if (dateFormat instanceof SimpleDateFormat)
							return ((SimpleDateFormat) dateFormat).toPattern();

						return "";
					}

				});

		// Change the CSS styles, when this control becomes invalid.
		this.invalid.addListener((InvalidationListener) observable -> {
			if (DatePicker.this.invalid.get()) {
				DatePicker.this.textField.getStyleClass()
						.add(DatePicker.CSS_DATE_PICKER_INVALID);
				DatePicker.this.textField.getStyleClass()
						.remove(DatePicker.CSS_DATE_PICKER_VALID);
			}
			else {
				DatePicker.this.textField.getStyleClass()
						.remove(DatePicker.CSS_DATE_PICKER_INVALID);
				DatePicker.this.textField.getStyleClass()
						.add(DatePicker.CSS_DATE_PICKER_VALID);
			}
		});

		// When the text field no longer has the focus, try to parse the date.
		this.textField.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			if (!DatePicker.this.textField.focusedProperty()
					.get())
				if (!DatePicker.this.textField.getText()
						.isEmpty())
					DatePicker.this.tryParse(true);
		});

		// Listen to user input.
		this.textField.textProperty()
				.addListener((ChangeListener<String>) (observableValue, s, s1) -> {
					// Only evaluate the input, it it wasn't set programmatically.
					if (DatePicker.this.textSetProgrammatically)
						return;

					if (DatePicker.this.timer != null)
						DatePicker.this.timer.cancel();

					// If the user clears the text field, set the date to null and the field to
					// valid.
					if (s1.isEmpty()) {
						DatePicker.this.selectedDate.set(null);
						DatePicker.this.invalid.set(false);
					}
					else {
						// Start a timer, so that the user input is not evaluated immediately, but after
						// a second.
						// This way, input like 01/01/1 is not immediately parsed as 01/01/01.
						// The user gets one second time, to complete his date, maybe his intention was
						// to enter 01/01/12.
						DatePicker.this.timer = new Timer();
						DatePicker.this.timer.schedule(new TimerTask() {

							@Override
							public void run() {

								Platform.runLater(() -> DatePicker.this.tryParse(false));
							}

						}, 1000);
					}
				});

		selectedDateProperty().addListener((InvalidationListener) observable -> {
			DatePicker.this.updateTextField();
			DatePicker.this.invalid.set(false);
		});

		localeProperty().addListener((InvalidationListener) observable -> DatePicker.this.updateTextField());

		// textField.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
		// {
		// @Override
		// public void handle(KeyEvent keyEvent) {
		// if (keyEvent.getCode() == KeyCode.DOWN) {
		// showPopup();
		// }
		// }
		// });
		final Button button = new Button(
			">");
		button.getStyleClass()
				.add("calendar-popup-button");
		button.setFocusTraversable(false);
		button.setOnAction(actionEvent -> DatePicker.this.showPopup());

		getStyleClass().add("calendar-editor");
		getChildren().add(this.textField);
		getChildren().add(button);
	}

	private void hidePopup() {

		if (this.popup != null)
			this.popup.hide();
	}

	/**
	 * Tries to parse the text field for a valid date.
	 *
	 * @param setDateToNullOnException
	 *        True, if the date should be set to null, when a {@link ParseException} occurs. This is the
	 *        case, when the text field loses focus.
	 */
	private void tryParse(final boolean setDateToNullOnException) {

		if (this.timer != null)
			this.timer.cancel();
		try {
			// Double parse the date here, since e.g. 01.01.1 is parsed as year 1, and then
			// formatted as 01.01.01 and then parsed as year 2001.
			// This might lead to an undesired date.
			final DateFormat dateFormat = getActualDateFormat();
			Date parsedDate = dateFormat.parse(this.textField.getText());
			parsedDate = dateFormat.parse(dateFormat.format(parsedDate));
			if ((this.selectedDate.get() == null)
					|| ((this.selectedDate.get() != null) && (parsedDate.getTime() != this.selectedDate.get()
							.getTime())))
				this.selectedDate.set(parsedDate);
			this.invalid.set(false);
			updateTextField();
		}
		catch (final ParseException e) {
			this.invalid.set(true);
			if (setDateToNullOnException)
				this.selectedDate.set(null);
		}

	}

	private boolean textSetProgrammatically;

	/**
	 * Updates the text field.
	 */
	private void updateTextField() {

		// Mark the we update the text field (and not the user), so that it can be
		// ignored, by textField.textProperty()
		this.textSetProgrammatically = true;
		if (selectedDateProperty().get() != null) {
			final String date = getActualDateFormat().format(selectedDateProperty().get());
			if (!this.textField.getText()
					.equals(date))
				this.textField.setText(date);
		}
		else
			this.textField.setText("");
		this.textSetProgrammatically = false;
	}

	/**
	 * Gets the actual date format. If {@link #dateFormatProperty()} is set, take it, otherwise get a
	 * default format for the current locale.
	 *
	 * @return The date format.
	 */
	private DateFormat getActualDateFormat() {

		if (this.dateFormat.get() != null)
			return this.dateFormat.get();

		final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, this.locale.get());
		format.setCalendar(this.calendarView.getCalendar());
		format.setLenient(false);

		return format;
	}

	private CalendarView calendarView;

	/**
	 * Use this to set further properties of the calendar.
	 *
	 * @return The calendar view.
	 */
	public CalendarView getCalendarView() {

		return this.calendarView;
	}

	private TextField textField;

	private final BooleanProperty invalid = new SimpleBooleanProperty();

	/**
	 * States whether the user input is invalid (is no valid date).
	 *
	 * @return The property.
	 */
	public ReadOnlyBooleanProperty invalidProperty() {

		return this.invalid;
	}

	/**
	 * The locale.
	 *
	 * @return The property.
	 */
	public ObjectProperty<Locale> localeProperty() {

		return this.locale;
	}

	private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

	public void setLocale(final Locale locale) {

		this.locale.set(locale);
	}

	public Locale getLocale() {

		return this.locale.get();
	}

	/**
	 * The selected date.
	 *
	 * @return The property.
	 */
	public ObjectProperty<Date> selectedDateProperty() {

		return this.selectedDate;
	}

	private final ObjectProperty<Date> selectedDate = new SimpleObjectProperty<>();

	public void setSelectedDate(final Date date) {

		this.selectedDate.set(date);
	}

	public Date getSelectedDate() {

		return this.selectedDate.get();
	}

	/**
	 * Gets the date format.
	 *
	 * @return The date format.
	 */
	public ObjectProperty<DateFormat> dateFormatProperty() {

		return this.dateFormat;
	}

	private final ObjectProperty<DateFormat> dateFormat = new SimpleObjectProperty<>();

	public void setDateFormat(final DateFormat dateFormat) {

		this.dateFormat.set(dateFormat);
	}

	public DateFormat getDateFormat() {

		return this.dateFormat.get();
	}

	private final StringProperty promptText = new SimpleStringProperty();

	/**
	 * The prompt text for the text field. By default, the prompt text is taken from the date format
	 * pattern.
	 *
	 * @return The property.
	 */
	public StringProperty promptTextProperty() {

		return this.promptText;
	}

	public void setPromptText(final String promptText) {

		this.promptText.set(promptText);
	}

	public String getPromptText() {

		return this.promptText.get();
	}

	private Popup popup;

	/**
	 * Shows the pop up.
	 */
	private void showPopup() {

		if (this.popup == null) {
			this.popup = new Popup();
			this.popup.setAutoHide(true);
			this.popup.setHideOnEscape(true);
			this.popup.setAutoFix(true);
			this.popup.getContent()
					.add(this.calendarView);
		}

		final Bounds calendarBounds = this.calendarView.getBoundsInLocal();
		final Bounds bounds = this.localToScene(getBoundsInLocal());

		final double posX = calendarBounds.getMinX() + bounds.getMinX() + getScene().getX() + getScene().getWindow()
				.getX();
		final double posY = calendarBounds.getMinY() + bounds.getHeight() + bounds.getMinY() + getScene().getY()
				+ getScene().getWindow()
						.getY();

		this.popup.show(this, posX, posY);
	}

}

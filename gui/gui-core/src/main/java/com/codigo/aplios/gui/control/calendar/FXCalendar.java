package com.codigo.aplios.gui.control.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

//https://github.com/dlemmermann/CalendarFX

public class FXCalendar extends HBox {

	private final SimpleIntegerProperty			selectedDate		= new SimpleIntegerProperty();
	private final SimpleIntegerProperty			selectedMonth		= new SimpleIntegerProperty();
	private final SimpleIntegerProperty			selectedYear		= new SimpleIntegerProperty();
	private final SimpleBooleanProperty			triggered			= new SimpleBooleanProperty();
	private final SimpleObjectProperty<Color>	baseColor			= new SimpleObjectProperty<>();
	private final SimpleDoubleProperty			dateTextWidth		= new SimpleDoubleProperty(
		74);
	private final SimpleObjectProperty<Date>	value				= new SimpleObjectProperty<>();
	private boolean								showWeekNumber;
	private FXCalendarUtility					fxCalendarUtility;
	private DateTextField						dateTxtField;
	private ChangeListener<Boolean>				focusOutListener;
	private Popup								popup;
	private DatePicker							datePicker;
	private final SimpleObjectProperty<Locale>	locale				= new SimpleObjectProperty<>();
	private final String						DEFAULT_STYLE_CLASS	= "fx-calendar";

	public FXCalendar() {

		super();
		super.getStyleClass().add(this.DEFAULT_STYLE_CLASS);
		this.locale.set(Locale.ENGLISH);
		this.baseColor.set(Color.web("#313131"));
		// setSpacing(6);
		setAlignment(Pos.CENTER);
		configureCalendar();
		configureListeners();
	}

	private void configureCalendar() {

		final DateFormatValidator dateFormatValidator = new DateFormatValidator();
		this.fxCalendarUtility = new FXCalendarUtility();

		this.popup = new Popup();
		this.popup.setAutoHide(true);
		this.popup.setAutoFix(true);
		this.popup.setHideOnEscape(true);

		addEventFilter(KeyEvent.KEY_PRESSED, event -> {

			if (KeyCode.UP.equals(event.getCode()) || KeyCode.DOWN.equals(event.getCode())
					|| KeyCode.ENTER.equals(event.getCode())) {
				initiatePopUp();
				showPopup();
			}
			else if (KeyCode.TAB.equals(event.getCode()))
				hidePopup();
		});

		/* Creating the date text field. */
		this.dateTxtField = new DateTextField();
		this.dateTxtField.prefWidthProperty()
				.bind(this.dateTextWidth);
		prefWidthProperty().bind(this.dateTextWidth.add(26));
		this.focusOutListener = (arg0, arg1, arg2) -> {

			// Handling only when focus is out.
			if (!arg2) {
				final String value = FXCalendar.this.dateTxtField.getText();
				if (!dateFormatValidator.isValid(value)) {
					clear(); // TODO : Error styling for invalid date format.
					FXCalendar.this.dateTxtField.setText(value);
				}
				else {
					final Date date = FXCalendar.this.fxCalendarUtility.convertStringtoDate(value);
					if (date != null)
						setValue(date);
					else
						// TODO : Error styling the text field for invalid date
						// entry.
						clear();
				}
			}
		};
		this.dateTxtField.focusedProperty()
				.addListener(this.focusOutListener);

		/* Creating the date button. */
		final Button popupButton = new Button();
		popupButton.getStyleClass()
				.add("dateButton");
		popupButton.setGraphic(FXCalendarUtility.getDateImage());
		popupButton.setFocusTraversable(false);
		popupButton.setOnAction(paramT -> {

			initiatePopUp();
			showPopup();
		});

		getChildren().addAll(this.dateTxtField, popupButton);
	}

	private void configureListeners() {

		/* Adding listeners when the date cell is selected. */
		triggeredProperty().addListener((ChangeListener<Boolean>) (paramObservableValue, paramT1, paramT2) -> {

			if (paramT2) {
				final FXCalendarUtility cu = new FXCalendarUtility();
				final Integer day = selectedDateProperty().get();
				final Integer month = selectedMonthProperty().get();
				final Integer year = selectedYearProperty().get();
				if ((day != 0) && (month > -1) && (year != 0)) {
					final String d = cu.getFormattedDate(day, month, year);
					valueProperty().set(cu.convertStringtoDate(d));
				}
				setTriggered(false);
			}
		});

		/*
		 * Changes to be done in text box on change of seletedDate , selectedMonth and selectedYear in
		 * DatePicker.
		 */
		final ChangeListener<Object> listener = (arg0, arg1, arg2) -> showDateInTextField();

		selectedDateProperty().addListener(listener);
		selectedMonthProperty().addListener(listener);
		selectedYearProperty().addListener(listener);
		showDateInTextField();

		/* Adding change listeners for locale. */
		final ChangeListener<Locale> localeListener = (arg0, arg1, arg2) -> {

			if (FXCalendar.this.datePicker != null)
				refreshLocale(arg2);
		};
		localeProperty().addListener(localeListener);

		/* Adding listeners for styles. */
		getStyleClass().addListener((ListChangeListener<String>) paramChange -> {

			FXCalendar.this.dateTxtField.getStyleClass()
					.clear();
			FXCalendar.this.dateTxtField.getStyleClass()
					.addAll("text-input", "text-field");
			for (final String clazz : getStyleClass())
				if (!clazz.equals(FXCalendar.this.DEFAULT_STYLE_CLASS))
					FXCalendar.this.dateTxtField.getStyleClass()
							.add(clazz);
		});
	}

	public void showDateInTextField() {

		final int date = selectedDateProperty().get();
		final int month = selectedMonthProperty().get();
		final int year = selectedYearProperty().get();
		if ((date != 0) && (month != -1) && (year != 0))
			this.dateTxtField.setText(this.fxCalendarUtility.getFormattedDate(date, month, year));
		else
			this.dateTxtField.setText("");
	}

	public void refreshLocale(final Locale locale) {

		this.fxCalendarUtility.resetShortestWeekDays(locale);
		this.fxCalendarUtility.resetShortMonths(locale);
		this.fxCalendarUtility.resetMonths(locale);
		this.datePicker.getBasePane()
				.setLabelText();
		this.datePicker.getBasePane()
				.setWeekLabels();
		this.datePicker.getTopPane()
				.setTopMonths();
	}

	/**
	 * Method to initiate the pop up before showing.
	 */
	private void initiatePopUp() {

		if (this.datePicker == null) {
			this.datePicker = new DatePicker(
				FXCalendar.this);
			this.popup.getContent()
					.add(this.datePicker);
		}

		// If there is no date selected, then setting the system date.
		if (FXCalendar.this.getSelectedYear() == 0) {
			final Calendar currentDate = FXCalendarUtility.getCurrentDateCalendar();
			this.datePicker.selectedDateProperty()
					.set(currentDate.get(Calendar.DAY_OF_MONTH));
			this.datePicker.selectedMonthProperty()
					.set(currentDate.get(Calendar.MONTH));
			this.datePicker.selectedYearProperty()
					.set(currentDate.get(Calendar.YEAR));
		}
		else {
			// Copying the date from calendar to date picker.
			this.datePicker.selectedDateProperty()
					.set(selectedDateProperty().get());
			this.datePicker.selectedMonthProperty()
					.set(selectedMonthProperty().get());
			this.datePicker.selectedYearProperty()
					.set(selectedYearProperty().get());
		}

		this.datePicker.getBasePane()
				.generateDates();
		this.datePicker.showBasePane();
	}

	/**
	 * Method to show the pop up.
	 */
	private void showPopup() {

		final Parent parent = getParent();
		final Bounds childBounds = getBoundsInParent();
		final Bounds parentBounds = parent.localToScene(parent.getBoundsInLocal());
		final double layoutX = childBounds.getMinX() + parentBounds.getMinX() + parent.getScene()
				.getX()
				+ parent.getScene()
						.getWindow()
						.getX();
		final double layoutY = childBounds.getMaxY() + parentBounds.getMinY() + parent.getScene()
				.getY()
				+ parent.getScene()
						.getWindow()
						.getY();
		this.popup.show(this, layoutX, layoutY);
	}

	/**
	 * Method to hide the pop up.
	 */
	public void hidePopup() {

		this.popup.hide();
	}

	/**
	 * @return the baseColor
	 */
	public Color getBaseColor() {

		return this.baseColor.get();
	}

	/**
	 * @param baseColor
	 *        the baseColor to set
	 */
	public void setBaseColor(final Color color) {

		this.baseColor.set(color);
	}

	/**
	 * @return baseColor Property
	 */
	public SimpleObjectProperty<Color> baseColorProperty() {

		return this.baseColor;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {

		return this.locale.get();
	}

	/**
	 * @param locale
	 *        the locale to set
	 */
	public void setLocale(final Locale locale) {

		this.locale.set(locale);
	}

	/**
	 * @return locale Property
	 */
	public SimpleObjectProperty<Locale> localeProperty() {

		return this.locale;
	}

	/**
	 * @return the dateTextWidth
	 */
	public Double getDateTextWidth() {

		return this.dateTextWidth.get();
	}

	/**
	 * @param dateTextWidth
	 *        the dateTextWidth to set
	 */
	public void setDateTextWidth(final Double width) {

		this.dateTextWidth.set(width);
	}

	/**
	 * @return dateTextWidth Property
	 */
	public SimpleDoubleProperty dateTextWidthProperty() {

		return this.dateTextWidth;
	}

	public int getSelectedDate() {

		return this.selectedDate.get();
	}

	public int getSelectedMonth() {

		return this.selectedMonth.get();
	}

	public int getSelectedYear() {

		return this.selectedYear.get();
	}

	public void setSelectedDate(final int selectedDate) {

		this.selectedDate.set(selectedDate);
	}

	public void setSelectedMonth(final int selectedMonth) {

		this.selectedMonth.set(selectedMonth);
	}

	public void setSelectedYear(final int selectedYear) {

		this.selectedYear.set(selectedYear);
	}

	public SimpleIntegerProperty selectedDateProperty() {

		return this.selectedDate;
	}

	public SimpleIntegerProperty selectedMonthProperty() {

		return this.selectedMonth;
	}

	public SimpleIntegerProperty selectedYearProperty() {

		return this.selectedYear;
	}

	public boolean getShowWeekNumber() {

		return this.showWeekNumber;
	}

	public void setShowWeekNumber(final boolean showWeekNumber) {

		this.showWeekNumber = showWeekNumber;
	}

	/**
	 * @return the value
	 */
	public Date getValue() {

		return this.value.get();
	}

	/**
	 * @param value
	 *        the value to set
	 */
	public void setValue(final Date date) {

		this.value.set(date);
		if (date != null) {
			final Calendar c = FXCalendarUtility.getDateCalendar(date);
			selectedDateProperty().set(c.get(Calendar.DAY_OF_MONTH));
			selectedMonthProperty().set(c.get(Calendar.MONTH));
			selectedYearProperty().set(c.get(Calendar.YEAR));
		}
		else {
			selectedDateProperty().set(0);
			selectedMonthProperty().set(0);
			selectedYearProperty().set(0);
		}
	}

	/**
	 * Method to clear the value in the calendar.
	 */
	public void clear() {

		setValue(null);
	}

	public SimpleObjectProperty<Date> valueProperty() {

		return this.value;
	}

	public FXCalendarUtility getFXCalendarUtility() {

		return this.fxCalendarUtility;
	}

	public void setTriggered(final Boolean triggered) {

		this.triggered.set(triggered);
	}

	public SimpleBooleanProperty triggeredProperty() {

		return this.triggered;
	}

	public TextField getTextField() {

		return this.dateTxtField;
	}

	public ChangeListener<Boolean> getFocusOutListener() {

		return this.focusOutListener;
	}

	/**
	 * Cell Inteface
	 *
	 * @author Sai.Dandem
	 *
	 * @param <ItemType>
	 */
	public static interface Cell {
		Node getNode();

		void updateItem(String item);

	}

	/**
	 * Simple Cell Class
	 *
	 * @author Sai.Dandem
	 *
	 * @param <ItemType>
	 */
	public static class DateTextField extends TextField implements Cell {
		public DateTextField() {

			setEditable(true);
			setPrefHeight(22);
			setPromptText("Select Date");
		}

		@Override
		public Node getNode() {

			return this;
		}

		@Override
		public void updateItem(final String item) {

			setText(Optional.ofNullable(item)
					.orElse(""));
		}
	}

}

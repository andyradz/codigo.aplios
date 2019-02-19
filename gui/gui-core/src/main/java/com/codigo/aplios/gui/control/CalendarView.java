package com.codigo.aplios.gui.control;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A calendar control
 *
 * @author Christian Schudt
 */
public class CalendarView extends VBox {

	private static final String CSS_CALENDAR_FOOTER = "calendar-footer";

	private static final String CSS_CALENDAR = "calendar";

	private static final String CSS_CALENDAR_TODAY_BUTTON = "calendar-today-button";

	/**
	 * Initializes a calendar with the default locale.
	 */
	public CalendarView() {

		this(
				Locale.getDefault());
	}

	/**
	 * Initializes a calendar with the given locale. E.g. if the locale is en-US, the calendar starts
	 * the days on Sunday. If it is de-DE the calendar starts the days on Monday.
	 * <p/>
	 * Note that the Java implementation only knows
	 *
	 * @param locale
	 *        The locale.
	 */
	public CalendarView(final Locale locale) {

		this(
				locale,
				Calendar.getInstance(locale));

		// When the locale changes, also change the calendar.
		this.locale.addListener((InvalidationListener) observable -> CalendarView.this.calendar
				.set(Calendar.getInstance(CalendarView.this.localeProperty()
						.get())));
	}

	/**
	 * Initializes the control with the given locale and the given calendar.
	 * <p/>
	 * This way, you can pass a custom calendar (e.g. you could implement the Hijri Calendar for the
	 * arabic world). Or you can use an American style calendar (starting with Sunday as first day of
	 * the week) together with another language.
	 * <p/>
	 * The locale determines the date format.
	 *
	 * @param locale
	 *        The locale.
	 * @param calendar
	 *        The calendar
	 */
	public CalendarView(final Locale locale, final Calendar calendar) {

		this.locale.set(locale);
		this.calendar.set(calendar);

		getStyleClass().add(CalendarView.CSS_CALENDAR);

		setMaxWidth(Region.USE_PREF_SIZE);

		this.currentlyViewing.set(Calendar.MONTH);

		this.calendarDate.addListener(
				(InvalidationListener) observable -> calendar.setTime(CalendarView.this.calendarDate.get()));
		this.calendarDate.set(new Date());
		this.currentDate.addListener((InvalidationListener) observable -> {
			Date date = new Date();
			if (CalendarView.this.currentDate.get() != null)
				date = CalendarView.this.currentDate.get();
			CalendarView.this.calendarDate.set(date);
		});
		final MainStackPane mainStackPane = new MainStackPane(
			this);
		VBox.setVgrow(mainStackPane, Priority.ALWAYS);
		this.mainNavigationPane = new MainNavigationPane(
			this);

		this.todayButtonBox = new HBox();
		this.todayButtonBox.getStyleClass()
				.add(CalendarView.CSS_CALENDAR_FOOTER);

		final Button todayButton = new Button();
		todayButton.textProperty()
				.bind(this.todayButtonText);
		todayButton.getStyleClass()
				.add(CalendarView.CSS_CALENDAR_TODAY_BUTTON);
		todayButton.setOnAction(actionEvent -> {
			final Calendar calendar1 = CalendarView.this.calendarProperty()
					.get();
			calendar1.setTime(new Date());
			calendar1.set(Calendar.HOUR_OF_DAY, 0);
			calendar1.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			CalendarView.this.selectedDate.set(calendar1.getTime());
		});
		this.todayButtonBox.setAlignment(Pos.CENTER);
		this.todayButtonBox.getChildren()
				.add(todayButton);

		getChildren().addAll(this.mainNavigationPane, mainStackPane);

		this.showTodayButton.addListener((InvalidationListener) observable -> {
			if (CalendarView.this.showTodayButton.get())
				CalendarView.this.getChildren()
						.add(CalendarView.this.todayButtonBox);
			else
				CalendarView.this.getChildren()
						.remove(CalendarView.this.todayButtonBox);
		});
		this.showTodayButton.set(true);

	}

	private final HBox todayButtonBox;

	/**
	 * Gets or sets the locale.
	 *
	 * @return The property.
	 */
	public ObjectProperty<Locale> localeProperty() {

		return this.locale;
	}

	private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

	public Locale getLocale() {

		return this.locale.get();
	}

	public void setLocale(final Locale locale) {

		this.locale.set(locale);
	}

	/**
	 * Gets or sets the calendar.
	 *
	 * @return The property.
	 */
	public ObjectProperty<Calendar> calendarProperty() {

		return this.calendar;
	}

	private final ObjectProperty<Calendar> calendar = new SimpleObjectProperty<>();

	public Calendar getCalendar() {

		return this.calendar.get();
	}

	public void setCalendar(final Calendar calendar) {

		this.calendar.set(calendar);
	}

	/**
	 * Gets the list of disabled week days. E.g. if you add <code>Calendar.WEDNESDAY</code>, Wednesday
	 * will be disabled.
	 *
	 * @return The list.
	 */
	public ObservableList<Integer> getDisabledWeekdays() {

		return this.disabledWeekdays;
	}

	private final ObservableList<Integer> disabledWeekdays = FXCollections.observableArrayList();

	/**
	 * Gets the list of disabled dates. You can add specific date, in order to disable them.
	 *
	 * @return The list.
	 */
	public ObservableList<Date> getDisabledDates() {

		return this.disabledDates;
	}

	private final ObservableList<Date> disabledDates = FXCollections.observableArrayList();

	/**
	 * Gets the selected date.
	 *
	 * @return The property.
	 */
	public ReadOnlyObjectProperty<Date> selectedDateProperty() {

		return this.selectedDate;
	}

	private final ObjectProperty<Date> currentDate = new SimpleObjectProperty<>();

	public ObjectProperty<Date> currentDateProperty() {

		return this.currentDate;
	}

	/**
	 * Indicates, whether the today button should be shown.
	 *
	 * @return The property.
	 */
	public BooleanProperty showTodayButtonProperty() {

		return this.showTodayButton;
	}

	private final BooleanProperty showTodayButton = new SimpleBooleanProperty();

	public boolean getShowTodayButton() {

		return this.showTodayButton.get();
	}

	public void setShowTodayButton(final boolean showTodayButton) {

		this.showTodayButton.set(showTodayButton);
	}

	/**
	 * The text of the today button
	 *
	 * @return The property.
	 */
	public StringProperty todayButtonTextProperty() {

		return this.todayButtonText;
	}

	private final StringProperty todayButtonText = new SimpleStringProperty(
		"Today");

	public String getTodayButtonText() {

		return this.todayButtonText.get();
	}

	public void setTodayButtonText(final String todayButtonText) {

		this.todayButtonText.set(todayButtonText);
	}

	/**
	 * Indicates, whether the week numbers are shown.
	 *
	 * @return The property.
	 */
	public BooleanProperty showWeeksProperty() {

		return this.showWeeks;
	}

	private final BooleanProperty showWeeks = new SimpleBooleanProperty(
		false);

	public boolean getShowWeeks() {

		return this.showWeeks.get();
	}

	public void setShowWeeks(final boolean showWeeks) {

		this.showWeeks.set(showWeeks);
	}

	/**
	 * Package internal properties.
	 */
	MainNavigationPane mainNavigationPane;

	/**
	 * Counts the current transitions. As long as an animation is going, the panels should not move left
	 * and right.
	 */
	IntegerProperty ongoingTransitions = new SimpleIntegerProperty(
		0);

	ObjectProperty<Date> selectedDate = new SimpleObjectProperty<>();

	ObjectProperty<Date> calendarDate = new SimpleObjectProperty<>();

	IntegerProperty currentlyViewing = new SimpleIntegerProperty();

	StringProperty title = new SimpleStringProperty();

}

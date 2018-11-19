package com.codigo.aplios.gui.control.calendar;

import java.util.Locale;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DatePicker extends StackPane {

	private final SimpleIntegerProperty	selectedDate	= new SimpleIntegerProperty();
	private final SimpleIntegerProperty	selectedMonth	= new SimpleIntegerProperty();
	private final SimpleIntegerProperty	selectedYear	= new SimpleIntegerProperty();
	private final Rectangle2D			calendarBounds	= new Rectangle2D(
		100, 100, 205, 196);
	private final FXCalendar			fxCalendar;
	private final BasePane				basePane;
	private final TopPane				topPane;

	public DatePicker(final FXCalendar fxCalendar) {

		super();
		this.fxCalendar = fxCalendar;
		this.selectedDate.set(fxCalendar.getSelectedDate());
		this.selectedMonth.set(fxCalendar.getSelectedMonth());
		this.selectedYear.set(fxCalendar.getSelectedYear());
		fxCalendar.setLocale(Locale.ENGLISH);
		setPrefHeight(this.calendarBounds.getHeight());
		setPrefWidth(this.calendarBounds.getWidth());
		setAlignment(Pos.TOP_LEFT);
		FXCalendarUtility.setBaseColorToNode(this, fxCalendar.getBaseColor());
		this.basePane = new BasePane(
			this);
		this.topPane = new TopPane(
			this);
		getChildren().addAll(this.basePane, this.topPane);
		showBasePane();
	}

	/* GETTER'S FROM FXCALENDAR * */
	public Color getBaseColor() {

		return this.fxCalendar.getBaseColor();
	}

	public FXCalendarUtility getFXCalendarUtility() {

		return this.fxCalendar.getFXCalendarUtility();
	}

	public Locale getLocale() {

		return this.fxCalendar.getLocale();
	}

	public boolean getShowWeekNumber() {

		return this.fxCalendar.getShowWeekNumber();
	}

	public FXCalendar getFxCalendar() {

		return this.fxCalendar;
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

	/* GETTER'S FROM DATEPICKER * */
	public Rectangle2D getBounds() {

		return this.calendarBounds;
	}

	public BasePane getBasePane() {

		return this.basePane;
	}

	public TopPane getTopPane() {

		return this.topPane;
	}

	public void showBasePane() {

		this.basePane.setVisible(true);
		this.topPane.setVisible(false);
	}

	public void showTopPane() {

		this.topPane.resetYearButtons();
		this.basePane.setVisible(false);
		this.topPane.setVisible(true);
	}

	public void incrementMonth() {

		final int currentMonth = this.selectedMonth.get();
		if (currentMonth >= (this.fxCalendar.getFXCalendarUtility()
				.getMonths(getLocale()).length - 2)) {
			this.selectedMonth.set(0);
			this.selectedYear.set(this.selectedYear.get() + 1);
		}
		else
			this.selectedMonth.set(currentMonth + 1);
	}

	public void decrementMonth() {

		final int currentMonth = this.selectedMonth.get();
		if (currentMonth <= 0) {
			this.selectedMonth.set(this.fxCalendar.getFXCalendarUtility()
					.getMonths(getLocale()).length - 2);
			this.selectedYear.set(this.selectedYear.get() - 1);
		}
		else
			this.selectedMonth.set(currentMonth - 1);
	}

}

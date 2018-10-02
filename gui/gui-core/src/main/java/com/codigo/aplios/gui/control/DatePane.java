package com.codigo.aplios.gui.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;

/**
 * Abstract base class for the {@link MonthView}, {@link YearView} and
 * {@link DecadesView}.
 *
 * @author Christian Schudt
 */
abstract class DatePane extends GridPane {

	/**
	 * Sets basic stuff
	 *
	 * @param calendarView
	 *        The calendar view.
	 */
	protected DatePane(final CalendarView calendarView) {

		this.calendarView = calendarView;

		// When the date changed, update the days.
		calendarView.calendarDate.addListener((InvalidationListener) observable -> DatePane.this.updateContent());

		// Every time the calendar changed, rebuild the pane and update the content.
		calendarView.calendarProperty()
		    .addListener((InvalidationListener) observable ->
		{
			    DatePane.this.getChildren()
			        .clear();
			    DatePane.this.buildContent();
			    DatePane.this.updateContent();
		    });

		this.buildContent();
		this.updateContent();
	}

	/**
	 * The calendar view.
	 */
	protected CalendarView calendarView;

	/**
	 * This is the date, this pane operates on.
	 *
	 * @param date
	 *        The date.
	 */
	protected void setDate(final Date date) {

		this.calendarView.getCalendar()
		    .setTime(date);
		this.updateContent();
		// Restore
		this.calendarView.getCalendar()
		    .setTime(this.calendarView.calendarDate.get());
	}

	/**
	 * Builds the content.
	 */
	protected abstract void buildContent();

	/**
	 * Updates the content.
	 */
	protected abstract void updateContent();

	protected StringProperty title = new SimpleStringProperty();

	/**
	 * The title property which is defined by the pane.
	 *
	 * @return The property.
	 */
	public ReadOnlyStringProperty titleProperty() {

		return this.title;
	}

	/**
	 * Gets the date format, associated with the current calendar.
	 *
	 * @param format
	 *        The date format as String.
	 * @return The date format.
	 */
	protected DateFormat getDateFormat(final String format) {

		final DateFormat dateFormat = new SimpleDateFormat(
		        format, this.calendarView.localeProperty()
		            .get());
		dateFormat.setCalendar(this.calendarView.getCalendar());
		return dateFormat;
	}
}

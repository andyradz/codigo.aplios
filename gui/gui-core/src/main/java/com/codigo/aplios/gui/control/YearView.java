package com.codigo.aplios.gui.control;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * The year view shows the months.
 *
 * @author Christian Schudt
 */
final class YearView extends DatePane {

	private static final String	CSS_CALENDAR_YEAR_VIEW		= "calendar-year-view";
	private static final String	CSS_CALENDAR_MONTH_BUTTON	= "calendar-month-button";

	public YearView(final CalendarView calendarView) {

		super(calendarView);

		this.getStyleClass()
		    .add(YearView.CSS_CALENDAR_YEAR_VIEW);

		// When the locale changes, update the contents (month names).
		calendarView.localeProperty()
		    .addListener((InvalidationListener) observable -> YearView.this.updateContent());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buildContent() {

		// Get the number of months. I read, there are some lunar calendars, with more
		// than 12 months.
		final int numberOfMonths = this.calendarView.getCalendar()
		    .getMaximum(Calendar.MONTH) + 1;

		final int numberOfColumns = 3;

		for (int i = 0; i < numberOfMonths; i++) {
			final int j = i;
			final Button button = new Button();
			button.getStyleClass()
			    .add(YearView.CSS_CALENDAR_MONTH_BUTTON);

			// Make the button stretch.
			button.setMaxWidth(Double.MAX_VALUE);
			button.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(button, Priority.ALWAYS);
			GridPane.setHgrow(button, Priority.ALWAYS);

			button.setOnAction(actionEvent -> {
				if (YearView.this.calendarView.currentlyViewing.get() == Calendar.YEAR) {
					YearView.this.calendarView.getCalendar()
					    .set(Calendar.MONTH, j);
					YearView.this.calendarView.currentlyViewing.set(Calendar.MONTH);
					YearView.this.calendarView.calendarDate.set(YearView.this.calendarView.getCalendar()
					    .getTime());
				}
			});
			final int rowIndex = i % numberOfColumns;
			final int colIndex = (i - rowIndex) / numberOfColumns;
			this.add(button, rowIndex, colIndex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateContent() {

		final DateFormatSymbols symbols = new DateFormatSymbols(
		        this.calendarView.localeProperty()
		            .get());
		final String[] monthNames = symbols.getShortMonths();
		for (int i = 1; i < monthNames.length; i++) {
			final Button button = (Button) this.getChildren()
			    .get(i - 1);
			button.setText(monthNames[i - 1]);
		}
		this.title.set(this.getDateFormat("yyyy")
		    .format(this.calendarView.getCalendar()
		        .getTime()));
	}
}
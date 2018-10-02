package com.codigo.aplios.gui.control;

import java.util.Calendar;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Shows the years of several decades.
 *
 * @author Christian Schudt
 */
final class DecadesView extends DatePane {

	private static final String CSS_CALENDAR_DECADES_VIEW = "calendar-decades-view";

	private final static int NUMBER_OF_DECADES = 2;

	public DecadesView(final CalendarView calendarView) {

		super(calendarView);
		this.getStyleClass()
		    .add(DecadesView.CSS_CALENDAR_DECADES_VIEW);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buildContent() {

		final Calendar calendar = this.calendarView.getCalendar();

		for (int i = 0; i < (DecadesView.NUMBER_OF_DECADES * 10); i++) {

			final Button button = new Button();
			button.setMaxWidth(Double.MAX_VALUE);
			button.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(button, Priority.ALWAYS);
			GridPane.setHgrow(button, Priority.ALWAYS);

			button.getStyleClass()
			    .add("calendar-year-button");
			button.setOnAction(actionEvent -> {
				if (DecadesView.this.calendarView.currentlyViewing.get() == Calendar.ERA) {
					calendar.set(Calendar.YEAR, (Integer) button.getUserData());
					DecadesView.this.calendarView.currentlyViewing.set(Calendar.YEAR);
					DecadesView.this.calendarView.calendarDate.set(calendar.getTime());
				}
			}

			);
			final int rowIndex = i % 5;
			final int colIndex = (i - rowIndex) / 5;

			this.add(button, rowIndex, colIndex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateContent() {

		final Calendar calendar = this.calendarView.getCalendar();

		final int year = calendar.get(Calendar.YEAR);
		int a = year % 10;
		if (a < 5)
			a += 10;
		final int startYear = year - a;
		for (int i = 0; i < (10 * DecadesView.NUMBER_OF_DECADES); i++) {
			final int y = i + startYear;
			final Button button = (Button) this.getChildren()
			    .get(i);
			button.setText(Integer.toString(y));
			button.setUserData(y);
		}

		this.title.set(String.format("%s - %s", startYear, (startYear + (10 * DecadesView.NUMBER_OF_DECADES)) - 1));
	}
}

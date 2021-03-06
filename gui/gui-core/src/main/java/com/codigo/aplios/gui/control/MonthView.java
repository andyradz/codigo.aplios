package com.codigo.aplios.gui.control;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.InvalidationListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Responsible for displaying the days of a month.
 *
 * @author Christian Schudt
 */
final class MonthView
        extends DatePane {

    private static final String CSS_CALENDAR_MONTH_VIEW = "calendar-month-view";

    private static final String CSS_CALENDAR_DAY_CURRENT_MONTH = "calendar-cell-current-month";

    private static final String CSS_CALENDAR_DAY_OTHER_MONTH = "calendar-cell-other-month";

    private static final String CSS_CALENDAR_TODAY = "calendar-cell-today";

    private static final String CSS_CALENDAR_WEEKDAYS = "calendar-weekdays";

    private static final String CSS_CALENDAR_WEEK_NUMBER = "calendar-week-number";

    /**
     * The number of days per week. I don't know if there is a culture with more or
     * less than seven days per week, but theoretically {@link Calendar} allows it.
     * This variable will correspond to the number of columns.
     */
    private int numberOfDaysPerWeek;

    /**
     * Constructs the month view.
     *
     * @param calendarView
     * The calendar view.
     */
    public MonthView(final CalendarView calendarView) {

        super(calendarView);

        this.getStyleClass()
                .add(MonthView.CSS_CALENDAR_MONTH_VIEW);

        // When the locale changed, update the weeks to the new locale.
        calendarView.localeProperty()
                .addListener((InvalidationListener)observable -> MonthView.this.updateContent());

        // When the disabled week days change, update the days.
        calendarView.getDisabledWeekdays()
                .addListener((InvalidationListener)observable -> MonthView.this.updateDays());

        // When the disabled dates change, update the days.
        calendarView.getDisabledDates()
                .addListener((InvalidationListener)observable -> MonthView.this.updateDays());

        // When the disabled dates change, update the days.
        calendarView.showWeeksProperty()
                .addListener((InvalidationListener)observable ->
                {
                    MonthView.this.getChildren()
                            .clear();
                    MonthView.this.buildContent();
                    MonthView.this.updateContent();
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildContent() {

        final Calendar calendar = this.calendarView.calendarProperty()
                .get();

        // get the maximum number of days in a week for this calendar.
        this.numberOfDaysPerWeek = calendar.getMaximum(Calendar.DAY_OF_WEEK);

        // get the maximum number of days a month could have.
        final int maxNumberOfDaysInMonth = calendar.getMaximum(Calendar.DAY_OF_MONTH);

        // assume the first row has only 1 day, then distribute the rest among the
        // remaining weeks and add the first week.
        final int numberOfRows = (int)Math.ceil((maxNumberOfDaysInMonth - 1) / (double)this.numberOfDaysPerWeek) + 1;

        // remove all controls
        this.getChildren()
                .clear();

        final int colOffset = this.calendarView.getShowWeeks() ? 1 : 0;

        if (this.calendarView.getShowWeeks()) {
            final Label empty = new Label();
            empty.setMaxWidth(Double.MAX_VALUE);
            empty.getStyleClass()
                    .add(MonthView.CSS_CALENDAR_WEEKDAYS);
            this.add(empty, 0, 0);
        }
        // iterate through the columns
        for (int i = 0; i < this.numberOfDaysPerWeek; i++) {
            final Label label = new Label();
            label.getStyleClass()
                    .add(MonthView.CSS_CALENDAR_WEEKDAYS);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            this.add(label, i + colOffset, 0);
        }

        // iterate through the rows
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {

            if (this.calendarView.getShowWeeks()) {
                final Label label = new Label();
                label.setMaxWidth(Double.MAX_VALUE);
                label.setMaxHeight(Double.MAX_VALUE);
                label.getStyleClass()
                        .add(MonthView.CSS_CALENDAR_WEEK_NUMBER);
                this.add(label, 0, rowIndex + 1);
            }

            // iterate through the columns
            for (int colIndex = 0; colIndex < this.numberOfDaysPerWeek; colIndex++) {
                final Button button = new Button();
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);

                GridPane.setVgrow(button, Priority.ALWAYS);
                GridPane.setHgrow(button, Priority.ALWAYS);
                button.setOnAction(
                        actionEvent -> MonthView.this.calendarView.selectedDate.set((Date)button.getUserData()));
                // add the button, starting at second row.
                this.add(button, colIndex + colOffset, rowIndex + 1);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateContent() {

        this.updateDays();
        this.updateWeekNames();
    }

    /**
     * Updates the days.
     */
    private void updateDays() {

        final Calendar calendar = this.calendarView.getCalendar();
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, this.calendarView.localeProperty()
                .get());

        dateFormat.setCalendar(this.calendarView.getCalendar());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Set the calendar to the first day of the current month.
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        final int month = calendar.get(Calendar.MONTH);

        final Date tmp = calendar.getTime();

        calendar.setTime(new Date());
        final int todayDay = calendar.get(Calendar.DATE);
        final int todayMonth = calendar.get(Calendar.MONTH);
        final int todayYear = calendar.get(Calendar.YEAR);
        calendar.setTime(tmp);

        // Set the calendar to the end of the previous month.
        while (calendar.getFirstDayOfWeek() != calendar.get(Calendar.DAY_OF_WEEK))
            calendar.add(Calendar.DAY_OF_MONTH, -1);

        // Ignore the week day row and the week number column
        for (int i = this.numberOfDaysPerWeek + (this.calendarView.getShowWeeks() ? 1 : 0); i < this.getChildren()
                .size(); i++) {
            final Date currentDate = calendar.getTime();
            if (((i % (this.numberOfDaysPerWeek + 1)) == 0) && this.calendarView.getShowWeeks()) {
                final Label label = (Label)this.getChildren()
                        .get(i);
                label.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
            } else {
                final Button control = (Button)this.getChildren()
                        .get(i);

                control.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                control.setTooltip(new Tooltip(dateFormat.format(currentDate)));

                boolean disabled = this.calendarView.getDisabledWeekdays()
                        .contains(calendar.get(Calendar.DAY_OF_WEEK));

                for (final Date disabledDate : this.calendarView.getDisabledDates()) {
                    final Calendar clone = (Calendar)calendar.clone();
                    clone.setTime(disabledDate);
                    if ((calendar.get(Calendar.YEAR) == clone.get(Calendar.YEAR)) &&
                            (calendar.get(Calendar.MONTH) == clone.get(Calendar.MONTH)) &&
                            (calendar.get(Calendar.DAY_OF_MONTH) == clone.get(Calendar.DAY_OF_MONTH)))
                        disabled = true;
                }

                control.setDisable(disabled);

                control.getStyleClass()
                        .remove(MonthView.CSS_CALENDAR_DAY_CURRENT_MONTH);
                control.getStyleClass()
                        .remove(MonthView.CSS_CALENDAR_DAY_OTHER_MONTH);
                control.getStyleClass()
                        .remove(MonthView.CSS_CALENDAR_TODAY);

                if (calendar.get(Calendar.MONTH) == month)
                    control.getStyleClass()
                            .add(MonthView.CSS_CALENDAR_DAY_CURRENT_MONTH);
                else
                    control.getStyleClass()
                            .add(MonthView.CSS_CALENDAR_DAY_OTHER_MONTH);

                if ((calendar.get(Calendar.YEAR) == todayYear) &&
                        (calendar.get(Calendar.MONTH) == todayMonth) && (calendar.get(Calendar.DATE) == todayDay))
                    control.getStyleClass()
                            .add(MonthView.CSS_CALENDAR_TODAY);

                control.setUserData(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
            }
        }

        // Restore original date
        calendar.setTime(this.calendarView.calendarDate.get());
    }

    /**
     * Updates the week names, when the locale changed.
     */
    private void updateWeekNames() {

        final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(
                this.calendarView.localeProperty()
                        .get());
        final String[] weekDays = dateFormatSymbols.getShortWeekdays();
        weekDays[1] = "Nd";

        // Start with 1 instead of 0, since the first element in the array is empty.
        for (int i = 1; i < weekDays.length; i++) {
            // Get the first character only.
            final String shortWeekDay = weekDays[i].substring(0, 2);

            // Shift the index according to the first day of week.
            int j = i -
                    this.calendarView.getCalendar()
                            .getFirstDayOfWeek();
            if (j < 0)
                j += weekDays.length - 1;

            final Label label = (Label)this.getChildren()
                    .get(j + (this.calendarView.getShowWeeks() ? 1 : 0));

            label.setText(shortWeekDay);
        }
        this.title.set(this.getDateFormat("MMMM yyyy")
                .format(this.calendarView.getCalendar()
                        .getTime()));
    }

}

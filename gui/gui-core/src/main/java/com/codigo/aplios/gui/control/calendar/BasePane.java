package com.codigo.aplios.gui.control.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.codigo.aplios.gui.control.calendar.FXCalendarCell.DateCell;
import com.codigo.aplios.gui.control.calendar.FXCalendarCell.WeekCell;
import com.codigo.aplios.gui.control.calendar.FXCalendarControls.BaseNavigatorArrowButton;
import com.codigo.aplios.gui.control.calendar.FXCalendarControls.NormalButton;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class BasePane extends Group {
	private final DatePicker				datePicker;
	private StackPane						navigatorPane;
	private StackPane						weekPane;
	private StackPane						deskPane;
	private StackPane						footerPane;
	private Label							displayLabel;
	private final ObservableList<WeekCell>	weekCellList	= FXCollections.observableArrayList();
	private final ObservableList<DateCell>	dateCellList	= FXCollections.observableArrayList();
	public static final String				WEEKNUMER_LABEL	= "Wk.";
	private BaseNavigatorArrowButton		prevMonthBtn;

	public BasePane(final DatePicker datePicker) {

		super();
		this.datePicker = datePicker;
		configureNavigator();
		configureWeekHeader();
		configureDesk();
		configureFooter();
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** MONTH NAVIGATOR
	 * ****************************** *******************************************
	 * ******************************* *******************************************
	 */
	private void configureNavigator() {

		this.navigatorPane = new StackPane();
		/*
		 * Changes to be done in BasePaneNavigator on change of selectedMonth and selectedYear in
		 * DatePicker.
		 */
		final ChangeListener<Object> listener = (arg0, arg1, arg2) -> setLabelText();

		this.datePicker.selectedMonthProperty()
				.addListener(listener);
		this.datePicker.selectedYearProperty()
				.addListener(listener);

		FXCalendarUtility.setBaseColorToNode(this.navigatorPane, this.datePicker.getBaseColor());
		this.navigatorPane.setPrefWidth(this.datePicker.getBounds()
				.getWidth());
		this.navigatorPane.setPrefHeight(26);
		this.navigatorPane.getStyleClass()
				.add("fx-calendar-navigator");

		/* Displaying the Month & Year of the selected date. */
		this.displayLabel = new Label();
		this.displayLabel.getStyleClass()
				.add("fx-calendar-navigator-label");
		this.displayLabel.setGraphic(new FXCalendarControls().new Arrow());
		setLabelText();
		this.displayLabel.setOnMouseClicked(arg0 -> BasePane.this.datePicker.showTopPane());

		/* Calculating the distance for the arrow buttons from the center. */
		final double pos = (this.datePicker.getBounds()
				.getWidth() / 2) - 12;

		/* Getting the Next Month Button. */
		final BaseNavigatorArrowButton nextMonthBtn = new FXCalendarControls().new BaseNavigatorArrowButton(Side.RIGHT,
				this.datePicker.getBaseColor());
		nextMonthBtn.setTranslateX(pos);
		nextMonthBtn.setOnMouseClicked(arg0 -> BasePane.this.datePicker.incrementMonth());

		/* Getting the Previous Month Button. */
		this.prevMonthBtn = new FXCalendarControls().new BaseNavigatorArrowButton(Side.LEFT,
				this.datePicker.getBaseColor());
		this.prevMonthBtn.setTranslateX(-pos);
		this.prevMonthBtn.setOnMouseClicked(arg0 -> {

			if (!((BasePane.this.datePicker.getSelectedMonth() == 0)
					&& (BasePane.this.datePicker.getSelectedYear() == 1)))
				BasePane.this.datePicker.decrementMonth();
		});

		this.navigatorPane.getChildren()
				.addAll(this.displayLabel, nextMonthBtn, this.prevMonthBtn);
		getChildren().add(this.navigatorPane);
	}

	public void setLabelText() {

		this.displayLabel.setText(this.datePicker.getFXCalendarUtility()
				.getMonths(this.datePicker.getLocale())[this.datePicker.getSelectedMonth()] + " "
				+ this.datePicker.getSelectedYear());
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** WEEK HEADER ****************************** *
	 * *************************************************************************
	 * *******************************************
	 */
	private void configureWeekHeader() {

		this.weekPane = new StackPane();

		FXCalendarUtility.setBaseColorToNode(this.weekPane, this.datePicker.getBaseColor());
		this.weekPane.setPrefWidth(this.datePicker.getBounds()
				.getWidth());
		this.weekPane.setPrefHeight(18);
		this.weekPane.getStyleClass()
				.add("fx-calendar-weekpane");

		final int count = this.datePicker.getShowWeekNumber() ? 8 : 7;

		final TilePane tp = new TilePane();
		tp.setPrefColumns(count);

		generateWeekCells(count);
		for (final WeekCell weekCell : this.weekCellList)
			tp.getChildren()
					.add(weekCell);

		this.weekPane.getChildren()
				.add(tp);
		this.weekPane.setTranslateY(this.navigatorPane.getPrefHeight());
		getChildren().add(this.weekPane);
	}

	private void generateWeekCells(final int count) {

		final Rectangle2D cellBounds = calculateBounds();
		WeekCell cell;
		final List<WeekCell> wkCells = new ArrayList<>(
			count);
		if (this.datePicker.getShowWeekNumber()) {
			cell = new FXCalendarCell().new WeekCell("week_num",
					BasePane.WEEKNUMER_LABEL,
					cellBounds.getWidth(),
					cellBounds.getHeight());
			FXCalendarUtility.setBaseColorToNode(cell.getTxt(), Color.BLUE);
			wkCells.add(cell);
		}

		final String[] wks = this.datePicker.getFXCalendarUtility()
				.getShortestWeekDays(this.datePicker.getLocale());
		for (int i = 1; i < wks.length; i++) {
			cell = new FXCalendarCell().new WeekCell("week_" + wks[i],
					wks[i],
					cellBounds.getWidth(),
					cellBounds.getHeight());
			FXCalendarUtility.setBaseColorToNode(cell.getTxt(), this.datePicker.getBaseColor());
			wkCells.add(cell);
		}
		this.weekCellList.addAll(wkCells);
	}

	public void setWeekLabels() {

		final String[] wks = this.datePicker.getFXCalendarUtility()
				.getShortestWeekDays(this.datePicker.getLocale());
		int pos = this.datePicker.getShowWeekNumber() ? 1 : 0;
		for (int i = 1; i < wks.length; i++) {
			this.weekCellList.get(pos)
					.setContent(wks[i]);
			pos++;
		}
	}

	private Rectangle2D calculateBounds() {

		final int divFactor = getColCount();
		final double width = this.datePicker.getBounds()
				.getWidth() / divFactor;
		final double height = 18;
		return new Rectangle2D(
			0, 0, width, height);
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** DATE DESK ****************************** **
	 * ************************************************************************
	 * *******************************************
	 */

	private void configureDesk() {

		this.deskPane = new StackPane();
		FXCalendarUtility.setBaseColorToNode(this.deskPane, this.datePicker.getBaseColor());
		this.deskPane.setPrefWidth(this.datePicker.getBounds()
				.getWidth());
		this.deskPane.setPrefHeight(120);
		this.deskPane.getStyleClass()
				.add("fx-calendar-desk");

		final TilePane tp = new TilePane();
		tp.setPrefColumns(getColCount());

		generateDateCells();

		for (final DateCell dateCell : this.dateCellList)
			tp.getChildren()
					.add(dateCell);

		generateDates();

		/*
		 * Changes to be done in BasePaneDesk on change of selectedMonth and selectedYear in DatePicker.
		 */
		final ChangeListener<Object> listener = (arg0, arg1, arg2) -> generateDates();

		this.datePicker.selectedDateProperty()
				.addListener(listener);
		this.datePicker.selectedMonthProperty()
				.addListener(listener);
		this.datePicker.selectedYearProperty()
				.addListener(listener);

		this.deskPane.getChildren()
				.add(tp);
		this.deskPane.setTranslateY(this.navigatorPane.getPrefHeight() + this.weekPane.getPrefHeight());
		getChildren().add(this.deskPane);

	}

	private int getColCount() {

		return this.datePicker.getShowWeekNumber() ? 8 : 7;
	}

	private void generateDateCells() {

		final int count = getColCount();
		final Rectangle2D cellBounds = calculateDeskBounds();
		DateCell dateCell;
		final List<DateCell> dateCells = new ArrayList<>(
			count * 6);

		for (int i = 0; i < (count * 6); i++) {
			dateCell = new FXCalendarCell().new DateCell("cell" + i, cellBounds.getWidth(), cellBounds.getHeight());
			FXCalendarUtility.setBaseColorToNode(dateCell, this.datePicker.getBaseColor());
			// For Week Number cells
			if (this.datePicker.getShowWeekNumber() && ((i % 8) == 0)) {
				FXCalendarUtility.setBaseColorToNode(dateCell.getTxt(), Color.BLUE);
				dateCell.setWeekNumCell(true);
				dateCell.getTxt()
						.getStyleClass()
						.add("fx-calendar-weektext");
			}
			// For actual Date cells
			else {
				// TODO : Anything to configure on date cell.
			}

			dateCells.add(dateCell);
		}
		this.dateCellList.addAll(dateCells);
	}

	public void generateDates() {

		final Calendar firstDayOfMonth = FXCalendarUtility.getDate(1, this.datePicker.getSelectedMonth(),
				this.datePicker.getSelectedYear());
		final Calendar paneFirstDate = (Calendar) firstDayOfMonth.clone();

		// If Monday is first day of week.
		if (Calendar.getInstance(this.datePicker.getLocale())
				.getFirstDayOfWeek() == 2) {
			int diff = 0;
			if (firstDayOfMonth.get(Calendar.DAY_OF_WEEK) == 1)
				diff = 6;
			else
				diff = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 2;
			paneFirstDate.add(Calendar.DAY_OF_YEAR, -diff);
		}
		else
			// If Sunday is first day of week.
			paneFirstDate.add(Calendar.DAY_OF_YEAR, -(firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1));

		final Calendar dummyDate = (Calendar) paneFirstDate.clone();
		final Calendar systemDate = FXCalendarUtility.getCurrentDateCalendar();

		final int fxDate = this.datePicker.getFxCalendar()
				.getSelectedDate();
		final int fxMonth = this.datePicker.getFxCalendar()
				.getSelectedMonth();
		final int fxYear = this.datePicker.getFxCalendar()
				.getSelectedYear();

		for (final DateCell dateCell : this.dateCellList)
			if (!dateCell.isWeekNumCell()) {
				dateCell.getStyleClass()
						.remove("fx-calendar-basic-datecell-selected");
				dateCell.getTxt()
						.setText(dummyDate.get(Calendar.DAY_OF_MONTH) + "");

				// Setting the date details of the cell.
				dateCell.setCellDate(dummyDate.get(Calendar.DAY_OF_MONTH));
				dateCell.setCellMonth(dummyDate.get(Calendar.MONTH));
				dateCell.setCellYear(dummyDate.get(Calendar.YEAR));

				// Highlighting the current month cells.
				if (dummyDate.get(Calendar.MONTH) == this.datePicker.getSelectedMonth())
					dateCell.getTxt()
							.setDisable(false);
				else {
					dateCell.getTxt()
							.setDisable(true);
					// Not showing the dates below 01/01/01
					if (((this.datePicker.getSelectedMonth() == 0) && (this.datePicker.getSelectedYear() == 1))
							&& (dateCell.getCellMonth() != 1))
						dateCell.setCellYear(0);
				}

				// Highlighting the current system date.
				if ((systemDate.get(Calendar.DAY_OF_MONTH) == dummyDate.get(Calendar.DAY_OF_MONTH))
						&& (systemDate.get(Calendar.MONTH) == dummyDate.get(Calendar.MONTH))
						&& (systemDate.get(Calendar.YEAR) == dummyDate.get(Calendar.YEAR)))
					dateCell.setCellFocused(true);
				else
					dateCell.setCellFocused(false);

				// Highlighting the Selected date.
				if ((fxDate == dummyDate.get(Calendar.DAY_OF_MONTH)) && (fxMonth == dummyDate.get(Calendar.MONTH))
						&& (fxYear == dummyDate.get(Calendar.YEAR))) {
					// Overriding the dotted line with selected class.
					if (dateCell.getCellFocused())
						dateCell.setCellFocused(false);
					dateCell.getStyleClass()
							.add("fx-calendar-basic-datecell-selected");
				}

				// Setting the event handler for the selected date.
				dateCell.setOnMouseClicked(event -> {

					final int year = dateCell.getCellYear();
					final int month = dateCell.getCellMonth();
					final int date = dateCell.getCellDate();
					BasePane.this.datePicker.setSelectedYear(year);
					BasePane.this.datePicker.setSelectedMonth(month);
					BasePane.this.datePicker.setSelectedDate(date);

					BasePane.this.datePicker.getFxCalendar()
							.setSelectedDate(date);
					BasePane.this.datePicker.getFxCalendar()
							.setSelectedMonth(month);
					BasePane.this.datePicker.getFxCalendar()
							.setSelectedYear(year);
					BasePane.this.datePicker.getFxCalendar()
							.setTriggered(true);

					BasePane.this.datePicker.getFxCalendar()
							.getTextField()
							.requestFocus();
					BasePane.this.datePicker.getFxCalendar()
							.showDateInTextField();
					BasePane.this.datePicker.getFxCalendar()
							.hidePopup();
				});

				// Incrementing the date.
				dummyDate.add(Calendar.DAY_OF_YEAR, 1);
			}
			else // Updating the week number
			if (dummyDate.get(Calendar.DAY_OF_WEEK) == 1) {
				dateCell.getTxt()
						.setText((dummyDate.get(Calendar.WEEK_OF_YEAR) - 1) + "");
				dateCell.getTxt()
						.getStyleClass()
						.add("fx-calendar-weektext");
			}
	}

	private Rectangle2D calculateDeskBounds() {

		final int divFactor = getColCount();
		final double width = this.datePicker.getBounds()
				.getWidth() / divFactor;
		final double height = 120 / 6;
		return new Rectangle2D(
			0, 0, width, height);
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** FOOTER ****************************** *****
	 * *********************************************************************
	 * *******************************************
	 */
	private void configureFooter() {

		this.footerPane = new StackPane();
		FXCalendarUtility.setBaseColorToNode(this.footerPane, this.datePicker.getBaseColor());
		this.footerPane.setPrefWidth(this.datePicker.getBounds()
				.getWidth());
		this.footerPane.setPrefHeight(32);
		this.footerPane.getStyleClass()
				.add("fx-calendar-footer");
		final NormalButton todayBtn = new FXCalendarControls().new NormalButton("Today");

		/**
		 * Event triggering to set the current date of the system.
		 */
		todayBtn.setOnAction(event -> {

			final Calendar today = FXCalendarUtility.getCurrentDateCalendar();
			BasePane.this.datePicker.getFxCalendar()
					.setSelectedDate(today.get(Calendar.DAY_OF_MONTH));
			BasePane.this.datePicker.getFxCalendar()
					.setSelectedMonth(today.get(Calendar.MONTH));
			BasePane.this.datePicker.getFxCalendar()
					.setSelectedYear(today.get(Calendar.YEAR));
			BasePane.this.datePicker.getFxCalendar()
					.hidePopup();
		});

		this.footerPane.getChildren()
				.add(todayBtn);
		this.footerPane.setTranslateY(
				this.navigatorPane.getPrefHeight() + this.weekPane.getPrefHeight() + this.deskPane.getPrefHeight());
		getChildren().add(this.footerPane);
	}
}

package com.codigo.aplios.gui.control.calendar;

import com.codigo.aplios.gui.control.calendar.FXCalendarControls.CalendarToggleButton;
import com.codigo.aplios.gui.control.calendar.FXCalendarControls.NormalButton;
import com.codigo.aplios.gui.control.calendar.FXCalendarControls.YearNavigatorArrowButton;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class TopPane extends Group {
	private final DatePicker				datePicker;
	private StackPane						monthPane;
	private StackPane						yearPane;
	private StackPane						footerPane;
	private final SimpleIntegerProperty		month			= new SimpleIntegerProperty();
	private final SimpleIntegerProperty		year			= new SimpleIntegerProperty();
	private final CalendarToggleButton[]	monthButtons	= new CalendarToggleButton[12];
	private final CalendarToggleButton[]	yearButtons		= new CalendarToggleButton[10];
	private YearNavigatorArrowButton		prevBtn;

	public TopPane(final DatePicker datePicker) {

		super();
		this.datePicker = datePicker;

		setMonth(datePicker.getSelectedMonth());
		setYear(datePicker.getSelectedYear());

		final HBox hb = new HBox();
		configureMonthPane(hb);
		configureYearPane(hb);
		getChildren().add(hb);
		configureFooter();

		/*
		 * Changes to be done in TopMonthPane on change of selectedMonth and selectedYear in DatePicker.
		 */
		final ChangeListener<Object> listener = (arg0, arg1, arg2) -> {

			styleTopMonthPane();
			styleTopYearPane();
		};
		monthProperty().addListener(listener);
		yearProperty().addListener(listener);

		/*
		 * Changes to be done in TopPane on change of selectedMonth and selectedYear in DatePicker.
		 */
		datePicker.selectedMonthProperty()
				.addListener((ChangeListener<Object>) (arg0, arg1, arg2) -> setMonth((Integer) arg2));

		datePicker.selectedYearProperty()
				.addListener((ChangeListener<Object>) (arg0, arg1, arg2) -> setYear((Integer) arg2));
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** MONTH PANE ****************************** *
	 * *************************************************************************
	 * *******************************************
	 */
	private void configureMonthPane(final HBox hb) {

		this.monthPane = new StackPane();
		FXCalendarUtility.setBaseColorToNode(this.monthPane, this.datePicker.getBaseColor());

		this.monthPane.setPrefWidth((this.datePicker.getBounds()
				.getWidth() - 1) / 2);
		this.monthPane.setPrefHeight(164);
		this.monthPane.getStyleClass()
				.add("fx-calendar-top-monthpane");

		final TilePane tilePane = new TilePane();
		tilePane.setPrefColumns(2);
		tilePane.setHgap(5);
		tilePane.setVgap(8);
		tilePane.setTranslateX(5);

		generateMonthButtons();

		for (final CalendarToggleButton btn : this.monthButtons)
			tilePane.getChildren()
					.add(btn);
		this.monthPane.setAlignment(Pos.CENTER);
		this.monthPane.getChildren()
				.add(tilePane);

		styleTopMonthPane();
		hb.getChildren()
				.add(this.monthPane);
	}

	private void generateMonthButtons() {

		final String[] months = this.datePicker.getFXCalendarUtility()
				.getShortMonths(this.datePicker.getLocale());
		int evenValue = 0;
		int oddValue = 6;

		for (int i = 0; i < 12; i++) {
			int pos = 0;
			if ((i % 2) == 0) {
				pos = evenValue;
				evenValue++;
			}
			else {
				pos = oddValue;
				oddValue++;
			}

			final CalendarToggleButton btn = new FXCalendarControls().new CalendarToggleButton(months[pos], pos);
			btn.setBaseColor(this.datePicker.getBaseColor());
			btn.setOnMouseClicked(e -> {

				setMonth((Integer) btn.getUserData());
				styleTopMonthPane();
			});
			this.monthButtons[i] = btn;
		}
	}

	public void setTopMonths() {

		final String[] months = this.datePicker.getFXCalendarUtility()
				.getShortMonths(this.datePicker.getLocale());
		int evenValue = 0;
		int oddValue = 6;
		for (int i = 0; i < 12; i++) {
			int pos = 0;
			if ((i % 2) == 0) {
				pos = evenValue;
				evenValue++;
			}
			else {
				pos = oddValue;
				oddValue++;
			}
			this.monthButtons[i].setText(months[pos]);
		}
	}

	public void styleTopMonthPane() {

		for (int i = 0; i < 12; i++)
			if (getMonth() == (Integer) this.monthButtons[i].getUserData())
				this.monthButtons[i].setDisable(true);
			else
				this.monthButtons[i].setDisable(false);
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** YEAR PANE ****************************** **
	 * ************************************************************************
	 * *******************************************
	 */
	private void configureYearPane(final HBox hb) {

		this.yearPane = new StackPane();
		FXCalendarUtility.setBaseColorToNode(this.yearPane, this.datePicker.getBaseColor());

		this.yearPane.setPrefWidth(this.datePicker.getBounds()
				.getWidth() / 2);
		this.yearPane.setPrefHeight(164);
		this.yearPane.getStyleClass()
				.add("fx-calendar-top-yearpane");

		final TilePane tilePane = new TilePane();
		tilePane.setPrefColumns(2);
		tilePane.setHgap(5);
		tilePane.setVgap(8);
		tilePane.setTranslateX(5);

		this.prevBtn = new FXCalendarControls().new YearNavigatorArrowButton(Side.LEFT, this.datePicker.getBaseColor());
		final YearNavigatorArrowButton nextBtn = new FXCalendarControls().new YearNavigatorArrowButton(Side.RIGHT,
				this.datePicker.getBaseColor());

		this.prevBtn.setOnMouseClicked(arg0 -> {

			final int start = (Integer) TopPane.this.yearButtons[0].getUserData();
			resetYearButtons(start - 6);
			styleTopYearPane();
		});
		nextBtn.setOnMouseClicked(arg0 -> {

			final int start = (Integer) TopPane.this.yearButtons[9].getUserData();
			resetYearButtons(start + 5);
			styleTopYearPane();
		});

		tilePane.getChildren()
				.add(this.prevBtn);
		tilePane.getChildren()
				.add(nextBtn);

		generateYearButtons();
		for (final CalendarToggleButton btn : this.yearButtons)
			tilePane.getChildren()
					.add(btn);

		this.yearPane.getChildren()
				.add(tilePane);
		styleTopYearPane();
		hb.getChildren()
				.add(this.yearPane);

	}

	private void generateYearButtons() {

		final int[] arr = getYearArray(getYear());
		int evenValue = 0;
		int oddValue = 5;

		for (int i = 0; i < 10; i++) {
			int pos = 0;
			if ((i % 2) == 0) {
				pos = evenValue;
				evenValue++;
			}
			else {
				pos = oddValue;
				oddValue++;
			}

			final CalendarToggleButton btn = new FXCalendarControls().new CalendarToggleButton(arr[pos] + "",
					Integer.valueOf(arr[pos]));
			btn.setBaseColor(this.datePicker.getBaseColor());
			btn.setOnMouseClicked(e -> {

				setYear((Integer) btn.getUserData());
				styleTopYearPane();
			});
			this.yearButtons[i] = btn;
		}
	}

	public void resetYearButtons() {

		resetYearButtons(getYear());
		styleTopYearPane();
	}

	public void resetYearButtons(final int year) {

		final int[] arr = getYearArray(year);
		int evenValue = 0;
		int oddValue = 5;
		for (int i = 0; i < 10; i++) {
			int pos = 0;
			if ((i % 2) == 0) {
				pos = evenValue;
				evenValue++;
			}
			else {
				pos = oddValue;
				oddValue++;
			}
			this.yearButtons[i].setText(arr[pos] + "");
			this.yearButtons[i].setUserData(Integer.valueOf(arr[pos]));
		}
	}

	public void styleTopYearPane() {

		for (int i = 0; i < 10; i++)
			if (getYear() == (Integer) this.yearButtons[i].getUserData())
				this.yearButtons[i].setDisable(true);
			else
				this.yearButtons[i].setDisable(false);
	}

	private int[] getYearArray(final int year) {

		final int[] arr = new int[10];
		int startYear = year > 5 ? year - 4 : 1; // Not showing negative years
		if (year == 1)
			this.prevBtn.setDisable(true);
		else
			this.prevBtn.setDisable(false);

		for (int i = 0; i < 10; i++) {
			arr[i] = startYear;
			startYear++;
		}
		return arr;
	}

	/*
	 * *************************************************************************************************
	 * ******************** ****************************** FOOTER PANE ****************************** *
	 * *************************************************************************
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

		final NormalButton todayBtn = new FXCalendarControls().new NormalButton("Ok");
		/**
		 * Event triggering to set the current date of the system.
		 */
		todayBtn.setOnAction(event -> {

			TopPane.this.datePicker.setSelectedMonth(getMonth());
			TopPane.this.datePicker.setSelectedYear(getYear());
			TopPane.this.datePicker.showBasePane();
		});

		final NormalButton cancelBtn = new FXCalendarControls().new NormalButton("Cancel");
		/**
		 * Event triggering to set the current date of the system.
		 */
		cancelBtn.setOnAction(event -> TopPane.this.datePicker.showBasePane());

		final HBox hb = new HBox();
		hb.setSpacing(5);
		hb.getChildren()
				.addAll(todayBtn, cancelBtn);
		final Group gp = new Group();
		gp.getChildren()
				.add(hb);
		this.footerPane.getChildren()
				.add(gp);

		this.footerPane.setTranslateY(this.monthPane.getPrefHeight());
		getChildren().add(this.footerPane);

	}

	public int getMonth() {

		return this.month.get();
	}

	public int getYear() {

		return this.year.get();
	}

	public void setMonth(final int month) {

		this.month.set(month);
	}

	public void setYear(final int year) {

		this.year.set(year);
	}

	public SimpleIntegerProperty monthProperty() {

		return this.month;
	}

	public SimpleIntegerProperty yearProperty() {

		return this.year;
	}
}

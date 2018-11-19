package com.codigo.aplios.gui.control.calendar;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FXCalendarUtility {

	private static int				MILLIS_IN_DAY		= 1000 * 60 * 60 * 24;
	private static SimpleDateFormat	DATE_FORMAT			= new SimpleDateFormat(
		"dd/MM/yy");
	private final SimpleDateFormat	DISPLAY_DATE_FORMAT	= new SimpleDateFormat(
		"dd/MM/yyyy");

	private String[]			SHORTEST_WEEK_DAYS;													// {"","S","M","T","W","T","F","S"}
	private String[]			SHORT_WEEK_DAYS;													// {"","Sun","Mon","Tue","Wed","Thu","Fri","Sat"}
	private String[]			WEEK_DAYS;															// {"","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"}
	private String[]			SHORT_MONTHS;														// {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec",""}
	private String[]			MONTHS;																// {"January","February","March","April","May","June","July","August","September","October","November","December",""}
	private static final Logger	LOG	= Logger.getLogger(FXCalendarUtility.class.getCanonicalName());

	public static Calendar getCalendar() {

		return Calendar.getInstance();
	}

	public static Date getCurrentDate() {

		return new Date();
	}

	public static Calendar getCurrentDateCalendar() {

		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c;
	}

	public static Calendar getDateCalendar(final Date date) {

		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static long getCurrentTime() {

		return (new Date()).getTime();
	}

	public static String getCurrentFormattedDate() {

		return FXCalendarUtility.DATE_FORMAT.format(FXCalendarUtility.getCurrentTime());
	}

	public static String getPreviousDate() {

		return FXCalendarUtility.DATE_FORMAT
				.format(FXCalendarUtility.getCurrentTime() - FXCalendarUtility.MILLIS_IN_DAY);
	}

	public static String getNextDate() {

		return FXCalendarUtility.DATE_FORMAT
				.format(FXCalendarUtility.getCurrentTime() + FXCalendarUtility.MILLIS_IN_DAY);
	}

	public static Calendar getDate(final Integer day, final Integer month, final Integer year) {

		try {
			final String str_date = day + "/" + (month + 1) + "/" + year;
			final Date date = FXCalendarUtility.DATE_FORMAT.parse(str_date);
			final Calendar c = FXCalendarUtility.getCalendar();
			c.setTime(date);
			return c;
		}
		catch (final ParseException e) {
			FXCalendarUtility.LOG.fine(e.getMessage());
		}
		return null;
	}

	public String convertDatetoString(final Date date) {

		return this.DISPLAY_DATE_FORMAT.format(date);
	}

	public Date convertStringtoDate(final String str) {

		try {
			return this.DISPLAY_DATE_FORMAT.parse(str);
		}
		catch (final ParseException e) {
			FXCalendarUtility.LOG.severe(e.getMessage());
			return null;
		}
	}

	public String getFormattedDate(final Integer day, final Integer month, final Integer year) {

		try {
			final String str_date = day + "/" + (month + 1) + "/" + year;
			final Date date = FXCalendarUtility.DATE_FORMAT.parse(str_date);
			return this.DISPLAY_DATE_FORMAT.format(date);
		}
		catch (final ParseException e) {
			FXCalendarUtility.LOG.fine(e.getMessage());
		}
		return null;
	}

	public void resetShortestWeekDays(final Locale locale) {

		this.SHORTEST_WEEK_DAYS = null;
		getShortestWeekDays(locale);
	}

	public String[] getShortestWeekDays(final Locale locale) {

		if ((this.SHORTEST_WEEK_DAYS == null) || (this.SHORTEST_WEEK_DAYS.length == 0)) {
			this.SHORTEST_WEEK_DAYS = getDayNames("xs", locale);
			// If Monday is first day of week.
			if (Calendar.getInstance(locale)
					.getFirstDayOfWeek() == 2) {
				final String dum = this.SHORTEST_WEEK_DAYS[1];
				for (int i = 1; i < 7; i++)
					this.SHORTEST_WEEK_DAYS[i] = this.SHORTEST_WEEK_DAYS[i + 1];
				this.SHORTEST_WEEK_DAYS[7] = dum;
			}
		}
		return this.SHORTEST_WEEK_DAYS;
	}

	public String[] getShortWeekDays(final Locale locale) {

		if ((this.SHORT_WEEK_DAYS == null) || (this.SHORT_WEEK_DAYS.length == 0))
			this.SHORT_WEEK_DAYS = getDayNames("s", locale);
		// If Monday is first day of week.
		if (Calendar.getInstance(locale)
				.getFirstDayOfWeek() == 2) {
			final String dum = this.SHORT_WEEK_DAYS[1];
			for (int i = 1; i < 7; i++)
				this.SHORT_WEEK_DAYS[i] = this.SHORT_WEEK_DAYS[i + 1];
			this.SHORT_WEEK_DAYS[7] = dum;
		}
		return this.SHORT_WEEK_DAYS;
	}

	public String[] getWeekDays(final Locale locale) {

		if ((this.WEEK_DAYS == null) || (this.WEEK_DAYS.length == 0))
			this.WEEK_DAYS = getDayNames(null, locale);
		// If Monday is first day of week.
		if (Calendar.getInstance(locale)
				.getFirstDayOfWeek() == 2) {
			final String dum = this.WEEK_DAYS[1];
			for (int i = 1; i < 7; i++)
				this.WEEK_DAYS[i] = this.WEEK_DAYS[i + 1];
			this.WEEK_DAYS[7] = dum;
		}
		return this.WEEK_DAYS;
	}

	public void resetShortMonths(final Locale locale) {

		this.SHORT_MONTHS = null;
		getShortMonths(locale);
	}

	public String[] getShortMonths(final Locale locale) {

		if ((this.SHORT_MONTHS == null) || (this.SHORT_MONTHS.length == 0))
			this.SHORT_MONTHS = getMonthNames("s", locale);
		return this.SHORT_MONTHS;
	}

	public void resetMonths(final Locale locale) {

		this.MONTHS = null;
		getMonths(locale);
	}

	public String[] getMonths(final Locale locale) {

		if ((this.MONTHS == null) || (this.MONTHS.length == 0))
			this.MONTHS = getMonthNames(null, locale);
		return this.MONTHS;
	}

	private String[] getDayNames(final String type, final Locale locale) {

		if ((type != null) && type.equalsIgnoreCase("xs")) {
			final String[] days = new DateFormatSymbols(
				locale).getShortWeekdays();
			final String[] xsDays = new String[days.length];
			for (int i = 0; i < days.length; i++)
				xsDays[i] = (days[i].equals("")) ? days[i] : days[i].charAt(0) + "";
			return xsDays;
		}
		if ((type != null) && type.equalsIgnoreCase("s"))
			return new DateFormatSymbols(
				locale).getShortWeekdays();
		else
			return new DateFormatSymbols(
				locale).getWeekdays();
	}

	private String[] getMonthNames(final String type, final Locale locale) {

		if ((type != null) && type.equalsIgnoreCase("s"))
			return new DateFormatSymbols(
				locale).getShortMonths();
		else
			return new DateFormatSymbols(
				locale).getMonths();
	}

	public static void setBaseColorToNode(final Node node, final Color baseColor) {

		node.setStyle("-fx-base:" + FXCalendarUtility.rgbToHex(baseColor) + ";");
	}

	public static String rgbToHex(final Color color) {

		final int i = (int) Math.round(color.getRed() * 255D);
		final int j = (int) Math.round(color.getGreen() * 255D);
		final int k = (int) Math.round(color.getBlue() * 255D);
		return "#" + FXCalendarUtility.toHex(i) + FXCalendarUtility.toHex(j) + FXCalendarUtility.toHex(k);
	}

	private static String toHex(final int code) {

		final String str = "0123456789ABCDEF";
		return str.charAt(code / 16) + "" + str.charAt(code % 16);
	}

	public static Group getDateImage() {

		final Group gp = new Group();
		final StackPane img = new StackPane();
		final double imgSize = 15.0;
		final double imgSizeQuar = imgSize / 4;
		img.setPrefSize(imgSize, imgSize);
		img.getStyleClass()
				.add("calendar-image");
		img.setAlignment(Pos.TOP_LEFT);

		/* Vertical Lines */
		final Line l = FXCalendarUtility.getLine(0, 0, 0, imgSize, imgSizeQuar, 0);
		final Line l1 = FXCalendarUtility.getLine(0, 0, 0, imgSize, imgSizeQuar * 2, 0);
		final Line l2 = FXCalendarUtility.getLine(0, 0, 0, imgSize, imgSizeQuar * 3, 0);
		/* Horizontal Lines */
		final Line l3 = FXCalendarUtility.getLine(0, 0, imgSize, 0, 0, imgSizeQuar);
		final Line l4 = FXCalendarUtility.getLine(0, 0, imgSize, 0, 0, imgSizeQuar * 2);
		final Line l5 = FXCalendarUtility.getLine(0, 0, imgSize, 0, 0, imgSizeQuar * 3);
		/* Circle */
		final Circle c = new Circle();
		c.getStyleClass()
				.add("calendar-image-circle");
		c.setRadius(imgSizeQuar / 2);
		c.setTranslateX(imgSizeQuar * 3);
		c.setTranslateY(imgSizeQuar);
		img.getChildren()
				.addAll(l, l1, l2, l3, l4, l5, c);

		gp.getChildren()
				.add(img);
		gp.setTranslateX(5);
		gp.setTranslateY(1);
		return gp;
	}

	private static Line getLine(final double startX, final double startY, final double endX, final double endY,
			final double translateX, final double translateY) {

		final Line l = new Line();
		l.getStyleClass()
				.add("calendar-image-line");
		l.setStartX(startX);
		l.setStartY(startY);
		l.setEndX(endX);
		l.setEndY(endY);
		l.setSmooth(true);
		l.setTranslateX(translateX);
		l.setTranslateY(translateY);
		return l;
	}

}

package com.codigo.aplios.group.timeline.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.codigo.aplios.group.sdk.core.compare.CompareOperator;
import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;
import com.codigo.aplios.group.sdk.core.datetime.TimeSpec;
import com.codigo.aplios.group.sdk.core.helper.StringOperator;

public class TimeFormatter implements ITimeFormatter {

	private static volatile TimeFormatter instance;

	private final Locale locale;
	private final String listSeparator;
	private final String contextSeparator;
	private final String startEndSeparator;
	private final String durationSeparator;
	private final String durationLastItemSeparator;
	private final String durationValueSeparator;
	private final String durationItemSeparator;
	private final String intervalStartClosed;
	private final String intervalStartOpen;
	private final String intervalStartOpenIso;
	private final String intervalEndClosed;
	private final String intervalEndOpen;
	private final String intervalEndOpenIso;
	private final DateTimeFormatter dateTimeFormat;
	private final DateTimeFormatter shortDateFormat;
	private final DateTimeFormatter longTimeFormat;
	private final DateTimeFormatter shortTimeFormat;
	private final DurationFormatType durationType;
	private final boolean useDurationSeconds;
	private final boolean useIsoIntervalNotation;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>TimeFormatter</code>
	 *
	 * @category constructor
	 */
	public TimeFormatter() {

		this(Locale.getDefault());
	}

	/**
	 * @param locale
	 *
	 * @category constructor
	 */
	public TimeFormatter(Locale locale) {

		this.contextSeparator = "; ";
		this.startEndSeparator = " - ";
		this.durationSeparator = " | ";
		this.dateTimeFormat = null;
		this.shortDateFormat = null;
		this.longTimeFormat = null;
		this.shortTimeFormat = null;
		this.durationType = DurationFormatType.COMPACT;
		this.useDurationSeconds = false;
		this.useIsoIntervalNotation = false;
		this.durationItemSeparator = " ";
		this.durationLastItemSeparator = " ";
		this.durationValueSeparator = " ";
		this.intervalStartClosed = "[";
		this.intervalStartOpen = "(";
		this.intervalStartOpenIso = "]";
		this.intervalEndClosed = "]";
		this.intervalEndOpen = ")";
		this.intervalEndOpenIso = "[";

		if (locale == null)
			locale = Locale.getDefault();

		this.locale = locale;
		// listSeparator = culture.TextInfo.ListSeparator;
		this.listSeparator = "";
//		this.contextSeparator = contextSeparator;
//		this.startEndSeparator = startEndSeparator;
//		this.durationSeparator = durationSeparator;
//		this.durationItemSeparator = durationItemSeparator;
//		this.durationLastItemSeparator = durationLastItemSeparator;
//		this.durationValueSeparator = durationValueSeparator;
//		this.intervalStartClosed = intervalStartClosed;
//		this.intervalStartOpen = intervalStartOpen;
//		this.intervalStartOpenIso = intervalStartOpenIso;
//		this.intervalEndClosed = intervalEndClosed;
//		this.intervalEndOpen = intervalEndOpen;
//		this.intervalEndOpenIso = intervalEndOpenIso;
//		this.dateTimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
//		this.shortDateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
//		this.longTimeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
//		this.shortTimeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
//		this.durationType = durationType;
//		this.useDurationSeconds = useDurationSeconds;
//		this.useIsoIntervalNotation = useIsoIntervalNotation;
	}

	public static TimeFormatter getInstance() {

		synchronized (TimeFormatter.class) {

			if (TimeFormatter.instance == null)
				if (TimeFormatter.instance == null)
					TimeFormatter.instance = new TimeFormatter();

			return TimeFormatter.instance;
		}
	}

	/*
	 * set { if ( value == null ) { throw new ArgumentNullException( "value" ); }
	 * lock ( mutex ) { instance = value; } }
	 */

	public Locale getlocale() {

		return this.locale;
	}

	@Override
	public String getListSeparator() {

		return this.listSeparator;
	}

	@Override
	public String getContextSeparator() {

		return this.contextSeparator;
	}

	@Override
	public String getStartEndSeparator() {

		return this.startEndSeparator;
	}

	@Override
	public String getDurationSeparator() {

		return this.durationSeparator;
	}

	@Override
	public String getDurationItemSeparator() {

		return this.durationItemSeparator;
	}

	@Override
	public String getDurationLastItemSeparator() {

		return this.durationLastItemSeparator;
	}

	@Override
	public String getDurationValueSeparator() {

		return this.durationValueSeparator;
	}

	@Override
	public String getIntervalStartClosed() {

		return this.intervalStartClosed;
	}

	@Override
	public String getIntervalStartOpen() {

		return this.intervalStartOpen;
	}

	@Override
	public String getIntervalStartOpenIso() {

		return this.intervalStartOpenIso;
	}

	@Override
	public String getIntervalEndClosed() {

		return this.intervalEndClosed;
	}

	@Override
	public String getIntervalEndOpen() {

		return this.intervalEndOpen;
	}

	@Override
	public String getIntervalEndOpenIso() {

		return this.intervalEndOpenIso;
	}

	@Override
	public String getDateTimeFormat() {

		return this.dateTimeFormat.toString();
	}

	@Override
	public String getShortDateFormat() {

		return this.shortDateFormat.toString();
	}

	@Override
	public String getLongTimeFormat() {

		return this.longTimeFormat.toString();
	}

	@Override
	public String getShortTimeFormat() {

		return this.shortTimeFormat.toString();
	}

	@Override
	public DurationFormatType getDurationType() {

		return this.durationType;
	}

	@Override
	public boolean getUseDurationSeconds() {

		return this.useDurationSeconds;
	}

	@Override
	public boolean getUseIsoIntervalNotation() {

		return this.useIsoIntervalNotation;
	}

	@Override
	public String getCollection(final int count) {

		return String.format("Count = %d", count);
	}

	@Override
	public String getCollectionPeriod(final int count, final LocalDateTime start, final LocalDateTime end,
			final TimeSpan duration) {

		return String.format("%s%s %s", this.getCollection(count), this.getListSeparator(),
				this.getPeriod(start, end, duration));
	}

	@Override
	public String getDateTime(final LocalDateTime dateTime) {

		return this.dateTimeFormat != null
				? dateTime.format(this.dateTimeFormat)
				: dateTime.toString();
	}

	@Override
	public String getShortDate(final LocalDateTime dateTime) {

		return StringOperator.isNullOrEmpty(this.shortDateFormat.toString())
				? dateTime.format(this.shortDateFormat)
				: ("d");
	}

	@Override
	public String getLongTime(final LocalDateTime dateTime) {

		return StringOperator.isNullOrEmpty(this.longTimeFormat.toString())
				? dateTime.format(this.longTimeFormat)
				: ("T");
	}

	@Override
	public String getShortTime(final LocalDateTime dateTime) {

		return !StringOperator.isNullOrEmpty(this.shortTimeFormat.toString())
				? dateTime.format(this.shortTimeFormat)
				: ("t");
	}

	@Override
	public String getPeriod(final LocalDateTime start, final LocalDateTime end) {

		return null;
		// return getPeriod(start, end, Period.between(end, start));
	}

	@Override
	public String getDuration(final TimeSpan timeSpan) {

		return this.getDuration(timeSpan, this.durationType);
	}

	@Override
	public String getDuration(final TimeSpan timeSpan, final DurationFormatType durationFormatType) {

		switch (durationFormatType) {

		case DETAILED:
			final int days = timeSpan.days()
				.get();
			final int hours = timeSpan.hours()
				.get();
			final int minutes = timeSpan.minutes()
				.get();
			final int seconds = this.useDurationSeconds
					? (int) timeSpan.seconds()
						.get()
					: 0;
			return this.getDuration(0, 0, days, hours, minutes, seconds);

		default:
			return this.useDurationSeconds
					? String.format("{0}.{1:00}:{2:00}:{3:00}", timeSpan.days()
						.get(),
							timeSpan.hours()
								.get(),
							timeSpan.minutes()
								.get(),
							timeSpan.seconds()
								.get())
					: String.format("{0}.{1:00}:{2:00}", timeSpan.days()
						.get(),
							timeSpan.hours()
								.get(),
							timeSpan.minutes()
								.get());
		}
	}

	@Override
	public String getDuration(final int years, final int months, final int days, final int hours, final int minutes,
			final int seconds) {

		final StringBuilder sb = new StringBuilder();
		/*
		 * if (years != 0) { sb.append(years); sb.append(DurationValueSeparator);
		 * sb.append(years == 1 ? Strings.TimeSpanYear : Strings.TimeSpanYears); }
		 *
		 * if (months != 0) { if (sb.Length > 0) { sb.Append(days == 0 && hours == 0 &&
		 * minutes == 0 && seconds == 0 ? DurationLastItemSeparator :
		 * DurationItemSeparator); } sb.Append(months);
		 * sb.Append(DurationValueSeparator); sb.Append(months == 1 ?
		 * Strings.TimeSpanMonth : Strings.TimeSpanMonths); }
		 *
		 * if (days != 0) { if (sb.Length > 0) { sb.Append( hours == 0 && minutes == 0
		 * && seconds == 0 ? DurationLastItemSeparator : DurationItemSeparator); }
		 * sb.Append(days); sb.Append(DurationValueSeparator); sb.Append(days == 1 ?
		 * Strings.TimeSpanDay : Strings.TimeSpanDays); }
		 *
		 * if (hours != 0) { if (sb.Length > 0) { sb.Append(minutes == 0 && seconds == 0
		 * ? DurationLastItemSeparator : DurationItemSeparator); } sb.Append(hours);
		 * sb.Append(DurationValueSeparator); sb.Append(hours == 1 ?
		 * Strings.TimeSpanHour : Strings.TimeSpanHours); }
		 *
		 * if (minutes != 0) { if (sb.Length > 0) { sb.Append(seconds == 0 ?
		 * DurationLastItemSeparator : DurationItemSeparator); } sb.Append(minutes);
		 * sb.Append(DurationValueSeparator); sb.Append(minutes == 1 ?
		 * Strings.TimeSpanMinute : Strings.TimeSpanMinutes); }
		 *
		 * if (seconds != 0) { if (sb.Length > 0) {
		 * sb.Append(DurationLastItemSeparator); } sb.Append(seconds);
		 * sb.Append(DurationValueSeparator); sb.Append(seconds == 1 ?
		 * Strings.TimeSpanSecond : Strings.TimeSpanSeconds); }
		 */
		return sb.toString();
	}

	@Override
	public String getPeriod(final LocalDateTime start, final LocalDateTime end, final TimeSpan duration) {

		return null;

		/*
		 * if (end < start) { throw new ArgumentOutOfRangeException("end"); }
		 *
		 * bool startHasTimeOfDay = TimeTool.HasTimeOfDay(start);
		 *
		 * // no duration - schow start date (optionally with the time part) if
		 * (duration == TimeSpec.MinPeriodDuration) { return startHasTimeOfDay ?
		 * getDateTime(start) : getShortDate(start); }
		 *
		 * // within one day: show full start, end time and suration if
		 * (TimeCompare.IsSameDay(start, end)) { return GetDateTime(start) +
		 * startEndSeparator + getLongTime(end) + durationSeparator +
		 * getDuration(duration); }
		 *
		 * // show start date, end date and duration (optionally with the time part)
		 * boolean endHasTimeOfDay = TimeTool.HasTimeOfDay(start); boolean hasTimeOfDays
		 * = startHasTimeOfDay || endHasTimeOfDay; String startPart = hasTimeOfDays ?
		 * getDateTime(start) : getShortDate(start); String endPart = hasTimeOfDays ?
		 * getDateTime(end) : getShortDate(end); return startPart + startEndSeparator +
		 * endPart + durationSeparator + getDuration(duration);
		 */
	}

	@Override
	public String getCalendarPeriod(final String start, final String end, final TimeSpan duration) {

		final String timePeriod = start.equals(end)
				? start
				: start + this.startEndSeparator
						+ end;
		return timePeriod + this.durationSeparator
				+ this.getDuration(duration);
	}

	@Override
	public String getCalendarPeriod(final String context, final String start, final String end,
			final TimeSpan duration) {

		final String timePeriod = start.equals(end)
				? start
				: start + this.startEndSeparator
						+ end;
		return context + this.contextSeparator
				+ timePeriod
				+ this.durationSeparator
				+ this.getDuration(duration);
	}

	@Override
	public String getCalendarPeriod(final String startContext, final String endContext, final String start,
			final String end, final TimeSpan duration) {

		final String contextPeriod = startContext.equals(endContext)
				? startContext
				: startContext + this.startEndSeparator
						+ endContext;
		final String timePeriod = start.equals(end)
				? start
				: start + this.startEndSeparator
						+ end;

		return contextPeriod + this.contextSeparator
				+ timePeriod
				+ this.durationSeparator
				+ this.getDuration(duration);

	}

	@Override
	public String getInterval(final LocalDateTime start, final LocalDateTime end, final IntervalEdge startEdge,
			final IntervalEdge endEdge, final TimeSpan duration) {

		if (CompareOperator.LESSTHEN.compare(end, start))
			throw new IllegalArgumentException("end");

		final StringBuilder sb = new StringBuilder();

		switch (startEdge) {

		case CLOSED:
			sb.append(this.intervalStartClosed);
			break;

		case OPEN:
			sb.append(this.useIsoIntervalNotation
					? this.intervalStartOpenIso
					: this.intervalStartOpen);
			break;

		default:
			sb.append("");
		}

		final boolean startHasTimeOfDay = false;// TimeTool.HasTimeOfDay(start);

		// no duration - schow start date (optionally with the time part)
		if (duration == TimeSpec.MINPERIODDURATION)
			sb.append(startHasTimeOfDay
					? this.getDateTime(start)
					: this.getShortDate(start));

		switch (endEdge) {

		case CLOSED:
			sb.append(this.intervalEndClosed);
			break;

		case OPEN:
			sb.append(this.useIsoIntervalNotation
					? this.intervalEndOpenIso
					: this.intervalEndOpen);
			break;

		default:
			sb.append("");
		}

//		if (addDuration) {
//			sb.append(durationSeparator);
//			sb.append(getDuration(duration));
//		}

		return sb.toString();
	}

	@Override
	public Locale getLocale() {

		// TODO Auto-generated method stub
		return null;
	}
}

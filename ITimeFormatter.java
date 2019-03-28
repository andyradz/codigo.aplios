package com.codigo.aplios.group.timeline.common;

import java.time.LocalDateTime;
import java.util.Locale;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;

// ------------------------------------------------------------------------
public interface ITimeFormatter {

	Locale getLocale();

	String getListSeparator();

	String getContextSeparator();

	String getStartEndSeparator();

	String getDurationSeparator();

	String getDurationItemSeparator();

	String getDurationLastItemSeparator();

	String getDurationValueSeparator();

	String getIntervalStartClosed();

	String getIntervalStartOpen();

	String getIntervalStartOpenIso();

	String getIntervalEndClosed();

	String getIntervalEndOpen();

	String getIntervalEndOpenIso();

	String getDateTimeFormat();

	String getShortDateFormat();

	String getLongTimeFormat();

	String getShortTimeFormat();

	DurationFormatType getDurationType();

	boolean getUseIsoIntervalNotation();

	boolean getUseDurationSeconds();

	String getCollection(int count);

	String getCollectionPeriod(int count, LocalDateTime start, LocalDateTime end, TimeSpan duration);

	String getDateTime(LocalDateTime dateTime);

	String getShortDate(LocalDateTime dateTime);

	String getLongTime(LocalDateTime dateTime);

	String getShortTime(LocalDateTime dateTime);

	String getPeriod(LocalDateTime start, LocalDateTime end);

	String getDuration(TimeSpan timeSpan);

	String getDuration(TimeSpan timeSpan, DurationFormatType durationFormatType);

	String getDuration(int years, int months, int days, int hours, int minutes, int seconds);

	String getPeriod(LocalDateTime start, LocalDateTime end, TimeSpan duration);

	String getInterval(LocalDateTime start, LocalDateTime end, IntervalEdge startEdge, IntervalEdge endEdge,
			TimeSpan duration);

	String getCalendarPeriod(String start, String end, TimeSpan duration);

	String getCalendarPeriod(String context, String start, String end, TimeSpan duration);

	String getCalendarPeriod(String startContext, String endContext, String start, String end, TimeSpan duration);
}
package com.codigo.aplios.domain.model.calendar;

import java.time.DayOfWeek;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.707+0100")
@StaticMetamodel(CalendarDay.class)
public class CalendarDay_ {
	public static volatile SingularAttribute<CalendarDay, CalendarPrimaryKey> calendarPrimaryKey;
	public static volatile SingularAttribute<CalendarDay, Long> orderNumber;
	public static volatile SingularAttribute<CalendarDay, Boolean> firstDayInMonth;
	public static volatile SingularAttribute<CalendarDay, Boolean> lastDayInMonth;
	public static volatile SingularAttribute<CalendarDay, Boolean> beginDayOfYear;
	public static volatile SingularAttribute<CalendarDay, Boolean> endDayOfYear;
	public static volatile SingularAttribute<CalendarDay, DayOfWeek> dayName;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInWeek;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInWeekend;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInMonth;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInQuoter;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInYear;
	public static volatile SingularAttribute<CalendarDay, Integer> dayNumberInQaliYear;
	public static volatile SingularAttribute<CalendarDay, Calendar> calendar;
}

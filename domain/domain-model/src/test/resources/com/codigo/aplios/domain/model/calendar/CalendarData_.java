package com.codigo.aplios.domain.model.calendar;

import com.codigo.aplios.domain.model.common.EntityModel_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.700+0100")
@StaticMetamodel(CalendarData.class)
public class CalendarData_ extends EntityModel_ {
	public static volatile SingularAttribute<CalendarData, Date> date;
	public static volatile SingularAttribute<CalendarData, Integer> year;
	public static volatile SingularAttribute<CalendarData, Integer> dayNumInYear;
	public static volatile SingularAttribute<CalendarData, Integer> dayNumInMonth;
	public static volatile SingularAttribute<CalendarData, Integer> dayNumInWeek;
	public static volatile SingularAttribute<CalendarData, String> monthName;
	public static volatile SingularAttribute<CalendarData, String> monthCode;
	public static volatile SingularAttribute<CalendarData, Integer> monthNumInYear;
	public static volatile SingularAttribute<CalendarData, Integer> quaterNum;
}

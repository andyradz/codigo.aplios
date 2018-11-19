package com.codigo.aplios.domain.model.calendar;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.687+0100")
@StaticMetamodel(Calendar.class)
public class Calendar_ {
	public static volatile SingularAttribute<Calendar, CalendarPrimaryKey> calendarPrimaryKey;
	public static volatile SingularAttribute<Calendar, String> name;
	public static volatile SingularAttribute<Calendar, CalendarDay> calendarDay;
}

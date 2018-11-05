package com.codigo.aplios.sdk.core.period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

public class HurricaneSeasonQuery implements TemporalQuery<Boolean> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.time.temporal.TemporalQuery#queryFrom(java.time.temporal. TemporalAccessor)
	 */
	@Override
	public Boolean queryFrom(final TemporalAccessor temporal) {

		final LocalDate date = LocalDate.from(temporal);

		final MonthDay juneFirst = MonthDay.of(Month.JUNE.getValue(), 1);
		final MonthDay novemberThirty = MonthDay.of(Month.NOVEMBER.getValue(), 30);

		if (date.isAfter(juneFirst.atYear(date.getYear())) && date.isBefore(novemberThirty.atYear(date.getYear())))
			return true;
		else
			return false;
	}

	public static void main(final String[] args) {

		final LocalDate date = LocalDate.of(2014, Month.JUNE, 30);
		date.query(new HurricaneSeasonQuery());

		final TemporalQuery<ZoneId> query = TemporalQueries.zone();

		final LocalDateTime date1 = LocalDateTime.of(2014, Month.DECEMBER, 02, 0, 0);
		final ZonedDateTime zonedDate = ZonedDateTime.of(date1, ZoneId.of("Pacific/Chatham"));

		final ZoneId zoneId = zonedDate.query(query);

		System.out.println(zoneId); // "Pacific/Chatham"

	}
}

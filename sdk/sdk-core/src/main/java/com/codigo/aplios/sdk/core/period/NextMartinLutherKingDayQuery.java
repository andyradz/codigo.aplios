package com.codigo.aplios.sdk.core.period;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;

public class NextMartinLutherKingDayQuery implements TemporalQuery<LocalDate> {

	@Override
	public LocalDate queryFrom(final TemporalAccessor temporal) {

		final LocalDate date = LocalDate.from(temporal);
		final LocalDate currentYearMLKDay = getMartinLutherKingDayForDateInYear(date.getYear());

		final Period periodToCurrentYearMLKDay = Period.between(date, currentYearMLKDay);

		if (periodToCurrentYearMLKDay.isNegative() || periodToCurrentYearMLKDay.isZero())
			return getMartinLutherKingDayForDateInYear(date.getYear() + 1);
		else
			return currentYearMLKDay;
	}

	private LocalDate getMartinLutherKingDayForDateInYear(final int year) {

		return LocalDate.of(year, Month.JANUARY, 1)
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	}
}

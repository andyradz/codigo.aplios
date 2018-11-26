package com.codigo.aplios.timeline;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQuery;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekendDayTest {

	@Disabled
	@Test
	@DisplayName("Test sprawdza czy wskazana data jest dniem przypdającym na weekend")
	public void testDayInWeekRequireSunday() {

		final LocalDateTime localDate = LocalDateTime.of(2018, 11, 23, 0, 0);
		localDate.toInstant(ZoneOffset.UTC);

		final TemporalQuery<Boolean> IS_WEEKEND_QUERY = t -> t.get(ChronoField.DAY_OF_WEEK) >= 5;

		final boolean isWeekandDay = localDate.query(IS_WEEKEND_QUERY);

		MatcherAssert.assertThat(true, Matchers.is(isWeekandDay));
	}
}

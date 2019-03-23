package com.codigo.aplios.group.timeline.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.group.timeline.common.TimeSpan;

public class TimeSpanTest {

	@Test
	public void testTicksPerMillisecond_Has_To_Be_10000() {

		MatcherAssert.assertThat(10000l, CoreMatchers.is(TimeSpan.TICKSPERMILLISECOND));
	}

	@Test
	public void testTicksPerSecond_Has_To_Be_10000000() {

		MatcherAssert.assertThat(10000000l, CoreMatchers.is(TimeSpan.TICKSPERSECOND));
	}

	@Test
	public void testTicksPerMinute_Has_To_Be_600000000() {

		MatcherAssert.assertThat(600000000l, CoreMatchers.is(TimeSpan.TICKSPERMINUTE));
	}

	@Test
	public void testTicksPerHour_Has_To_Be_36000000000() {

		MatcherAssert.assertThat(36000000000l, CoreMatchers.is(TimeSpan.TICKSPERHOUR));
	}

	@Test
	public void testTicksPerDay_Has_To_Be_864000000000() {

		MatcherAssert.assertThat(864000000000l, CoreMatchers.is(TimeSpan.TICKSPERDAY));
	}

	@Test
	public void testConstructor_Passing_Ticks() {

		final TimeSpan span = new TimeSpan(
				864000000000l);

		MatcherAssert.assertThat(864000000000l, CoreMatchers.is(span.ticks()
			.get()));
	}

	@Test
	public void test_test3() {

		final TimeSpan time = TimeSpan.fromTicks(111_222_333_444_555L);

		MatcherAssert.assertThat(111_222_333_444_555L, CoreMatchers.is(time.ticks()
			.get()));

		MatcherAssert.assertThat(128, CoreMatchers.is(time.days()
			.get()));

		MatcherAssert.assertThat(17, CoreMatchers.is(time.hours()
			.get()));

		MatcherAssert.assertThat(30, CoreMatchers.is(time.minutes()
			.get()));

		MatcherAssert.assertThat(33, CoreMatchers.is(time.seconds()
			.get()));

		MatcherAssert.assertThat(344, CoreMatchers.is(time.milliseconds()
			.get()));

		double val = BigDecimal.valueOf(time.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue();

		MatcherAssert.assertThat(128.730d, CoreMatchers.is(val));

		val = BigDecimal.valueOf(time.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue();
		MatcherAssert.assertThat(3_089.509d, CoreMatchers.is(val));

		val = BigDecimal.valueOf(time.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue();
		MatcherAssert.assertThat(185_370.556d, CoreMatchers.is(val));

		val = BigDecimal.valueOf(time.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue();
		MatcherAssert.assertThat(11_122_233.344d, CoreMatchers.is(val));

		val = BigDecimal.valueOf(time.totalMilliseconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue();

		MatcherAssert.assertThat(11_122_233_344.456d, CoreMatchers.is(val));
	}

	@Test
	public void test_test2() {

		final TimeSpan time = TimeSpan.fromTicks(0L);

		MatcherAssert.assertThat(true, CoreMatchers.is(time.isZero()));
	}

	@Test
	public void test_test1() {

		final TimeSpan time = TimeSpan.fromTicks(0);

		MatcherAssert.assertThat(0L, CoreMatchers.is(time.ticks()
			.get()));

		MatcherAssert.assertThat(0, CoreMatchers.is(time.days()
			.get()));

		MatcherAssert.assertThat(0, CoreMatchers.is(time.hours()
			.get()));

		MatcherAssert.assertThat(0, CoreMatchers.is(time.minutes()
			.get()));

		MatcherAssert.assertThat(0, CoreMatchers.is(time.seconds()
			.get()));

		MatcherAssert.assertThat(0, CoreMatchers.is(time.milliseconds()
			.get()));

		MatcherAssert.assertThat(0d, CoreMatchers.is(time.totalDays()
			.get()));

		MatcherAssert.assertThat(0d, CoreMatchers.is(time.totalHours()
			.get()));

		MatcherAssert.assertThat(0d, CoreMatchers.is(time.totalMinutes()
			.get()));

		MatcherAssert.assertThat(0d, CoreMatchers.is(time.totalSeconds()
			.get()));

		MatcherAssert.assertThat(0d, CoreMatchers.is(time.totalMilliseconds()
			.get()));
	}
}

package com.codigo.aplios.group.sdk.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;

//https://docs.microsoft.com/en-us/dotnet/api/system.timespan.ticks?redirectedfrom=MSDN&view=netframework-4.7.2#System_TimeSpan_Ticks
//The example displays the following output if the current culture is en-US:
// TimeSpan( 1 )                                     00:00:00.0000001
// Days               0       TotalDays                         0.000
// Hours              0       TotalHours                        0.000
// Minutes            0       TotalMinutes                      0.000
// Seconds            0       TotalSeconds                      0.000
// Milliseconds       0       TotalMilliseconds                 0.000
//                            Ticks                                 1
//
// TimeSpan( 111222333444555 )                   128.17:30:33.3444555
// Days             128       TotalDays                       128.730
// Hours             17       TotalHours                    3,089.509
// Minutes           30       TotalMinutes                185,370.556
// Seconds           33       TotalSeconds             11,122,233.344
// Milliseconds     344       TotalMilliseconds    11,122,233,344.456
//                            Ticks               111,222,333,444,555
//
// TimeSpan( 10, 20, 30, 40, 50 )                 10.20:30:40.0500000
// Days              10       TotalDays                        10.855
// Hours             20       TotalHours                      260.511
// Minutes           30       TotalMinutes                 15,630.668
// Seconds           40       TotalSeconds                937,840.050
// Milliseconds      50       TotalMilliseconds       937,840,050.000
//                            Ticks                 9,378,400,500,000
//
// TimeSpan( 1111, 2222, 3333, 4444, 5555 )     1205.22:47:09.5550000
// Days            1205       TotalDays                     1,205.949
// Hours             22       TotalHours                   28,942.786
// Minutes           47       TotalMinutes              1,736,567.159
// Seconds            9       TotalSeconds            104,194,029.555
// Milliseconds     555       TotalMilliseconds   104,194,029,555.000
//                            Ticks             1,041,940,295,550,000
//
// FromDays( 20.84745602 )                        20.20:20:20.2000000
// Days              20       TotalDays                        20.847
// Hours             20       TotalHours                      500.339
// Minutes           20       TotalMinutes                 30,020.337
// Seconds           20       TotalSeconds              1,801,220.200
// Milliseconds     200       TotalMilliseconds     1,801,220,200.000
//                            Ticks                18,012,202,000,000

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

		final TimeSpan span = new TimeSpan(864000000000l);

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

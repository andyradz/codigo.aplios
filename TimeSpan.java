package com.codigo.aplios.group.timeline.common;

import java.util.Objects;

import com.codigo.aplios.group.timeline.common.helper.Property;

public class TimeSpan {

//https://docs.microsoft.com/en-us/dotnet/api/system.timespan.ticks?redirectedfrom=MSDN&view=netframework-4.7.2#System_TimeSpan_Ticks
// The example displays the following output if the current culture is en-US:
//    TimeSpan( 1 )                                     00:00:00.0000001
//    Days               0       TotalDays                         0.000
//    Hours              0       TotalHours                        0.000
//    Minutes            0       TotalMinutes                      0.000
//    Seconds            0       TotalSeconds                      0.000
//    Milliseconds       0       TotalMilliseconds                 0.000
//                               Ticks                                 1
//
//    TimeSpan( 111222333444555 )                   128.17:30:33.3444555
//    Days             128       TotalDays                       128.730
//    Hours             17       TotalHours                    3,089.509
//    Minutes           30       TotalMinutes                185,370.556
//    Seconds           33       TotalSeconds             11,122,233.344
//    Milliseconds     344       TotalMilliseconds    11,122,233,344.456
//                               Ticks               111,222,333,444,555
//
//    TimeSpan( 10, 20, 30, 40, 50 )                 10.20:30:40.0500000
//    Days              10       TotalDays                        10.855
//    Hours             20       TotalHours                      260.511
//    Minutes           30       TotalMinutes                 15,630.668
//    Seconds           40       TotalSeconds                937,840.050
//    Milliseconds      50       TotalMilliseconds       937,840,050.000
//                               Ticks                 9,378,400,500,000
//
//    TimeSpan( 1111, 2222, 3333, 4444, 5555 )     1205.22:47:09.5550000
//    Days            1205       TotalDays                     1,205.949
//    Hours             22       TotalHours                   28,942.786
//    Minutes           47       TotalMinutes              1,736,567.159
//    Seconds            9       TotalSeconds            104,194,029.555
//    Milliseconds     555       TotalMilliseconds   104,194,029,555.000
//                               Ticks             1,041,940,295,550,000
//
//    FromDays( 20.84745602 )                        20.20:20:20.2000000
//    Days              20       TotalDays                        20.847
//    Hours             20       TotalHours                      500.339
//    Minutes           20       TotalMinutes                 30,020.337
//    Seconds           20       TotalSeconds              1,801,220.200
//    Milliseconds     200       TotalMilliseconds     1,801,220,200.000
//                               Ticks                18,012,202,000,000

	public static void main(final String[] args) {

		for (int indexLoop = 1; indexLoop <= 5; indexLoop++) {
			TimeSpan span = TimeSpan.fromTicks(0L);

			switch (indexLoop) {
			case 1:
				span = new TimeSpan(
						1);
				break;
			case 2:
				span = new TimeSpan(
						111222333444555L);
				break;

			case 3:
				span = new TimeSpan(
						10,
						20,
						30,
						40,
						50);
				break;

			case 4:
				span = new TimeSpan(
						1111,
						2222,
						3333,
						4444,
						5555);
				break;

			case 5:
				span = TimeSpan.fromDays(20.84745602D);
				break;

			default:
				return;

			}

			System.out.println(span);
			System.out.println(String.format("%.7f",
					span.totalDays()
						.get()));
			System.out.println(String.format("%.7f",
					span.totalHours()
						.get()));
			System.out.println(String.format("%.7f",
					span.totalMinutes()
						.get()));
			System.out.println(String.format("%.7f",
					span.totalSeconds()
						.get()));
			System.out.println(String.format("%.7f",
					span.totalMilliseconds()
						.get()));
			System.out.println(String.format("%d",
					span.ticks()
						.get()));

			System.out.println(String.format("%d", span.days()
				.get()));

		}
	}

	/*
	 * A single tick represents one hundred nanoseconds or one ten-millionth of a
	 * second. There are 10,000 ticks in a millisecond.
	 *
	 * A tick represents the total number of ticks in local time, which is midnight
	 * on January 1st in the year 0001. But a tick is also smallest unit for
	 * TimeSpan also. Since ticks are Int64, so if miliseconds used instead of
	 * ticks, there can be a information losing.
	 */

	public static TimeSpan fromDays(final double days) {

		return new TimeSpan(
				(long) (Math.ceil(days * 24.d
						* 3600.d
						* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromHours(final double hours) {

		return new TimeSpan(
				(long) (Math.ceil(hours * 3600.d
						* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromMinutes(final double minutes) {

		return new TimeSpan(
				(long) (Math.ceil(minutes * 60.d
						* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromSeconds(final double seconds) {

		return new TimeSpan(
				(long) (Math.ceil(seconds * 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromMilliseconds(final double milliseconds) {

		return new TimeSpan(
				(long) (Math.ceil(milliseconds) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromTicks(final long ticks) {

		return new TimeSpan(
				ticks);
	}

	public static final long TICKSPERMILLISECOND = 10_000L;
	public static final long TICKSPERSECOND = TimeSpan.TICKSPERMILLISECOND * 1_000L;
	public static final long TICKSPERMINUTE = TimeSpan.TICKSPERSECOND * 60L;
	public static final long TICKSPERHOUR = TimeSpan.TICKSPERMINUTE * 60L;
	public static final long TICKSPERDAY = TimeSpan.TICKSPERHOUR * 24L;
	public static final TimeSpan ZERO;
	public static final TimeSpan MINVALUE;
	public static final TimeSpan MAXVALUE;
	private static final String TRAILING_ZEROS = "0000000";

	static {
		ZERO = new TimeSpan(
				0L);
		MINVALUE = new TimeSpan(
				Long.MIN_VALUE);
		MAXVALUE = new TimeSpan(
				Long.MAX_VALUE);
	}

	private final Property<Long> ticks;
	private final Property<Integer> days;
	private final Property<Integer> hours;
	private final Property<Integer> minutes;
	private final Property<Integer> seconds;
	private final Property<Integer> milliseconds;
	private final Property<Double> totalDays;
	private final Property<Double> totalHours;
	private final Property<Double> totalMinutes;
	private final Property<Double> totalSeconds;
	private final Property<Double> totalMilliseconds;

	/**
	 * @category constructor
	 */
	public TimeSpan() {

		this.ticks = Property.from();
		this.days = Property.from();
		this.hours = Property.from();
		this.minutes = Property.from();
		this.seconds = Property.from();
		this.milliseconds = Property.from();
		this.totalDays = Property.from();
		this.totalHours = Property.from();
		this.totalMinutes = Property.from();
		this.totalSeconds = Property.from();
		this.totalMilliseconds = Property.from();
	}

	/**
	 * @param ticks
	 *
	 * @category constructor
	 */
	public TimeSpan(final long ticks) {

		this();

		this.ticks.set(ticks);
		this.convertTicksToTotalTime();
		this.convertTicksToTime();
	}

	/**
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @category constructor
	 */
	public TimeSpan(final int hours, final int minutes, final int seconds) {

		this(0, hours, minutes, seconds);
	}

	/**
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @category constructor
	 */
	public TimeSpan(final int days, final int hours, final int minutes, final int seconds) {

		this(days, hours, minutes, seconds, 0);
	}

	/**
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param milliseconds
	 * @category constructor
	 */
	public TimeSpan(final int days, final int hours, final int minutes, final int seconds, final int milliseconds) {

		this();

		this.days.set(days);
		this.hours.set(hours);
		this.minutes.set(minutes);
		this.seconds.set(seconds);
		this.milliseconds.set(milliseconds);
		this.ticks.set((days * TimeSpan.TICKSPERDAY) + (hours * TimeSpan.TICKSPERHOUR)
				+ (minutes * TimeSpan.TICKSPERMINUTE)
				+ (seconds * TimeSpan.TICKSPERSECOND)
				+ (milliseconds * TimeSpan.TICKSPERMILLISECOND));
		this.convertTicksToTotalTime();
	}

	public Property<Integer> hours() {

		return this.hours;
	}

	public Property<Integer> minutes() {

		return this.minutes;
	}

	public Property<Integer> seconds() {

		return this.seconds;
	}

	public Property<Integer> days() {

		return this.days;
	}

	public Property<Integer> milliseconds() {

		return this.milliseconds;
	}

	private void evalueTotalDays(final double totalDays) {

		this.totalDays.set(totalDays);
	}

	private void evalueTotalHours(final double totalHours) {

		this.totalHours.set(totalHours);
	}

	private void evalueTotalMinutes(final double totalMinutes) {

		this.totalMinutes.set(totalMinutes);
	}

	private void evalueTotalSeconds(final double totalSeconds) {

		this.totalSeconds.set(totalSeconds);
	}

	public Property<Double> totalDays() {

		return this.totalDays;
	}

	public Property<Double> totalHours() {

		return this.totalHours;
	}

	public Property<Double> totalMinutes() {

		return this.totalMinutes;
	}

	public Property<Double> totalSeconds() {

		return this.totalSeconds;
	}

	public Property<Double> totalMilliseconds() {

		return this.totalMilliseconds;
	}

	private void evalueTotalMilliseconds(final double totalMilliseconds) {

		double num = totalMilliseconds;
		if (num > 922337203685477.0)
			num = 922337203685477.0;

		if (num < -922337203685477.0)
			num = -922337203685477.0;

		this.totalMilliseconds.set(num);
	}

	public Property<Long> ticks() {

		return this.ticks;
	}

	public boolean isZero() {

		return this.equals(TimeSpan.ZERO);
	}

	private void convertTicksToTime() {

		this.days.set((int) (this.ticks.get() / (TimeSpan.TICKSPERDAY + .0d)));
		long diff = (this.ticks.get() - (TimeSpan.TICKSPERDAY * this.days.get()));
		this.hours.set((int) (diff / (TimeSpan.TICKSPERHOUR + .0d)));
		diff = (diff - (TimeSpan.TICKSPERHOUR * this.hours()
			.get()));
		this.minutes.set((int) (diff / (TimeSpan.TICKSPERMINUTE + .0d)));
		diff = (diff - (TimeSpan.TICKSPERMINUTE * this.minutes.get()));
		this.seconds.set((int) (diff / (TimeSpan.TICKSPERSECOND + .0d)));
		diff = (diff - (TimeSpan.TICKSPERSECOND * this.seconds.get()));
		this.milliseconds.set((int) (diff / (TimeSpan.TICKSPERMILLISECOND + .0d)));
	}

	private void convertTicksToTotalTime() {

		this.evalueTotalDays((this.ticks.get() * 1.1574074074074074E-12));
		this.evalueTotalHours(this.ticks.get() * 2.7777777777777777E-11);
		this.evalueTotalMinutes(this.ticks.get() * 1.6666666666666667E-09);
		this.evalueTotalSeconds(this.ticks.get() * 1E-07);
		this.evalueTotalMilliseconds((this.ticks.get() * 0.0001));
	}

	public static TimeSpan add(final TimeSpan t1, final TimeSpan t2) {

		return new TimeSpan(
				t1.ticks.get() + t2.ticks.get());
	}

	public TimeSpan add(final TimeSpan t1) {

		return new TimeSpan(
				this.ticks.get() + t1.ticks.get());
	}

	@Override
	public boolean equals(final Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (this.getClass() != other.getClass())
			return false;

		final TimeSpan otherClass = (TimeSpan) other;
		return (this.ticks.get() == otherClass.ticks()
			.get());
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.ticks);
	}

	public static boolean equals(final TimeSpan time1, final TimeSpan time2) {

		return time1.equals(time2);
	}

	public boolean greaterThan(final TimeSpan time) {

		return this.ticks.get() > time.ticks.get();
	}

	public boolean greaterThanOrEqual(final TimeSpan time) {

		return this.ticks.get() >= time.ticks.get();
	}

	public boolean notEquals(final TimeSpan time) {

		return !this.equals(time);
	}

	public boolean lessThan(final TimeSpan time) {

		return this.ticks.get() < time.ticks.get();
	}

	public boolean lessThanOrEqual(final TimeSpan time) {

		return this.ticks.get() <= time.ticks.get();
	}

	public TimeSpan subtract(final TimeSpan time) {

		return new TimeSpan(
				this.ticks.get() - time.ticks.get());
	}

	public static TimeSpan subtract(final TimeSpan time1, final TimeSpan time2) {

		return new TimeSpan(
				time1.ticks.get() - time2.ticks.get());
	}

	public TimeSpan getDuration() {

		return new TimeSpan(
				Math.abs(this.ticks.get()));
	}

	@Override
	public String toString() {

		final StringBuilder str = new StringBuilder();

		if ((this.days.get() >= 1) || (this.days.get() <= -1))
			str.append(String.format("%02d.", this.days.get()));

		str.append(String.format("%02d:", this.hours.get()));
		str.append(String.format("%02d:", this.minutes.get()));
		str.append(String.format("%02d", this.seconds.get()));
		// str.append(String.format("%02d", this.ticks.get()));

		// if (this.milliseconds.get() >= 1)
		str.append(String.format(".%d%s", this.milliseconds.get(),
				TimeSpan.TRAILING_ZEROS.substring(Integer.toString(this.milliseconds.get())
					.length())));

		return str.toString();
	}
}

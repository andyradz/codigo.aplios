package com.codigo.aplios.group.timeline.common;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.UnaryOperator;

import com.codigo.aplios.group.timeline.common.TimeSpec.TimeRelations;
import com.codigo.aplios.group.timeline.common.helper.CompareResult;
import com.codigo.aplios.group.timeline.common.helper.Valuable;

/**
 * @author andrzej.radziszewski
 *
 */
public class TimeSpan implements Comparable<TimeSpan> {

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

		return new TimeSpan(Long.class.cast(Math.ceil(days * TimeRelations.HOURSPERDAY.get()
				* 3600.d
				* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromHours(final double hours) {

		return new TimeSpan(Long.class.cast(Math.ceil(hours * 3600.d
				* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromMinutes(final double minutes) {

		return new TimeSpan(Long.class.cast(Math.ceil(minutes * 60.d
				* 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromSeconds(final double seconds) {

		return new TimeSpan(Long.class.cast(Math.ceil(seconds * 1000.d) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromMilliseconds(final double milliseconds) {

		return new TimeSpan(Long.class.cast(Math.ceil(milliseconds) * TimeSpan.TICKSPERMILLISECOND));
	}

	public static TimeSpan fromTicks(final long ticks) {

		return new TimeSpan(ticks);
	}

	public static final long TICKSPERMILLISECOND = 10_000L;
	public static final long TICKSPERSECOND = TimeSpan.TICKSPERMILLISECOND * 1_000L;
	public static final long TICKSPERMINUTE = TimeSpan.TICKSPERSECOND * TimeSpec.TimeRelations.SECONDSPERMINUTE.get();
	public static final long TICKSPERHOUR = TimeSpan.TICKSPERMINUTE * TimeSpec.TimeRelations.SECONDSPERMINUTE.get();
	public static final long TICKSPERDAY = TimeSpan.TICKSPERHOUR * TimeSpec.TimeRelations.HOURSPERDAY.get();
	public static final TimeSpan ZERO;
	public static final TimeSpan MINVALUE;
	public static final TimeSpan MAXVALUE;
	private static final String TRAILING_ZEROS = "0000000";

	private static final UnaryOperator<Double> evalueTotalMilliseconds = value -> {
		double num = value;
		if (num > 922_337_203_685_477.0d)
			num = 922_337_203_685_477.0d;

		if (num < -922_337_203_685_477.0d)
			num = -922_337_203_685_477.0d;

		return num;
	};

	/**
	 * Statyczy konstruktor obiektu klasy <code>TimeSpan</code>
	 */
	static {
		ZERO = new TimeSpan(0L);
		MINVALUE = new TimeSpan(Long.MIN_VALUE);
		MAXVALUE = new TimeSpan(Long.MAX_VALUE);
	}

	private final Valuable<Long> ticks;
	private final Valuable<Integer> days;
	private final Valuable<Integer> hours;
	private final Valuable<Integer> minutes;
	private final Valuable<Integer> seconds;
	private final Valuable<Integer> milliseconds;
	private final Valuable<Double> totalDays;
	private final Valuable<Double> totalHours;
	private final Valuable<Double> totalMinutes;
	private final Valuable<Double> totalSeconds;
	private final Valuable<Double> totalMilliseconds;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>TimeSpan</code>
	 *
	 * @category constructor
	 */
	public TimeSpan() {

		this.ticks = Valuable.from();
		this.days = Valuable.from();
		this.hours = Valuable.from();
		this.minutes = Valuable.from();
		this.seconds = Valuable.from();
		this.milliseconds = Valuable.from();
		this.totalDays = Valuable.from();
		this.totalHours = Valuable.from();
		this.totalMinutes = Valuable.from();
		this.totalSeconds = Valuable.from();
		this.totalMilliseconds = Valuable.from(null, UnaryOperator.identity(), TimeSpan.evalueTotalMilliseconds);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>TimeSpan</code>
	 *
	 * @param ticks Wartość określająca ilość tików procesora
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
	 * Podstawowy konstruktor obiektu klasy <code>TimeSpan</code>
	 *
	 * @param hours   Wartość określająca ilość godzin
	 * @param minutes Wartość określająca ilość minut
	 * @param seconds Wartość określająca ilość sekund
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

	public Valuable<Integer> hours() {

		return this.hours;
	}

	public Valuable<Integer> minutes() {

		return this.minutes;
	}

	public Valuable<Integer> seconds() {

		return this.seconds;
	}

	public Valuable<Integer> days() {

		return this.days;
	}

	public Valuable<Integer> milliseconds() {

		return this.milliseconds;
	}

	public Valuable<Double> totalDays() {

		return this.totalDays;
	}

	public Valuable<Double> totalHours() {

		return this.totalHours;
	}

	public Valuable<Double> totalMinutes() {

		return this.totalMinutes;
	}

	public Valuable<Double> totalSeconds() {

		return this.totalSeconds;
	}

	public Valuable<Double> totalMilliseconds() {

		return this.totalMilliseconds;
	}

	public Valuable<Long> ticks() {

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

		this.totalDays.set(this.ticks.get() * 1.1574074074074074E-12);
		this.totalHours.set(this.ticks.get() * 2.7777777777777777E-11);
		this.totalMinutes.set(this.ticks.get() * 1.6666666666666667E-09);
		this.totalSeconds.set(this.ticks.get() * 1E-07);
		this.totalMilliseconds.set(this.ticks.get() * 0.0001d);
	}

	public static TimeSpan add(final TimeSpan t1, final TimeSpan t2) {

		return new TimeSpan(t1.ticks.get() + t2.ticks.get());
	}

	public TimeSpan add(final TimeSpan t1) {

		return new TimeSpan(this.ticks.get() + t1.ticks.get());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.ticks);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		final StringBuilder str = new StringBuilder();

		if ((this.days.get() >= 1) || (this.days.get() <= -1))
			str.append(String.format("%02d.", this.days.get()));

		str.append(String.format("%02d:", this.hours.get()));
		str.append(String.format("%02d:", this.minutes.get()));
		str.append(String.format("%02d", this.seconds.get()));
		str.append(String.format("%02d", this.ticks.get()));

		if (this.milliseconds.get() >= 1)
			str.append(String.format(".%d%s", this.milliseconds.get(),
					TimeSpan.TRAILING_ZEROS.substring(Integer.toString(this.milliseconds.get())
						.length())));

		return str.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final TimeSpan timespan) {

		final Comparator<TimeSpan> comparator = (final TimeSpan a, final TimeSpan b) -> {

			if (Objects.isNull(a))
				return (Objects.isNull(b))
						? CompareResult.EQUALS.get()
						: CompareResult.LESSER.get();

			if (Objects.isNull(b))
				return CompareResult.GREATER.get();

			if (b == a)
				return CompareResult.EQUALS.get();

			return a.ticks.compareTo(b.ticks);
		};

		return comparator.compare(this, timespan);
	}

	public static boolean equals(final TimeSpan time1, final TimeSpan time2) {

		return time1.equals(time2);
	}

	public boolean greaterThen(final TimeSpan time) {

		return this.ticks.get() > time.ticks.get();
	}

	public boolean greaterThenOrEqual(final TimeSpan time) {

		return this.ticks.get() >= time.ticks.get();
	}

	public boolean notEquals(final TimeSpan time) {

		return !this.equals(time);
	}

	public boolean lessThen(final TimeSpan time) {

		return this.ticks.get() < time.ticks.get();
	}

	public boolean lessThenOrEqual(final TimeSpan time) {

		return this.ticks.get() <= time.ticks.get();
	}

	public TimeSpan subtract(final TimeSpan time) {

		return new TimeSpan(this.ticks.get() - time.ticks.get());
	}

	public static TimeSpan subtract(final TimeSpan time1, final TimeSpan time2) {

		return new TimeSpan(time1.ticks.get() - time2.ticks.get());
	}

	public TimeSpan getDuration() {

		return new TimeSpan(Math.abs(this.ticks.get()));
	}
}

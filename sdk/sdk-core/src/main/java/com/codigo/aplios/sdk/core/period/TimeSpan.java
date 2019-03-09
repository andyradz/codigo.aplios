// package com.codigo.aplios.sdk.core.period;
//
// import java.util.Objects;
//
// public class TimeSpan {
//
// //
// https://docs.microsoft.com/en-us/dotnet/api/system.timespan.ticks?redirectedfrom=MSDN&view=netframework-4.7.2#System_TimeSpan_Ticks
// // The example displays the following output if the current culture is en-US:
// // TimeSpan( 1 ) 00:00:00.0000001
// // Days 0 TotalDays 0.000
// // Hours 0 TotalHours 0.000
// // Minutes 0 TotalMinutes 0.000
// // Seconds 0 TotalSeconds 0.000
// // Milliseconds 0 TotalMilliseconds 0.000
// // Ticks 1
// //
// // TimeSpan( 111222333444555 ) 128.17:30:33.3444555
// // Days 128 TotalDays 128.730
// // Hours 17 TotalHours 3,089.509
// // Minutes 30 TotalMinutes 185,370.556
// // Seconds 33 TotalSeconds 11,122,233.344
// // Milliseconds 344 TotalMilliseconds 11,122,233,344.456
// // Ticks 111,222,333,444,555
// //
// // TimeSpan( 10, 20, 30, 40, 50 ) 10.20:30:40.0500000
// // Days 10 TotalDays 10.855
// // Hours 20 TotalHours 260.511
// // Minutes 30 TotalMinutes 15,630.668
// // Seconds 40 TotalSeconds 937,840.050
// // Milliseconds 50 TotalMilliseconds 937,840,050.000
// // Ticks 9,378,400,500,000
// //
// // TimeSpan( 1111, 2222, 3333, 4444, 5555 ) 1205.22:47:09.5550000
// // Days 1205 TotalDays 1,205.949
// // Hours 22 TotalHours 28,942.786
// // Minutes 47 TotalMinutes 1,736,567.159
// // Seconds 9 TotalSeconds 104,194,029.555
// // Milliseconds 555 TotalMilliseconds 104,194,029,555.000
// // Ticks 1,041,940,295,550,000
// //
// // FromDays( 20.84745602 ) 20.20:20:20.2000000
// // Days 20 TotalDays 20.847
// // Hours 20 TotalHours 500.339
// // Minutes 20 TotalMinutes 30,020.337
// // Seconds 20 TotalSeconds 1,801,220.200
// // Milliseconds 200 TotalMilliseconds 1,801,220,200.000
// // Ticks 18,012,202,000,000
//
// public static void main(final String[] args) {
//
// TimeSpan span = TimeSpan.fromTicks(111222333444555L);
//
// System.out.println(span);
// System.out.println(String.format("%.3f", span.TotalDays()));
// System.out.println(String.format("%.3f", span.TotalHours()));
// System.out.println(String.format("%.3f", span.TotalMinutes()));
// System.out.println(String.format("%.3f", span.TotalSeconds()));
// System.out.println(String.format("%.3f", span.TotalMilliseconds()));
// System.out.println(String.format("%d", span.Ticks()));
//
// span = new TimeSpan(10, 20, 30, 40, 50);
// System.out.println(span);
// System.out.println(String.format("%.3f", span.TotalDays()));
// System.out.println(String.format("%.3f", span.TotalHours()));
// System.out.println(String.format("%.3f", span.TotalMinutes()));
// System.out.println(String.format("%.3f", span.TotalSeconds()));
// System.out.println(String.format("%.3f", span.TotalMilliseconds()));
// System.out.println(String.format("%d", span.Ticks()));
//
// span = TimeSpan.fromDays(20.84745602d);
//
// System.out.println(span);
// System.out.println(String.format("%.3f", span.TotalDays()));
// System.out.println(String.format("%.3f", span.TotalHours()));
// System.out.println(String.format("%.3f", span.TotalMinutes()));
// System.out.println(String.format("%.3f", span.TotalSeconds()));
// System.out.println(String.format("%.3f", span.TotalMilliseconds()));
// System.out.println(String.format("%d", span.Ticks()));
// }
//
// /*
// * A single tick represents one hundred nanoseconds or one ten-millionth of a second. There are
// * 10,000 ticks in a millisecond.
// *
// * A tick represents the total number of ticks in local time, which is midnight on January 1st in
// * the year 0001. But a tick is also smallest unit for TimeSpan also. Since ticks are Int64, so if
// * miliseconds used instead of ticks, there can be a information losing.
// */
//
// public static TimeSpan fromDays(final double days) {
//
// return new TimeSpan((long) (Math.ceil(days * 24.d * 3600.d * 1000.d) *
// TimeSpan.TICKSPERMILLISECOND));
// }
//
// public static TimeSpan fromHours(final double hours) {
//
// return new TimeSpan((long) (Math.ceil(hours * 3600.d * 1000.d) * TimeSpan.TICKSPERMILLISECOND));
// }
//
// public static TimeSpan fromMinutes(final double minutes) {
//
// return new TimeSpan((long) (Math.ceil(minutes * 60.d * 1000.d) * TimeSpan.TICKSPERMILLISECOND));
// }
//
// public static TimeSpan fromSeconds(final double seconds) {
//
// return new TimeSpan((long) (Math.ceil(seconds * 1000.d) * TimeSpan.TICKSPERMILLISECOND));
// }
//
// public static TimeSpan fromMilliseconds(final double milliseconds) {
//
// return new TimeSpan((long) (Math.ceil(milliseconds) * TimeSpan.TICKSPERMILLISECOND));
// }
//
// public static TimeSpan fromTicks(final long ticks) {
//
// return new TimeSpan(ticks);
// }
//
// // The smallest unit of time is the tick, which is equal to 100 nanoseconds.
// private static final long TICKSPERNANOSECOND = 100L;
// private static final long TICKSPERMILLISECOND = 10_000L;
// private static final long TICKSPERSECOND = TimeSpan.TICKSPERMILLISECOND * 1_000L;
// private static final long TICKSPERMINUTE = TimeSpan.TICKSPERSECOND * 60L;
// private static final long TICKSPERHOUR = TimeSpan.TICKSPERMINUTE * 60L;
// private static final long TICKSPERDAY = TimeSpan.TICKSPERHOUR * 24L;
// public static final TimeSpan ZERO;
// public static final TimeSpan MINVALUE;
// public static final TimeSpan MAXVALUE;
// private static final String TRAILING_ZEROS = "0000000";
//
// static {
// ZERO = new TimeSpan(0L);
// MINVALUE = new TimeSpan(Long.MIN_VALUE);
// MAXVALUE = new TimeSpan(Long.MAX_VALUE);
// }
//
// private final long ticks;
// private int days;
// private int hours;
// private int minutes;
// private int seconds;
// private int milliseconds;
// private double totalDays;
// private double totalHours;
// private double totalMinutes;
// private double totalSeconds;
// private double totalMilliseconds;
//
// public int Hours() {
//
// return this.hours;
// }
//
// public int Minutes() {
//
// return this.minutes;
// }
//
// public int Seconds() {
//
// return this.seconds;
// }
//
// public int Days() {
//
// return this.days;
// }
//
// public int Milliseconds() {
//
// return this.milliseconds;
// }
//
// private void TotalDays(final double totalDays) {
//
// this.totalDays = totalDays;
// }
//
// private void TotalHours(final double totalHours) {
//
// this.totalHours = totalHours;
// }
//
// private void TotalMinutes(final double totalMinutes) {
//
// this.totalMinutes = totalMinutes;
// }
//
// private void TotalSeconds(final double totalSeconds) {
//
// this.totalSeconds = totalSeconds;
// }
//
// public double TotalDays() {
//
// return this.totalDays;
// }
//
// public double TotalHours() {
//
// return this.totalHours;
// }
//
// public double TotalMinutes() {
//
// return this.totalMinutes;
// }
//
// public double TotalSeconds() {
//
// return this.totalSeconds;
// }
//
// public double TotalMilliseconds() {
//
// return this.totalMilliseconds;
// }
//
// private void TotalMilliseconds(final double totalMilliseconds) {
//
// this.totalMilliseconds = totalMilliseconds;
// }
//
// public long Ticks() {
//
// return this.ticks;
// }
//
// public boolean isZero() {
//
// return this.equals(TimeSpan.ZERO);
// }
//
// public TimeSpan(final long ticks) {
//
// this.ticks = ticks;
// convertTicksToTotalTime();
// convertTicksToTime();
// }
//
// private void convertTicksToTime() {
//
// this.days = (int) (this.ticks / (TimeSpan.TICKSPERDAY + .0d));
// long diff = (this.ticks - (TimeSpan.TICKSPERDAY * this.days));
// this.hours = (int) (diff / (TimeSpan.TICKSPERHOUR + .0d));
// diff = (diff - (TimeSpan.TICKSPERHOUR * this.hours));
// this.minutes = (int) (diff / (TimeSpan.TICKSPERMINUTE + .0d));
// diff = (diff - (TimeSpan.TICKSPERMINUTE * this.minutes));
// this.seconds = (int) (diff / (TimeSpan.TICKSPERSECOND + .0d));
// diff = (diff - (TimeSpan.TICKSPERSECOND * this.seconds));
// this.milliseconds = (int) (((diff / TimeSpan.TICKSPERMILLISECOND) + .0d));
// }
//
// private void convertTicksToTotalTime() {
//
// TotalDays(this.ticks / (TimeSpan.TICKSPERDAY + 0.0d));
// TotalHours(this.ticks / (TimeSpan.TICKSPERHOUR + 0.0d));
// TotalMinutes(this.ticks / (TimeSpan.TICKSPERMINUTE + 0.0d));
// TotalSeconds(this.ticks / (TimeSpan.TICKSPERSECOND + 0.0d));
// TotalMilliseconds(this.ticks / (TimeSpan.TICKSPERMILLISECOND + 0.0d));
// }
//
// public TimeSpan(final int hours, final int minutes, final int seconds) {
//
// this(0, hours, minutes, seconds);
// }
//
// public TimeSpan(final int days, final int hours, final int minutes, final int seconds) {
//
// this(days, hours, minutes, seconds, 0);
// }
//
// public TimeSpan(final int days, final int hours, final int minutes, final int seconds, final int
// milliseconds) {
//
// this.days = days;
// this.hours = hours;
// this.minutes = minutes;
// this.seconds = seconds;
// this.milliseconds = milliseconds;
// this.ticks = (days * TimeSpan.TICKSPERDAY) + (hours * TimeSpan.TICKSPERHOUR)
// + (minutes * TimeSpan.TICKSPERMINUTE) + (seconds * TimeSpan.TICKSPERSECOND)
// + (milliseconds * TimeSpan.TICKSPERMILLISECOND);
// convertTicksToTotalTime();
// }
//
// public static TimeSpan add(final TimeSpan t1, final TimeSpan t2) {
//
// return new TimeSpan(t1.ticks + t2.ticks);
// }
//
// public TimeSpan add(final TimeSpan t1) {
//
// return new TimeSpan(this.ticks + t1.ticks);
// }
//
// @Override
// public boolean equals(final Object other) {
//
// if (other == null)
// return false;
// if (other == this)
// return true;
// if (this.getClass() != other.getClass())
// return false;
// final TimeSpan otherClass = (TimeSpan) other;
// return (this.ticks == otherClass.Ticks());
// }
//
// @Override
// public int hashCode() {
//
// return Objects.hash(this.ticks);
// }
//
// public boolean equals(final TimeSpan other) {
//
// return equals(other);
// }
//
// public static boolean equals(final TimeSpan time1, final TimeSpan time2) {
//
// return time1.equals(time2);
// }
//
// public boolean greaterThan(final TimeSpan time) {
//
// return this.ticks > time.Ticks();
// }
//
// public boolean greaterThanOrEqual(final TimeSpan time) {
//
// return this.ticks >= time.Ticks();
// }
//
// public boolean notEquals(final TimeSpan time) {
//
// return !equals(time);
// }
//
// public boolean lessThan(final TimeSpan time) {
//
// return this.ticks < time.Ticks();
// }
//
// public boolean lessThanOrEqual(final TimeSpan time) {
//
// return this.ticks <= time.Ticks();
// }
//
// public TimeSpan subtract(final TimeSpan time) {
//
// return new TimeSpan(this.ticks - time.Ticks());
// }
//
// public static TimeSpan subtract(final TimeSpan time1, final TimeSpan time2) {
//
// return new TimeSpan(time1.Ticks() - time2.Ticks());
// }
//
// public TimeSpan Duration() {
//
// return new TimeSpan(Math.abs(this.ticks));
// }
//
// @Override
// public String toString() {
//
// final StringBuilder str = new StringBuilder();
//
// if ((this.days >= 1) || (this.days <= -1))
// str.append(String.format("%02d.", this.days));
//
// str.append(String.format("%02d:", this.hours));
// str.append(String.format("%02d:", this.minutes));
// str.append(String.format("%02d", this.seconds));
//
// if (this.milliseconds >= 1)
// str.append(String.format(".%d%s", this.milliseconds,
// TimeSpan.TRAILING_ZEROS.substring(Integer.toString(this.milliseconds)
// .length())));
//
// return str.toString();
// }
// }

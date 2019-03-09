package com.codigo.aplios.sdk.core.period;
//
// import com.codigo.aplios.sdk.core.compare.IComparable;
//
// public class TimeSpan implements IComparable {
//
// public static TimeSpan fromDays(final double value) {
//
// return TimeSpan.interval(value, 86400000);
// }
//
// public static TimeSpan fromHours(final double value) {
//
// return TimeSpan.interval(value, 3600000);
// }
//
// private static TimeSpan interval(final double value, final int scale) {
//
// if (Double.isNaN(value))
// throw new ArgumentException(Environment.GetResourceString("Arg_CannotBeNaN"));
//
// final double num = value * scale;
// final double num2 = num + ((value >= 0.0) ? 0.5 : -0.5);
//
// if ((num2 > 922337203685477.0) || (num2 < -922337203685477.0))
// throw new OverflowException(Environment.GetResourceString("Overflow_TimeSpanTooLong"));
//
// return new TimeSpan((long) num2 * 10000L);
// }
//
// public static TimeSpan fromMilliseconds(final double value) {
//
// return TimeSpan.interval(value, 1);
// }
//
// public static TimeSpan fromMinutes(final double value) {
//
// return TimeSpan.interval(value, 60000);
// }
//
// public static final long TicksPerMillisecond = 10000L;
//
// private static final double MillisecondsPerTick = 0.0001;
//
// private static final long TicksPerSecond = 10000000L;
//
// private static final double SecondsPerTick = 1E-07;
//
// public static final long TicksPerMinute = 600000000L;
//
// private static final double MinutesPerTick = 1.6666666666666667E-09;
//
// public static final long TICKSPERHOUR = 36000000000L;
//
// private static final double HoursPerTick = 2.7777777777777777E-11;
//
// public static final long TICKSPERDAY = 864000000000L;
//
// private static final double DaysPerTick = 1.1574074074074074E-12;
//
// private static final int MillisPerSecond = 1000;
//
// private static final int MillisPerMinute = 60000;
//
// private static final int MillisPerHour = 3600000;
//
// private static final int MillisPerDay = 86400000;
//
// private static final long MaxSeconds = 922337203685L;
//
// private static final long MinSeconds = -922337203685L;
//
// private static final long MaxMilliSeconds = 922337203685477L;
//
// private static final long MinMilliSeconds = -922337203685477L;
//
// private static final long TicksPerTenthSecond = 1000000L;
//
// public static final TimeSpan Zero = new TimeSpan(0L);
//
// public static final TimeSpan MaxValue = new TimeSpan(9223372036854775807L);
//
// public static final TimeSpan MinValue = new TimeSpan(-9223372036854775808L);
//
// private long ticks;
//
// private static volatile boolean _legacyConfigChecked;
//
// private static volatile boolean _legacyMode;
//
// public long getTicks() {
//
// return this.ticks;
//
// }
//
// public int getDays() {
//
// return (int) (this.ticks / TimeSpan.TICKSPERDAY);
//
// }
//
// public int getHours() {
//
// return (int) ((this.ticks / TimeSpan.TICKSPERHOUR) % 24L);
// }
//
// public int getMilliseconds() {
//
// return (int) ((this.ticks / 10000L) % 1000L);
// }
//
// public int getMinutes() {
//
// return (int) ((this.ticks / 600000000L) % 60L);
// }
//
// public int getSeconds() {
//
// return (int) ((this.ticks / 10000000L) % 60L);
// }
//
// public double getTotalDays() {
//
// return this.ticks * 1.1574074074074074E-12;
// }
//
// public double getTotalHours() {
//
// return this.ticks * 2.7777777777777777E-11;
// }
//
// public double getTotalMilliseconds() {
//
// final double num = this.ticks * 0.0001;
//
// if (num > 922337203685477.0)
// return 922337203685477.0;
//
// if (num < -922337203685477.0)
// return -922337203685477.0;
//
// return num;
// }
//
// public double getTotalMinutes() {
//
// return this.ticks * 1.6666666666666667E-09;
// }
//
// public double gtetTotalSeconds() {
//
// return this.ticks * 1E-07;
//
// }
//
// // private static bool LegacyMode{get
// // {
// // if (!TimeSpan._legacyConfigChecked) {
// // TimeSpan._legacyMode = TimeSpan.GetLegacyFormatMode();
// // TimeSpan._legacyConfigChecked = true;
// // }
// // return TimeSpan._legacyMode;
// // }
// // }
//
// /**
// *
// * @param days
// * @param hours
// * @param minutes
// * @param seconds
// *
// * @category constructor
// */
// public TimeSpan(final long ticks) {
//
// this.ticks = ticks;
// }
//
// /**
// *
// * @param hours
// * @param minutes
// * @param seconds
// *
// * @category constructor
// */
// public TimeSpan(final int hours, final int minutes, final int seconds) {
//
// this.ticks = TimeSpan.timeToTicks(hours, minutes, seconds);
// }
//
// /**
// *
// * @param days
// * @param hours
// * @param minutes
// * @param seconds
// *
// * @category constructor
// */
// public TimeSpan(final int days, final int hours, final int minutes, final int seconds) {
//
// this = new TimeSpan(days, hours, minutes, seconds, 0);
// }
//
// /**
// *
// * @param days
// * @param hours
// * @param minutes
// * @param seconds
// * @param milliseconds
// *
// * @category constructor
// */
// public TimeSpan(final int days, final int hours, final int minutes, final int seconds, final int
// milliseconds) {
//
// final long num = (((days * 3600L * 24L) + (hours * 3600L) + (minutes * 60L) + seconds) * 1000L) +
// milliseconds;
// // if (num > 922337203685477L || num < -922337203685477L) {
// // throw new ArgumentOutOfRangeException(null,
// // Environment.GetResourceString("Overflow_TimeSpanTooLong"));
// // }
// this.ticks = num * 10000L;
// }
//
// public TimeSpan add(final TimeSpan ts) {
//
// final long num = this.ticks + ts.ticks;
//
// if (((this.ticks >> 63) == (ts.ticks >> 63)) && ((this.ticks >> 63) != (num >> 63)))
// // throw new OverflowException(Environment.GetResourceString("Overflow_TimeSpanTooLong"));
//
// return new TimeSpan(num);
// }
//
// public static int compare(final TimeSpan t1, final TimeSpan t2) {
//
// if (t1.ticks > t2.ticks)
// return 1;
//
// if (t1.ticks < t2.ticks)
// return -1;
// return 0;
// }
//
// public int compareTo(Object value)
// {
// if (value == null)
// return 1;
// if (!(value is
// TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan))
// throw new ArgumentException(Environment.GetResourceString("Arg_MustBeTimeSpan"));
// final long ticks = ((TimeSpan)value)._ticks;
// if (this._ticks > ticks)
// return 1;
// if (this._ticks < ticks)
// return -1;
// return 0;
// }
//
// public int compareTo(final TimeSpan value) {
//
// final long ticks = value._ticks;
//
// if (this._ticks > ticks)
// return 1;
//
// if (this._ticks < ticks)
// return -1;
//
// return 0;
// }
//
// public TimeSpan getDuration() {
//
// if (getTicks() ==
// TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.MinValue.Ticks)
// throw new OverflowException(Environment.GetResourceString("Overflow_Duration"));
// return new TimeSpan((this._ticks >= 0L) ? this._ticks : (-this._ticks));
// }
//
// @Override
// public boolean equals(final Object value) {
//
// return (value instanceof TimeSpan) && (this._ticks == ((TimeSpan) value)._ticks);
// }
//
// public boolean equals(final TimeSpan obj) {
//
// return this._ticks == obj._ticks;
// }
//
// public static boolean equals(final TimeSpan t1, final TimeSpan t2) {
//
// return t1._ticks == t2._ticks;
// }
//
// @Override
// public int hashCode() {
//
// return (int) this._ticks ^ (int) (this._ticks >> 32);
// }
//
// public TimeSpan negate() {
//
// if (getTicks() ==
// TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.MinValue
// .getTicks())
// throw new OverflowException(Environment.GetResourceString("Overflow_NegateTwosCompNum"));
// return new TimeSpan(-this._ticks);
// }
//
// public static TimeSpan fromSeconds(final double value) {
//
// return
// TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan
// .Interval(value, 1000);
// }
//
// public TimeSpan subtract(final TimeSpan ts) {
//
// final long num = this._ticks - ts._ticks;
// if (((this._ticks >> 63) != (ts._ticks >> 63)) && ((this._ticks >> 63) != (num >> 63)))
// throw new OverflowException(Environment.GetResourceString("Overflow_TimeSpanTooLong"));
// return new TimeSpan(num);
// }
//
// public static TimeSpan fromTicks(final long value) {
//
// return new TimeSpan(value);
// }
//
// private static long timeToTicks(final int hour, final int minute, final int second) {
//
// final long num = (hour * 3600L) + (minute * 60L) + second;
// if ((num > 922337203685L) || (num < -922337203685L))
// throw new ArgumentOutOfRangeException(null,
// Environment.GetResourceString("Overflow_TimeSpanTooLong"));
// return num * 10000000L;
// }
//
// public static TimeSpan Parse(final string s) {
//
// return TimeSpanParse.Parse(s, null);
// }
//
// public static TimeSpan Parse(final String input, final IFormatProvider formatProvider) {
//
// return TimeSpanParse.Parse(input, formatProvider);
// }
//
// public static TimeSpan ParseExact(final String input, final String format, final IFormatProvider
// formatProvider) {
//
// return TimeSpanParse.ParseExact(input, format, formatProvider, TimeSpanStyles.None);
// }
//
// public static TimeSpan ParseExact(final String input, final String[] formats,
// final IFormatProvider formatProvider) {
//
// return TimeSpanParse.ParseExactMultiple(input, formats, formatProvider, TimeSpanStyles.None);
// }
//
// public static TimeSpan ParseExact(final string input, final string format, final IFormatProvider
// formatProvider,
// final TimeSpanStyles styles) {
//
// TimeSpanParse.ValidateStyles(styles, "styles");
// return TimeSpanParse.ParseExact(input, format, formatProvider, styles);
// }
//
// public static TimeSpan ParseExact(final string input, final string[] formats, final
// IFormatProvider formatProvider,
// final TimeSpanStyles styles) {
//
// TimeSpanParse.ValidateStyles(styles, "styles");
// return TimeSpanParse.ParseExactMultiple(input, formats, formatProvider, styles);
// }
//
// public static bool TryParse(final string s, final out TimeSpan result)
// {
// return TimeSpanParse.TryParse(s, null, out result);
// }
//
// public static bool TryParse(final string input, final IFormatProvider formatProvider, final out
// TimeSpan result)
// {
// return TimeSpanParse.TryParse(input, formatProvider, out result);
// }
//
// public static bool TryParseExact(final string input, final string format, final IFormatProvider
// formatProvider, final out TimeSpan result)
// {
// return TimeSpanParse.TryParseExact(input, format, formatProvider, TimeSpanStyles.None, out
// result);
// }
//
// public static bool TryParseExact(final string input, final string[] formats, final
// IFormatProvider formatProvider, final out TimeSpan result)
// {
// return TimeSpanParse.TryParseExactMultiple(input, formats, formatProvider, TimeSpanStyles.None,
// out result);
// }
//
// public static bool TryParseExact(final string input, final string format, final IFormatProvider
// formatProvider, final TimeSpanStyles styles, final out TimeSpan result)
// {
// TimeSpanParse.ValidateStyles(styles, "styles");
// return TimeSpanParse.TryParseExact(input, format, formatProvider, styles, out result);
// }
//
// public static bool TryParseExact(final string input, final string[] formats, final
// IFormatProvider formatProvider, final TimeSpanStyles styles, final out TimeSpan result)
// {
// TimeSpanParse.ValidateStyles(styles, "styles");
// return TimeSpanParse.TryParseExactMultiple(input, formats, formatProvider, styles, out result);
// }
//
// @Override
// public String toString() {
//
// return TimeSpanFormat.Format(this, null, null);
// }
//
// public String toString(final String format) {
//
// return TimeSpanFormat.Format(this, format, null);
// }
//
// public String toString(final String format, final IFormatProvider formatProvider) {
//
// if
// (TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.TimeSpan.LegacyMode)
// return TimeSpanFormat.Format(this, null, null);
// return TimeSpanFormat.Format(this, format, formatProvider);
// }
//
// @Override
// public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?>
// rightOperand) {
//
// // TODO Auto-generated method stub
// return false;
// }
//
// /*
// * public static TimeSpan operator-( TimeSpan t) { if (t._ticks == TimeSpan.MinValue._ticks) {
// throw
// * new OverflowException(Environment.GetResourceString("Overflow_NegateTwosCompNum")); } return
// new
// * TimeSpan(-t._ticks); }
// *
// * public static TimeSpan operator-( TimeSpan t1, TimeSpan t2) { return t1.Subtract(t2); }
// *
// * public static TimeSpan operator+( TimeSpan t) { return t; }
// *
// * public static TimeSpan operator+( TimeSpan t1, TimeSpan t2) { return t1.Add(t2); }
// *
// * public static bool operator==( TimeSpan t1, TimeSpan t2) { return t1._ticks == t2._ticks; }
// *
// * public static bool operator!=( TimeSpan t1, TimeSpan t2) { return t1._ticks != t2._ticks; }
// *
// * public static bool operator<( TimeSpan t1, TimeSpan t2) { return t1._ticks < t2._ticks; }
// *
// * public static bool operator<=( TimeSpan t1, TimeSpan t2) { return t1._ticks <= t2._ticks; }
// *
// * public static bool operator>( TimeSpan t1, TimeSpan t2) { return t1._ticks > t2._ticks; }
// *
// * public static bool operator>=( TimeSpan t1, TimeSpan t2) { return t1._ticks >= t2._ticks; }
// */
// }}

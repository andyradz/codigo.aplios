package com.codigo.aplios.timeline.common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.EnumSet;

public class TimeSpec {

    public static final LocalDateTime MinPeriodDate = LocalDateTime.MIN;

    public static final LocalDateTime MaxPeriodDate = LocalDateTime.MAX;
    // public static final TimeSpan MinPeriodDuration = TimeSpan.Zero;
    // public static final TimeSpan MaxPeriodDuration = MaxPeriodDate - MinPeriodDate;

    public static enum FiscalCalendar {
        // YearMonth FiscalYearBaseMonth = YearMonth.July;
        WeeksPerShortMonth(4),
        WeeksPerLongMonth(5),
        WeeksPerLeapMonth(6),
        WeeksPerQuarter((2 * FiscalCalendar.WeeksPerShortMonth.constant) + FiscalCalendar.WeeksPerLongMonth.constant),
        WeeksPerLeapQuarter(FiscalCalendar.WeeksPerQuarter.constant + 1);
        // WeeksPerHalfyear(FiscalCalendar.WeeksPerQuarter.constant * TimeRelations.QuartersPerHalfyear.getSpecConst());
        // WeeksPerLeapHalfyear = FiscalWeeksPerHalfyear + 1,
        // WeeksPerYear = FiscalWeeksPerQuarter * QuartersPerYear,
        // WeeksPerLeapYear = FiscalWeeksPerYear + 1
        //
        // DaysPerShortMonth = FiscalWeeksPerShortMonth * DaysPerWeek,
        // DaysPerLongMonth = FiscalWeeksPerLongMonth * DaysPerWeek,
        // DaysPerLeapMonth = FiscalWeeksPerLeapMonth * DaysPerWeek,
        // DaysPerQuarter = ( 2 * FiscalDaysPerShortMonth ) + FiscalDaysPerLongMonth,
        // DaysPerLeapQuarter = FiscalDaysPerQuarter + DaysPerWeek,
        // DaysPerHalfyear = FiscalDaysPerQuarter * QuartersPerHalfyear,
        // DaysPerLeapHalfyear = FiscalDaysPerHalfyear + DaysPerWeek,
        // DaysPerYear = FiscalDaysPerQuarter * QuartersPerYear,
        // DaysPerLeapYear = FiscalDaysPerYear + DaysPerWeek;

        FiscalCalendar(final int constant) {

            this.constant = constant;
        }

        private final int constant;

    }

    public static enum TimeRelations {

        // relations
        MonthsPerYear(12),
        HalfYearsPerYear(2),
        QuartersPerYear(4),
        QuartersPerHalfyear(QuartersPerYear.constant / HalfYearsPerYear.constant),
        MaxWeeksPerYear(53),
        MonthsPerHalfyear(MonthsPerYear.constant / HalfYearsPerYear.constant),
        MonthsPerQuarter(MonthsPerYear.constant / QuartersPerYear.constant),
        MaxDaysPerMonth(31),
        DaysPerWeek(7),
        HoursPerDay(24),
        MinutesPerHour(60),
        SecondsPerMinute(60),
        MillisecondsPerSecond(1_000);

        TimeRelations(final int specConst) {

            this.constant = specConst;

            EnumSet.of(Month.JANUARY);
            EnumSet.of(Month.JULY);

            Period.from(Duration.ofNanos(1));
            Period.from(Duration.ofNanos(-1));
            ; // negative tick;
        }

        private final int constant;

    }
}

enum FiscalCalendarConstants {
    // public const YearMonth FiscalYearBaseMonth = YearMonth.July;
    FiscalWeeksPerShortMonth(4),
    FiscalWeeksPerLongMonth(5),
    FiscalWeeksPerLeapMonth(6),
    FiscalWeeksPerQuarter((2 * FiscalWeeksPerShortMonth.constant) + FiscalWeeksPerLongMonth.constant),
    FiscalWeeksPerLeapQuarter(FiscalWeeksPerQuarter.constant + 1),
    FiscalWeeksPerHalfyear(FiscalWeeksPerQuarter.constant), // QuartersPerHalfyear.constant),
    FiscalWeeksPerLeapHalfyear(FiscalWeeksPerHalfyear.constant + 1),
    FiscalWeeksPerYear(FiscalWeeksPerQuarter.constant), // QuartersPerYear.constant),
    FiscalWeeksPerLeapYear(FiscalWeeksPerYear.constant + 1);
    //
    // FiscalDaysPerShortMonth = FiscalWeeksPerShortMonth * DaysPerWeek;
    // FiscalDaysPerLongMonth = FiscalWeeksPerLongMonth * DaysPerWeek;
    // FiscalDaysPerLeapMonth = FiscalWeeksPerLeapMonth * DaysPerWeek;
    // FiscalDaysPerQuarter = ( 2 * FiscalDaysPerShortMonth ) + FiscalDaysPerLongMonth;
    // FiscalDaysPerLeapQuarter = FiscalDaysPerQuarter + DaysPerWeek;
    // FiscalDaysPerHalfyear = FiscalDaysPerQuarter * QuartersPerHalfyear;
    // FiscalDaysPerLeapHalfyear = FiscalDaysPerHalfyear + DaysPerWeek;
    // FiscalDaysPerYear = FiscalDaysPerQuarter * QuartersPerYear;
    // FiscalDaysPerLeapYear = FiscalDaysPerYear + DaysPerWeek;

    FiscalCalendarConstants(final int constant) {

        this.constant = constant;
    }

    private final int constant;

}

/*
 * // ------------------------------------------------------------------------ public static class TimeSpec {
 *
 * // ---------------------------------------------------------------------- // relations public const int
 * MonthsPerYear
 * = 12; public const int HalfyearsPerYear = 2; public const int QuartersPerYear = 4; public const int
 * QuartersPerHalfyear = QuartersPerYear / HalfyearsPerYear; public const int MaxWeeksPerYear = 53; public const int
 * MonthsPerHalfyear = MonthsPerYear / HalfyearsPerYear; public const int MonthsPerQuarter = MonthsPerYear /
 * QuartersPerYear; public const int MaxDaysPerMonth = 31; public const int DaysPerWeek = 7; public const int
 * HoursPerDay = 24; public const int MinutesPerHour = 60; public const int SecondsPerMinute = 60; public const int
 * MillisecondsPerSecond = 1000;
 *
 * public const YearMonth CalendarYearStartMonth = YearMonth.January; public const DayOfWeek FirstWorkingDayOfWeek =
 * DayOfWeek.Monday;
 *
 * // ---------------------------------------------------------------------- // fiscal calendar public const YearMonth
 * FiscalYearBaseMonth = YearMonth.July; public const int FiscalWeeksPerShortMonth = 4; public const int
 * FiscalWeeksPerLongMonth = 5; public const int FiscalWeeksPerLeapMonth = 6; public const int FiscalWeeksPerQuarter = (
 * 2 * FiscalWeeksPerShortMonth ) + FiscalWeeksPerLongMonth; public const int FiscalWeeksPerLeapQuarter =
 * FiscalWeeksPerQuarter + 1; public const int FiscalWeeksPerHalfyear = FiscalWeeksPerQuarter * QuartersPerHalfyear;
 * public const int FiscalWeeksPerLeapHalfyear = FiscalWeeksPerHalfyear + 1; public const int FiscalWeeksPerYear =
 * FiscalWeeksPerQuarter * QuartersPerYear; public const int FiscalWeeksPerLeapYear = FiscalWeeksPerYear + 1;
 *
 * public const int FiscalDaysPerShortMonth = FiscalWeeksPerShortMonth * DaysPerWeek; public const int
 * FiscalDaysPerLongMonth = FiscalWeeksPerLongMonth * DaysPerWeek; public const int FiscalDaysPerLeapMonth =
 * FiscalWeeksPerLeapMonth * DaysPerWeek; public const int FiscalDaysPerQuarter = ( 2 * FiscalDaysPerShortMonth ) +
 * FiscalDaysPerLongMonth; public const int FiscalDaysPerLeapQuarter = FiscalDaysPerQuarter + DaysPerWeek; public const
 * int FiscalDaysPerHalfyear = FiscalDaysPerQuarter * QuartersPerHalfyear; public const int FiscalDaysPerLeapHalfyear =
 * FiscalDaysPerHalfyear + DaysPerWeek; public const int FiscalDaysPerYear = FiscalDaysPerQuarter * QuartersPerYear;
 * public const int FiscalDaysPerLeapYear = FiscalDaysPerYear + DaysPerWeek;
 *
 * // ---------------------------------------------------------------------- // halfyear public static YearMonth[]
 * FirstHalfyearMonths = new[] { YearMonth.January, YearMonth.February, YearMonth.March, YearMonth.April, YearMonth.May,
 * YearMonth.June }; public static YearMonth[] SecondHalfyearMonths = new[] { YearMonth.July, YearMonth.August,
 * YearMonth.September, YearMonth.October, YearMonth.November, YearMonth.December };
 *
 * // ---------------------------------------------------------------------- // quarter public const int
 * FirstQuarterMonthIndex = 1; public const int SecondQuarterMonthIndex = FirstQuarterMonthIndex + MonthsPerQuarter;
 * public const int ThirdQuarterMonthIndex = SecondQuarterMonthIndex + MonthsPerQuarter; public const int
 * FourthQuarterMonthIndex = ThirdQuarterMonthIndex + MonthsPerQuarter;
 *
 * public static YearMonth[] FirstQuarterMonths = new[] { YearMonth.January, YearMonth.February, YearMonth.March };
 * public static YearMonth[] SecondQuarterMonths = new[] { YearMonth.April, YearMonth.May, YearMonth.June }; public
 * static YearMonth[] ThirdQuarterMonths = new[] { YearMonth.July, YearMonth.August, YearMonth.September }; public
 * static YearMonth[] FourthQuarterMonths = new[] { YearMonth.October, YearMonth.November, YearMonth.December };
 *
 * // ---------------------------------------------------------------------- // duration public static readonly
 * TimeSpan
 * NoDuration = TimeSpan.Zero; public static readonly TimeSpan MinPositiveDuration = new TimeSpan( 1 ); // positive
 * tick; public static readonly TimeSpan MinNegativeDuration = new TimeSpan( -1 ); // negative tick;
 *
 * // ---------------------------------------------------------------------- // period public static readonly DateTime
 * MinPeriodDate = DateTime.MinValue; public static readonly DateTime MaxPeriodDate = DateTime.MaxValue; public static
 * readonly TimeSpan MinPeriodDuration = TimeSpan.Zero; public static readonly TimeSpan MaxPeriodDuration =
 * MaxPeriodDate - MinPeriodDate;
 *
 * } // class TimeSpec
 *
 * } // namespace Itenso.TimePeriod
 */

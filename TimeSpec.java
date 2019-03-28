package com.codigo.aplios.group.sdk.core.datetime;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Klasa reprezentuje wartości stałych definiujące niezmienne właściwości daty i
 * czasu
 *
 * @author andrzej.radziszewski
 */
public class TimeSpec {

	/**
	 * Stała reprezentuje mininalną wartość daty i czasu
	 */
	public static final LocalDateTime MINPERIODDATE;

	/**
	 * Stała reprezentuje maksymalną wartość daty i czasu
	 */
	public static final LocalDateTime MAXPERIODDATE;

	/**
	 * Stała reprezentuje minimalną wartość rozpiętości czasu
	 */
	public static final TimeSpan MINPERIODDURATION;

	/**
	 * Stała reprezentuje maksymalną wartość rozpiętości czasu
	 */
	public static final TimeSpan MAXPERIODDURATION;

	/**
	 * Statyczny konstruktor obiektu klasy <code>TimeSpec</code>
	 */
	static {
		MINPERIODDATE = LocalDateTime.MIN;
		MAXPERIODDATE = LocalDateTime.MAX;
		MINPERIODDURATION = TimeSpan.ZERO;
		MAXPERIODDURATION = TimeSpan.subtract(TimeSpan.MAXVALUE, TimeSpan.MINVALUE);
	}

	public enum TimeRelations {

		/**
		 * Stała reprezentuje ilość miesięcy w roku
		 */
		MONTHSPERYEAR(12),

		/**
		 * Stała reprezentuje ilość półroczy w roku
		 */
		HALFYEARSPERYEAR(2),

		/**
		 * Stała reprezentuje ilość kwartałów w roku
		 */
		QUARTERSPERYEAR(4),

		/**
		 * Stała reprezentuje ilość kwartałów w półroczu
		 */
		QUARTERSPERHALFYEAR(QUARTERSPERYEAR.constant / HALFYEARSPERYEAR.constant),

		/**
		 * Stała reprezentuje ilość tygodni w roku
		 */
		MAXWEEKSPERYEAR(53),

		/**
		 * Stała reprezentuje ilość miesięcy w półroczu
		 */
		MONTHSPERHALFYEAR(MONTHSPERYEAR.constant / HALFYEARSPERYEAR.constant),

		/**
		 * Stała reprezentuje ilość miesięcy w kwartale
		 */
		MONTHSPERQUARTER(MONTHSPERYEAR.constant / QUARTERSPERYEAR.constant),

		/**
		 * Stała reprezentuje ilość dni w miesiącu
		 */
		MAXDAYSPERMONTH(31),

		/**
		 * Stała reprezentuje ilość dni w tygodniu
		 */
		DAYSPERWEEK(7),

		/**
		 * Stała reprezentuje ilość godzin na dzień
		 */
		HOURSPERDAY(24),

		/**
		 * Stała reprezentuje ilość godzin na dzień lub noc
		 */
		HOURSPERHALFDAY(HOURSPERDAY.constant / 2),

		/**
		 * Stała reprezentuje ilość minut w godzinie
		 */
		MINUTESPERHOUR(60),

		/**
		 * Stała reprezentuje ilość sekund w minucie
		 */
		SECONDSPERMINUTE(60),

		/**
		 * Stała reprezentuje ilość milisekund w sekundzie
		 */
		MILLISECONDSPERSECOND(1_000);

		/**
		 * Podstawowy konstruktor obiektu klasy <code>TimeRelations</code>
		 *
		 * @param value
		 * @category constructor
		 */
		TimeRelations(final int constant) {

			this.constant = constant;
		}

		/**
		 * Pole obiektu zawiera informacje o stałej dla danej flagi
		 */
		private final int constant;

		/**
		 * Własciwość z informacją wartości flagi
		 *
		 * @return Wartość stałej dla danei opcji flagi
		 */
		public int get() {

			return this.constant;
		}
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>TimeSpec</code>
	 *
	 * @category constructor
	 */
	private TimeSpec() {

	}

	public enum FiscalCalendar {

		FISCALWEEKSPERSHORTMONTH(4),
		FISCALWEEKSPERLONGMONTH(5),
		FISCALWEEKSPERLEAPMONTH(6),
		FISCALWEEKSPERQUARTER((2 * FISCALWEEKSPERSHORTMONTH.constant) + FISCALWEEKSPERLONGMONTH.constant),
		FISCALWEEKSPERLEAPQUARTER(FISCALWEEKSPERQUARTER.constant + 1),
		FISCALWEEKSPERHALFYEAR(FISCALWEEKSPERQUARTER.constant), // QuartersPerHalfyear.constant),
		FISCALWEEKSPERLEAPHALFYEAR(FISCALWEEKSPERHALFYEAR.constant + 1),
		FISCALWEEKSPERYEAR(FISCALWEEKSPERQUARTER.constant), // QuartersPerYear.constant),
		FISCALWEEKSPERLEAPYEAR(FISCALWEEKSPERYEAR.constant + 1),
		FISCALDAYSPERSHORTMONTH(FISCALWEEKSPERSHORTMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		FISCALDAYSPERLONGMONTH(FISCALWEEKSPERLONGMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		FISCALDAYSPERLEAPMONTH(FISCALWEEKSPERLEAPMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		FISCALDAYSPERQUARTER((2 * FISCALDAYSPERSHORTMONTH.constant) + FISCALDAYSPERLONGMONTH.constant),
		FISCALDAYSPERLEAPQUARTER(FISCALDAYSPERQUARTER.constant + TimeRelations.DAYSPERWEEK.constant),
		FISCALDAYSPERHALFYEAR(FISCALDAYSPERQUARTER.constant * TimeRelations.QUARTERSPERHALFYEAR.constant),
		FISCALDAYSPERLEAPHALFYEAR(FISCALDAYSPERHALFYEAR.constant + TimeRelations.DAYSPERWEEK.constant),
		FISCALDAYSPERYEAR(FISCALDAYSPERQUARTER.constant * TimeRelations.QUARTERSPERYEAR.constant),
		FISCALDAYSPERLEAPYEAR(FISCALDAYSPERYEAR.constant + TimeRelations.DAYSPERWEEK.constant),
		WEEKSPERSHORTMONTH(4),
		WEEKSPERLONGMONTH(5),
		WEEKSPERLEAPMONTH(6),
		WEEKSPERQUARTER((2 * WEEKSPERSHORTMONTH.constant) + WEEKSPERLONGMONTH.constant),
		WEEKSPERLEAPQUARTER(WEEKSPERQUARTER.constant + 1),
		WEEKSPERHALFYEAR(WEEKSPERQUARTER.constant * TimeRelations.QUARTERSPERHALFYEAR.constant),
		WEEKSPERLEAPHALFYEAR(FISCALWEEKSPERHALFYEAR.constant + 1),
		WEEKSPERYEAR(FISCALWEEKSPERQUARTER.constant * TimeRelations.QUARTERSPERYEAR.constant),
		WEEKSPERLEAPYEAR(FISCALWEEKSPERYEAR.constant + 1),
		DAYSPERSHORTMONTH(FISCALWEEKSPERSHORTMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		DAYSPERLONGMONTH(FISCALWEEKSPERLONGMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		DAYSPERLEAPMONTH(FISCALWEEKSPERLEAPMONTH.constant * TimeRelations.DAYSPERWEEK.constant),
		DAYSPERQUARTER((2 * FISCALDAYSPERSHORTMONTH.constant) + FISCALDAYSPERLONGMONTH.constant),
		DAYSPERLEAPQUARTER(FISCALDAYSPERQUARTER.constant + TimeRelations.DAYSPERWEEK.constant),
		DAYSPERHALFYEAR(FISCALDAYSPERQUARTER.constant * TimeRelations.QUARTERSPERHALFYEAR.constant),
		DAYSPERLEAPHALFYEAR(FISCALDAYSPERHALFYEAR.constant + TimeRelations.DAYSPERWEEK.constant),
		DAYSPERYEAR(FISCALDAYSPERQUARTER.constant * TimeRelations.QUARTERSPERYEAR.constant),
		DAYSPERLEAPYEAR(FISCALDAYSPERYEAR.constant + TimeRelations.DAYSPERWEEK.constant);

		public static final Month FISCALYEARBASEMONTH = Month.JULY;
		private final int constant;

		/**
		 * Podstawowy konstruktor obiektu klasy <code>FiscalCalendar</code>
		 *
		 * @param constant
		 */
		FiscalCalendar(final int constant) {

			this.constant = constant;
		}
	}
}

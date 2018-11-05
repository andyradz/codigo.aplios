package com.codigo.aplios.sdk.core.period;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Locale.Category;

public class TimeSpan {

	// custom clock -
	// http://www.concretepage.com/java/jdk-8/java-8-clock-example-using-dependency-injection-framework
	public static void main(final String[] args) {

		Locale.setDefault(Category.DISPLAY, Locale.US);
		Locale.setDefault(Category.FORMAT, Locale.US);

		LocalDate.now()
				.query(new NextMartinLutherKingDayQuery());

		// LocalDate datefirst = LocalDate.now();
		// LocalDate datelast = LocalDate.of(2016, Month.MAY, 3);
		//
		// long period = ChronoUnit.DAYS.between(datefirst, datelast);
		// period = ChronoUnit.MONTHS.between(datefirst, datelast);
		// period = ChronoUnit.NANOS.between(datelast, datefirst);

		final LocalDateTime ldt1 = LocalDateTime.of(2010, 6, 12, 18, 32, 0);
		final LocalDateTime ldt2 = LocalDateTime.of(2012, 6, 13, 22, 47, 0);
		ChronoUnit.YEARS.between(ldt1, ldt2);
		ChronoUnit.MONTHS.between(ldt1, ldt2);
		ChronoUnit.WEEKS.between(ldt1, ldt2);
		ChronoUnit.DAYS.between(ldt1, ldt2);
		ChronoUnit.HOURS.between(ldt1, ldt2);
		ChronoUnit.MINUTES.between(ldt1, ldt2);
		ChronoUnit.SECONDS.between(ldt1, ldt2);
		LocalDate.now()
				.get(IsoFields.QUARTER_OF_YEAR);
		ChronoField.DAY_OF_MONTH.getFrom(LocalDate.now());
		ChronoField.DAY_OF_WEEK.getFrom(LocalDate.now());
		ChronoField.DAY_OF_YEAR.getFrom(LocalDate.now());
		Year.of(2016)
				.length();
		Month.of(2)
				.length(Year.of(2016)
						.isLeap());

		ChronoField.DAY_OF_MONTH.getFrom(LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));

		final ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
		final ZoneId berlin = ZoneId.of("Europe/Berlin");
		// 2014-02-20 12:00
		final LocalDateTime dateTime = LocalDateTime.now();
		// 2014-02-20 12:00, Europe/Berlin (+01:00)
		final ZonedDateTime berlinDateTime = ZonedDateTime.of(dateTime, berlin);
		// 2014-02-20 03:00, America/Los_Angeles (-08:00)
		final ZonedDateTime losAngelesDateTime = berlinDateTime.withZoneSameInstant(losAngeles);
		losAngelesDateTime.getOffset()
				.getTotalSeconds();
		ZoneId.getAvailableZoneIds();
		// using offsets
		final LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 20, 3, 30);
		final ZoneOffset offset = ZoneOffset.of("+05:00");
		// 2013-07-20 03:30 +05:00
		final OffsetDateTime plusFive = OffsetDateTime.of(date, offset);
		plusFive.withOffsetSameInstant(ZoneOffset.ofHours(-2));

		// current time
		final Instant now = Instant.now();
		Instant.ofEpochSecond(1262347200);
		Instant.ofEpochMilli(1262347200000l);
		Instant.parse("2010-01-01T12:00:00Z");
		now.toString();
		now.getEpochSecond();
		now.toEpochMilli();
		now.plusSeconds(10);
		final Instant nowPlusOneWeek = now.plus(7, ChronoUnit.DAYS);
		final Instant nowPlusTwoWeeks = now.plus(14, ChronoUnit.DAYS);

		System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(ldt2));
		System.out.println(DateTimeFormatter.ISO_INSTANT.format(nowPlusTwoWeeks));
		System.out.println(DateTimeFormatter.ofPattern("yyyy.mm.dd hh:mm:ss")
				.format(ldt2));

		GregorianCalendar.from(ZonedDateTime.now());
		System.out.println(Instant.MIN);
		System.out.println(Instant.MAX);
		System.out.println(nowPlusOneWeek);
		System.out.println(nowPlusTwoWeeks);
		System.out.println((ChronoUnit.DAYS.between(now, nowPlusTwoWeeks)) / 7);
	}
}

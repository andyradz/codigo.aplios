package com.codigo.aplios.domain.model;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.ArrayLen;

import com.codigo.aplios.domain.model.calendar.Calendar;
import com.codigo.aplios.domain.model.calendar.CalendarDay;
import com.codigo.aplios.domain.model.calendar.CalendarPrimaryKey;
import com.codigo.aplios.domain.model.common.Person;
import com.codigo.aplios.domain.model.common.PersonAttributeImpl;
import com.codigo.aplios.domain.model.common.TaxOffice;
import com.codigo.aplios.domain.model.contacts.Address;

/**
 * Klasa testowa - do refaktoryzacji i usunięcia
 *
 * @author dp0470
 *
 */
public class App {

	// private static final Logger log = Logger.getLogger(App.class);

	private static @Positive @ArrayLen(12) int[] data = { -1, -2 };

	public static void testValue(@NonNull final String[] args) {

	}

	public static void main(@Nullable final String[] args) {

		App.testValue(args);

		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopdb");
		final EntityManager em = emf.createEntityManager();

		final EntityTransaction et = em.getTransaction();
		et.begin();

		em.clear();
		for (double loopIdx = 1.; loopIdx <= 1.; loopIdx++) {

			final Person person = new Person();
			person.setId((long) loopIdx);
			person.setName("Andrzej");
			person.setMiddleName("Marek");
			person.setSureName("Radziszewski");
			person.setBirthDate(Date.from(Instant.now()
					.plusMillis(((long) (10 * loopIdx)))));
			person.setBirthTIme(Date.from(Instant.now()));

			final Address address = new Address();
			address.setCity("Brzoza Bydgoska");
			address.setEmailAddress("and.radz@wp.pl");
			person.getAddresses()
					.add(address);

			PersonAttributeImpl attr = new PersonAttributeImpl();
			attr.setId(1L);
			attr.setName("charset");
			attr.setValue("utf-8");
			attr.setPerson(person);
			person.getPersonAttributes()
					.put("1", attr);

			attr = new PersonAttributeImpl();
			attr.setId(2L);
			attr.setName("validation");
			attr.setValue("yes");
			attr.setPerson(person);
			person.getPersonAttributes()
					.put("2", attr);

			attr = new PersonAttributeImpl();
			attr.setId(3L);
			attr.setName("presentation");
			attr.setValue("false");
			attr.setPerson(person);
			person.getPersonAttributes()
					.put("3", attr);

			em.persist(person);

		}

		TaxOffice taxOffice = new TaxOffice();
		taxOffice.setId(1L);
		taxOffice.setOfficeType("IAS");
		taxOffice.setOfficeName("Izba Administracji Skarbowej we Wrocławiu");
		taxOffice.setOfficeCode("0201");

		Address taxAddress = new Address();
		taxOffice.getAddresses()
				.add(taxAddress);
		em.persist(taxOffice);

		taxOffice = new TaxOffice();
		taxOffice.setId(2L);
		taxOffice.setOfficeType("US");
		taxOffice.setOfficeName("Urząd Skarbowy w Bolesławcu");
		taxOffice.setOfficeCode("0202");

		taxAddress = new Address();
		taxOffice.getAddresses()
				.add(taxAddress);
		em.persist(taxOffice);

		CalendarPrimaryKey key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(1);

		Calendar cal = new Calendar(
			key);
		cal.setName("Kalendarz fiskalny");

		CalendarDay calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.FRIDAY);
		calDay.setDayNumberInWeekend(122);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(2);

		cal = new Calendar(
			key);
		cal.setName("Kalendarz szkolny");
		calDay = new CalendarDay(
			key);
		calDay.setFirstDayInMonth(true);
		calDay.setLastDayInMonth(false);
		calDay.setDayName(DayOfWeek.MONDAY);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(3);

		cal = new Calendar(
			key);
		cal.setName("Kalendarz świąt");
		calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.WEDNESDAY);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		et.commit();

		final CalendarPrimaryKey key1 = new CalendarPrimaryKey();
		key1.setYearNumber(2018);
		key1.setMonthNumber(1);
		key1.setDayNumber(3);
		// final commons.Calendar c1 = em.find(commons.Calendar.class, key1);

		// System.out.println(c1);

		em.close();
		emf.close();

	}

}

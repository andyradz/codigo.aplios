package data.mapping;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.ArrayLen;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import data.mapping.calendar.Calendar;
import data.mapping.calendar.CalendarDay;
import data.mapping.calendar.CalendarPrimaryKey;
import data.mapping.customer.Manufacturer;
import data.mapping.customer.TaxOffice;
import data.mapping.location.Address;
import data.mapping.location.AddressType;
import data.mapping.user.Caption;
import data.mapping.user.Person;
import data.mapping.user.PersonAttributeImpl;

/**
 * Klasa testowa - do refaktoryzacji i usunięcia
 *
 * @author dp0470
 *
 */
public class App {

	private static final Logger log = Logger.getLogger(App.class);

	private static @Positive @ArrayLen(12) int[] data = { -1, -2 };

	public static void testValue(@NonNull final String[] args) {

	}

	public static void main(@Nullable final String[] args) throws JAXBException {

		App.testValue(args);

		Map<String, Object> properties = new HashMap<>();

		// Enable DDL Generation
		properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
		properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE,
				PersistenceUnitProperties.DDL_DATABASE_GENERATION);

		// Configure Session Customizer which will pipe sql file to db before DDL Generation runs
		properties.put(PersistenceUnitProperties.SESSION_CUSTOMIZER, "data.mapping.ImportSQL");
		properties.put("import.sql.file", "/META-INF/create.sql");

		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsqldb-eclipselink", properties);
		final EntityManager em = emf.createEntityManager();

		final EntityTransaction et = em.getTransaction();
		et.begin();

		em.clear();
		double id = 1.;

		for (double loopIdx = 1.; loopIdx <= 1.; loopIdx++) {

			final Person person = new Person();
			person.setId((long) loopIdx);
			person.setName("Andrzej");
			person.setMiddleName("Marek");
			person.setSureName("Radziszewski");
			person.setBirthDate(Date.from(Instant.now()
					.plusMillis(((long) (10 * loopIdx)))));
			person.setBirthTIme(Date.from(Instant.now()));

			Address address = new Address();
			address.setId((long) id++);
			address.setAddressType(AddressType.ADMINISTRATIVE);
			address.setCountry("Polska");
			address.setCity("Brzoza Bydgoska");
			address.setStreet("Pomorska");
			address.setBuildNumber("1");
			address.setFlatNumber("12");
			address.setZipCode("86-060");
			address.setPostName("Poznań");
			address.setDistrict("Nowa Wieś Wielka");
			address.setPostBox("XXXXX");
			address.setProvince("Bydgoski");
			address.setCounty("Bydgoski");
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

			address = new Address();
			address.setId((long) id++);
			address.setAddressType(AddressType.REGISTRATION);
			address.setCountry("Polska");
			address.setCity("Warszawa");
			address.setStreet("Ceramiczna");
			address.setBuildNumber("29");
			address.setFlatNumber("29");
			address.setZipCode("03-126");
			address.setPostName("Warsszwa");
			address.setDistrict("Białołęka");
			address.setPostBox("POST1");
			address.setProvince("Warszawski");
			address.setCounty("Tarchomin");
			person.getAddresses()
					.add(address);

			address = new Address();
			address.setId((long) id++);
			address.setAddressType(AddressType.OTHER);
			address.setCountry("Polska");
			address.setCity("Warszawa");
			address.setStreet("Ceramiczna");
			address.setBuildNumber("29");
			address.setFlatNumber("29");
			address.setZipCode("03-126");
			address.setPostName("Warsszwa");
			address.setDistrict("Białołęka");
			address.setPostBox("POST1");
			address.setProvince("Warszawski");
			address.setCounty("Praga");
			person.getAddresses()
					.add(address);

			em.persist(person);

			JAXBContext jaxbContext = JAXBContext.newInstance(Address.class);

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// marshaller.setProperty(MarshallerProperties., "xml");
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(address, System.out);
		}

		TaxOffice taxOffice = new TaxOffice();
		taxOffice.setId(1L);
		taxOffice.setOfficeType("IAS");
		taxOffice.setOfficeName("Izba Administracji Skarbowej we Wrocławiu");
		taxOffice.setOfficeCode("0201");

		Address taxAddress = new Address();
		taxAddress.setId(100000L);
		taxAddress.setAddressType(AddressType.ADMINISTRATIVE);
		taxAddress.setCountry("Polska");
		taxAddress.setCity("Wrocław");
		taxAddress.setStreet("Powstańców Sląskich");
		taxAddress.setBuildNumber("24");
		taxAddress.setFlatNumber("26");
		taxAddress.setZipCode("53-333");
		taxAddress.setProvince("dolnośląskie");
		taxOffice.getAddresses()
				.add(taxAddress);
		em.persist(taxOffice);

		taxOffice = new TaxOffice();
		taxOffice.setId(2L);
		taxOffice.setOfficeType("US");
		taxOffice.setOfficeName("Urząd Skarbowy w Bolesławcu");
		taxOffice.setOfficeCode("0202");

		taxAddress = new Address();
		taxAddress.setId(200000L);
		taxAddress.setAddressType(AddressType.ADMINISTRATIVE);
		taxAddress.setCountry("Polska");
		taxAddress.setCity("Bolesławiec");
		taxAddress.setStreet("Gamcarska");
		taxAddress.setBuildNumber("10");
		taxAddress.setZipCode("59-700");
		taxAddress.setProvince("dolnośląskie");
		taxOffice.getAddresses()
				.add(taxAddress);
		em.persist(taxOffice);

		CalendarPrimaryKey key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(1);

		Calendar cal = new Calendar(key);
		cal.setName("Kalendarz fiskalny");

		CalendarDay calDay = new CalendarDay(key);
		calDay.setDayName(DayOfWeek.FRIDAY);
		calDay.setDayNumberInWeekend(122);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(2);
		calDay = new CalendarDay(key);
		calDay.setDayName(DayOfWeek.THURSDAY);
		calDay.setDayNumberInWeekend(12);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(3);
		calDay = new CalendarDay(key);
		calDay.setDayName(DayOfWeek.SUNDAY);
		calDay.setDayNumberInWeekend(44);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(4);
		calDay = new CalendarDay(key);
		calDay.setDayName(DayOfWeek.MONDAY);
		calDay.setDayNumberInWeekend(55);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(5);
		calDay = new CalendarDay(key);
		calDay.setDayName(DayOfWeek.WEDNESDAY);
		calDay.setDayNumberInWeekend(155);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		Manufacturer customer = new Manufacturer();
		customer.setId(1L);
		customer.setFirstName(Caption.of("Codigo"));
		customer.setLastName(Caption.of("Digital"));
		customer.setFullName(Caption.of("Codigo Digital Technology Sp z o.o."));
		customer.setOfficialSalutation(Caption.of("Mr"));
		customer.setOfficialTitle(Caption.of("Podmiot"));
		em.persist(customer);

		customer = new Manufacturer();
		customer.setId(2L);
		customer.setFirstName(Caption.of("Codigo"));
		customer.setLastName(Caption.of("Digital"));
		customer.setFullName(Caption.of("Codigo Digital Technology Sp z o.o."));
		customer.setOfficialSalutation(Caption.of("Mr"));
		customer.setOfficialTitle(Caption.of("Podmiot"));
		em.persist(customer);

		et.commit();
		em.close();
		emf.close();

	}

}

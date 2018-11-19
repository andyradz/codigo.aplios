package com.codigo.aplios.domain.model;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.ArrayLen;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.codigo.aplios.domain.model.calendar.Calendar;
import com.codigo.aplios.domain.model.calendar.CalendarDay;
import com.codigo.aplios.domain.model.calendar.CalendarPrimaryKey;
import com.codigo.aplios.domain.model.common.Person;
import com.codigo.aplios.domain.model.common.PersonAttributeImpl;
import com.codigo.aplios.domain.model.common.TaxOffice;
import com.codigo.aplios.domain.model.contacts.Address;
import com.codigo.aplios.domain.model.contacts.AddressType;
import com.codigo.aplios.domain.model.customers.Customer;
import com.codigo.aplios.domain.model.locale.ZipCodeShortcut;

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

		final Map<String, Object> properties = Maps.mutable.empty();

		// Enable DDL Generation
		properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
		properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE,
				PersistenceUnitProperties.DDL_DATABASE_GENERATION);

		properties.put(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,
				PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML_DEFAULT);

		// Configure Session Customizer which will pipe sql file to db before DDL Generation runs
		properties.put(PersistenceUnitProperties.SESSION_CUSTOMIZER, "data.mapping.ImportSQL");
		properties.put("import.sql.file", "/META-INF/create.sql");

		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bitshop", properties);
		final EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();

		// em.clear();
		for (double loopIdx = 1.; loopIdx <= 1.; loopIdx++) {

			final Person person = new Person();
			person.setId(1L);
			person.setName("Andrzej");
			person.setMiddleName("Marek");
			person.setSureName("Radziszewski");
			person.setBirthDate(java.util.Date.from(Instant.now()
					.plusMillis(((long) (10 * loopIdx)))));
			person.setBirthTIme(java.util.Date.from(Instant.now()));

			Address address = new Address();
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

			final Person person1 = new Person();
			person1.setId(2L);
			person1.setName("Izabela");
			person1.setMiddleName("Anna");
			person1.setSureName("Radziszewska");
			person1.setBirthDate(java.util.Date.from(Instant.now()
					.plusMillis(((long) (100 * loopIdx)))));
			person1.setBirthTIme(java.util.Date.from(Instant.now()));
			em.persist(person1);

			final Person person2 = new Person();
			person2.setId(3L);
			person2.setName("Aleksandra");
			person2.setMiddleName("Helena");
			person2.setSureName("Radziszewska");
			person2.setBirthDate(java.util.Date.from(Instant.now()
					.plusMillis(((long) (1002 * loopIdx)))));
			person2.setBirthTIme(java.util.Date.from(Instant.now()));
			em.persist(person2);

			final JAXBContext jaxbContext = JAXBContext.newInstance(Address.class);

			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(address, System.out);
		}

		TaxOffice taxOffice = new TaxOffice();
		taxOffice.setId(1L);
		taxOffice.setOfficeType("IAS");
		taxOffice.setOfficeName("Izba Administracji Skarbowej we Wrocławiu");
		taxOffice.setOfficeCode("0201");

		Address taxAddress = new Address();
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

		final Calendar cal = new Calendar(
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
		calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.THURSDAY);
		calDay.setDayNumberInWeekend(12);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(3);
		calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.SUNDAY);
		calDay.setDayNumberInWeekend(44);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(4);
		calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.MONDAY);
		calDay.setDayNumberInWeekend(55);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		key = new CalendarPrimaryKey();
		key.setYearNumber(2018);
		key.setMonthNumber(1);
		key.setDayNumber(5);
		calDay = new CalendarDay(
			key);
		calDay.setDayName(DayOfWeek.WEDNESDAY);
		calDay.setDayNumberInWeekend(155);
		cal.setCalendarDay(calDay);
		em.persist(cal);

		// Manufacturer customer = new Manufacturer();
		// customer.setFirstName("Codigo");
		// customer.setLastName(Caption.of("Digital"));
		// customer.setFullName(Caption.of("Codigo Digital Technology Sp z o.o."));
		// customer.setOfficialSalutation(Caption.of("Mr"));
		// customer.setOfficialTitle(Caption.of("Podmiot"));
		// em.persist(customer);
		//
		// customer = new Manufacturer();
		// customer.setFirstName(Caption.of("Codigo"));
		// customer.setLastName(Caption.of("Digital"));
		// customer.setFullName(Caption.of("Codigo Digital Technology Sp z o.o."));
		// customer.setOfficialSalutation(Caption.of("Mr"));
		// customer.setOfficialTitle(Caption.of("Podmiot"));
		// em.persist(customer);

		et.commit();

		// https://wiki.eclipse.org/EclipseLink/Release/2.5/JPA21#Update_Example

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<String> query = builder.createQuery(String.class);
		final Root<Customer> r = query.from(Customer.class);
		query.select(r.get("firstName"))
				.where(builder.equal(r.get("id"), 1L));

		final TypedQuery<String> tq = em.createQuery(query);

		App.log.info(tq);

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Tuple> query1 = cb.createTupleQuery();
		final Root<Person> hh = query1.from(Person.class);
		query1.select(cb.tuple(hh.get("id")
				.alias("id"),

				hh.get("name")
						.alias("name"),

				hh.get("middleName")
						.alias("middleName"),

				hh.get("sureName")
						.alias("sureName")))

				.where(cb.equal(hh.get("name"), "Andrzej"));

		final TypedQuery<Tuple> tq1 = em.createQuery(query1);

		final String format = "[%d]:[%s]:[%s]:[%s]";
		tq1.getResultList()
				.forEach(t -> App.log.info(String.format(format, t.get(0), t.get(1), t.get(2), t.get(3))));

		et = em.getTransaction();
		et.begin();

		ZipCodeShortcut zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Aleja");
		zipShortcut.setDescription("Aleja");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Aleje");
		zipShortcut.setDescription("Aleje");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Alejka");
		zipShortcut.setDescription("Alejka");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("AP");
		zipShortcut.setName("Agencja Pocztowa");
		zipShortcut.setDescription("Agencja Pocztowa");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Bast.");
		zipShortcut.setName("Bastion");
		zipShortcut.setDescription("Bastion");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Bulw.");
		zipShortcut.setName("Bulwar");
		zipShortcut.setDescription("Bulwar");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Bulw.");
		zipShortcut.setName("Bulwary");
		zipShortcut.setDescription("Bulwary");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Dep.");
		zipShortcut.setName("Deptak");
		zipShortcut.setDescription("Deptak");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("DK");
		zipShortcut.setName("Do końca");
		zipShortcut.setDescription("Do końca");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Dol.");
		zipShortcut.setName("Dolina");
		zipShortcut.setDescription("Dolina");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("FUP");
		zipShortcut.setName("Filia Urzędu Pocztowego");
		zipShortcut.setDescription("Filia Urzędu Pocztowego");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Gm.");
		zipShortcut.setName("Gmina");
		zipShortcut.setDescription("Gmina");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Jez.");
		zipShortcut.setName("Jezioro");
		zipShortcut.setDescription("Jezioro");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Kol.");
		zipShortcut.setName("Kolonia");
		zipShortcut.setDescription("Kolonia");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Kol.");
		zipShortcut.setName("Kolonie");
		zipShortcut.setDescription("Kolonie");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("n");
		zipShortcut.setName("Numery nieparzyste");
		zipShortcut.setDescription("Numery nieparzyste");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Os.");
		zipShortcut.setName("Osiedle");
		zipShortcut.setDescription("Osiedle");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Oś.");
		zipShortcut.setName("Ośrodek");
		zipShortcut.setDescription("Ośrodek");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("p");
		zipShortcut.setName("Liczby parzyste");
		zipShortcut.setDescription("Liczby parzyste");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Pl.");
		zipShortcut.setName("Plac");
		zipShortcut.setDescription("Plac");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("PH");
		zipShortcut.setName("Przedstawicielstwo Handlowe");
		zipShortcut.setDescription("Przedstawicielstwo Handlowe");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Pol.");
		zipShortcut.setName("Polana");
		zipShortcut.setDescription("Polana");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Prom.");
		zipShortcut.setName("Promenada");
		zipShortcut.setDescription("Promenada");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Przeł.");
		zipShortcut.setName("Przełęcz");
		zipShortcut.setDescription("Przełęcz");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("RH");
		zipShortcut.setName("Region Handlowy");
		zipShortcut.setDescription("Region Handlowy");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Schr.");
		zipShortcut.setName("Schronisko");
		zipShortcut.setDescription("Schronisko");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Skw.");
		zipShortcut.setName("Skwer");
		zipShortcut.setDescription("Skwer");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Skw.");
		zipShortcut.setName("Skwerek");
		zipShortcut.setDescription("Skwerek");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Targ.");
		zipShortcut.setName("Targowisko");
		zipShortcut.setDescription("Targowisko");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Ul.");
		zipShortcut.setName("Ulica");
		zipShortcut.setDescription("Ulica");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("UP");
		zipShortcut.setName("Urząd Pocztowy");
		zipShortcut.setDescription("Urząd Pocztowy");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Wybrz.");
		zipShortcut.setName("Wybrzeże");
		zipShortcut.setDescription("Wybrzeże");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Wzg.");
		zipShortcut.setName("Wzgórze");
		zipShortcut.setDescription("Wzgórze");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Wzg.");
		zipShortcut.setName("Wzgórza");
		zipShortcut.setDescription("Wzgórza");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		zipShortcut.setCode("Źr.");
		zipShortcut.setName("Źródło");
		zipShortcut.setDescription("Źródło");
		em.persist(zipShortcut);

		et.commit();
		em.close();
		emf.close();
	}
}
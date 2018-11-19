package com.codigo.aplios.domain.model;

import java.time.Instant;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.ArrayLen;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.codigo.aplios.domain.model.common.Person;
import com.codigo.aplios.domain.model.locale.ZipCodeShortcut;

/**
 * Klasa testowa - do refaktoryzacji i usunięcia
 *
 * @author dp0470
 *
 */
public class App1 {

	// private static final Logger log = Logger.getLogger(App1.class);
	private static Logger log = Logger.getLogger(App1.class.getName());

	private static @Positive @ArrayLen(12) int[] data = { -1, -2 };

	public static void testValue(@NonNull final String[] args) {

	}

	public static void main(@Nullable final String[] args) throws JAXBException {

		App1.testValue(args);

		final Map<String, Object> properties = Maps.mutable.empty();

		// Enable DDL Generation
		properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE,
				PersistenceUnitProperties.DDL_DATABASE_GENERATION);
		properties.put(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,
				PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML_DEFAULT);

		// Configure Session Customizer which will pipe sql file to db before DDL Generation runs
		// properties.put(PersistenceUnitProperties.SESSION_CUSTOMIZER, "data.mapping.ImportSQL");
		// properties.put("import.sql.file", "/META-INF/create.sql");

		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bitshop", properties);
		final EntityManager em = emf.createEntityManager();

		final EntityTransaction et = em.getTransaction();
		et.begin();

		Person person = new Person();
		// person.setId(1L);
		person.setName("Andrzej");
		person.setMiddleName("Marek");
		person.setSureName("Radziszewski");
		person.setBirthDate(java.util.Date.from(Instant.now()));
		person.setBirthTIme(java.util.Date.from(Instant.now()));
		em.persist(person);

		person = new Person();
		// person.setId(2L);
		person.setName("Andrzej");
		person.setMiddleName("Marek");
		person.setSureName("Radziszewski");
		person.setBirthDate(java.util.Date.from(Instant.now()));
		person.setBirthTIme(java.util.Date.from(Instant.now()));
		em.persist(person);

		ZipCodeShortcut zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(1L);
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Aleja");
		zipShortcut.setDescription("Aleja");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(2L);
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Aleje");
		zipShortcut.setDescription("Aleje");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(3L);
		zipShortcut.setCode("Al.");
		zipShortcut.setName("Alejka");
		zipShortcut.setDescription("Alejka");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(4L);
		zipShortcut.setCode("AP");
		zipShortcut.setName("Agencja Pocztowa");
		zipShortcut.setDescription("Agencja Pocztowa");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(5L);
		zipShortcut.setCode("Bast.");
		zipShortcut.setName("Bastion");
		zipShortcut.setDescription("Bastion");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(6L);
		zipShortcut.setCode("Bulw.");
		zipShortcut.setName("Bulwar");
		zipShortcut.setDescription("Bulwar");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(7L);
		zipShortcut.setCode("Bulw.");
		zipShortcut.setName("Bulwary");
		zipShortcut.setDescription("Bulwary");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(8L);
		zipShortcut.setCode("Dep.");
		zipShortcut.setName("Deptak");
		zipShortcut.setDescription("Deptak");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(9L);
		zipShortcut.setCode("DK");
		zipShortcut.setName("Do końca");
		zipShortcut.setDescription("Do końca");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(10L);
		zipShortcut.setCode("Dol.");
		zipShortcut.setName("Dolina");
		zipShortcut.setDescription("Dolina");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(11L);
		zipShortcut.setCode("FUP");
		zipShortcut.setName("Filia Urzędu Pocztowego");
		zipShortcut.setDescription("Filia Urzędu Pocztowego");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(12L);
		zipShortcut.setCode("Gm.");
		zipShortcut.setName("Gmina");
		zipShortcut.setDescription("Gmina");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(13L);
		zipShortcut.setCode("Jez.");
		zipShortcut.setName("Jezioro");
		zipShortcut.setDescription("Jezioro");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(14L);
		zipShortcut.setCode("Kol.");
		zipShortcut.setName("Kolonia");
		zipShortcut.setDescription("Kolonia");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(15L);
		zipShortcut.setCode("Kol.");
		zipShortcut.setName("Kolonie");
		zipShortcut.setDescription("Kolonie");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(16L);
		zipShortcut.setCode("n");
		zipShortcut.setName("Numery nieparzyste");
		zipShortcut.setDescription("Numery nieparzyste");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(17L);
		zipShortcut.setCode("Os.");
		zipShortcut.setName("Osiedle");
		zipShortcut.setDescription("Osiedle");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(18L);
		zipShortcut.setCode("Oś.");
		zipShortcut.setName("Ośrodek");
		zipShortcut.setDescription("Ośrodek");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(19L);
		zipShortcut.setCode("p");
		zipShortcut.setName("Liczby parzyste");
		zipShortcut.setDescription("Liczby parzyste");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(20L);
		zipShortcut.setCode("Pl.");
		zipShortcut.setName("Plac");
		zipShortcut.setDescription("Plac");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(21L);
		zipShortcut.setCode("PH");
		zipShortcut.setName("Przedstawicielstwo Handlowe");
		zipShortcut.setDescription("Przedstawicielstwo Handlowe");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(22L);
		zipShortcut.setCode("Pol.");
		zipShortcut.setName("Polana");
		zipShortcut.setDescription("Polana");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(23L);
		zipShortcut.setCode("Prom.");
		zipShortcut.setName("Promenada");
		zipShortcut.setDescription("Promenada");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(24L);
		zipShortcut.setCode("Przeł.");
		zipShortcut.setName("Przełęcz");
		zipShortcut.setDescription("Przełęcz");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(25L);
		zipShortcut.setCode("RH");
		zipShortcut.setName("Region Handlowy");
		zipShortcut.setDescription("Region Handlowy");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(26L);
		zipShortcut.setCode("Schr.");
		zipShortcut.setName("Schronisko");
		zipShortcut.setDescription("Schronisko");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(27L);
		zipShortcut.setCode("Skw.");
		zipShortcut.setName("Skwer");
		zipShortcut.setDescription("Skwer");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(28L);
		zipShortcut.setCode("Skw.");
		zipShortcut.setName("Skwerek");
		zipShortcut.setDescription("Skwerek");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(29L);
		zipShortcut.setCode("Targ.");
		zipShortcut.setName("Targowisko");
		zipShortcut.setDescription("Targowisko");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(30L);
		zipShortcut.setCode("Ul.");
		zipShortcut.setName("Ulica");
		zipShortcut.setDescription("Ulica");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(31L);
		zipShortcut.setCode("UP");
		zipShortcut.setName("Urząd Pocztowy");
		zipShortcut.setDescription("Urząd Pocztowy");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(32L);
		zipShortcut.setCode("Wybrz.");
		zipShortcut.setName("Wybrzeże");
		zipShortcut.setDescription("Wybrzeże");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(33L);
		zipShortcut.setCode("Wzg.");
		zipShortcut.setName("Wzgórze");
		zipShortcut.setDescription("Wzgórze");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(34L);
		zipShortcut.setCode("Wzg.");
		zipShortcut.setName("Wzgórza");
		zipShortcut.setDescription("Wzgórza");
		em.persist(zipShortcut);

		zipShortcut = new ZipCodeShortcut();
		// zipShortcut.setId(35L);
		zipShortcut.setCode("Źr.");
		zipShortcut.setName("Źródło");
		zipShortcut.setDescription("Źródło");
		em.persist(zipShortcut);

		et.commit();
		em.close();
		emf.close();
		App1.log.debug("zipShortcut");
	}
}

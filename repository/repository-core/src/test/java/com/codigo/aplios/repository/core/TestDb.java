package com.codigo.aplios.repository.core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.codigo.aplios.domain.model.catalog.Product;

/*import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.domain.XmlCountries;
import com.codigo.aplios.domain.model.domain.XmlCountry;
import com.codigo.aplios.domain.model.locale.Country;*/

public class TestDb {

	public static void main(final String[] args) {
		// throws InterruptedException, ExecutionException, IOException, JAXBException {

		// Class<Product> cls = Product.class;
		// Metamodel model = em.getMetamodel();
		// EntityType<Product> entity = model.entity(cls);
		// CriteriaQuery<Product> c = cb.createQuery(cls);
		// Root<Product> account = c.from(entity);
		// Path<Integer> balance = account.<Integer>get("balance");
		// c.where(cb.and /
		// (c.greaterThan(balance, 100),
		// c.lessThan(balance), 200)
		// ));

		new GenericRepository<>(
			Product.class, "shopdb");
		new Product();

		// System.out.println(postCodes.getName());
		// byte[] picturePl = Files.readAllBytes(Paths.get("D:/databases/flagi/pl.png"));
		//
		// Country country = new Country();
		// country.setName("Afganistan");
		// country.setTwoLetterIsoCode("AF");
		// country.setThreLetterIsoCode("AFG");
		// country.setNumericIsoCode("004");
		// country.setDescription("Islamska Republika Afganistanu");
		// EntityLifeState dd = new EntityLifeState();
		// dd.setRecordInfo(EntityRowInfo.PENDING);
		// country.setEntityLifeState(dd);
		// countries.save(country);
		// System.out.println(country.getIsoSequence());
		//
		// country = new Country();
		// country.setName("Albania");
		// country.setTwoLetterIsoCode("AL");
		// country.setThreLetterIsoCode("ALB");
		// country.setNumericIsoCode("008");
		// country.setDescription("Republika Albanii");
		// country.setEntityLifeState(dd);
		// countries.save(country);
		// CreditCard cd = new CreditCard();
		// cd.setCcNumber("Andrzej");
		// countries.save(country);
		//
		// curriences.add(currencyRUR);
		// country = new Country();
		// country.setPicture(picturePl);
		// countries.save(country);
		//
		// System.out.println(countries.countAsync());
		// country = countries.find(2).stream().findFirst().get();
		//
		// final GenericRepository<CalendarData> cals = new GenericRepository<>(CalendarData.class, "db");
		// cals.removeAll();
		// out.println(cals.count());
		// System.exit(0);
		// Calendar cal = Calendar.getInstance(Locale.getDefault());
		// CalendarData calDta = new CalendarData();
		// calDta.setDate(Date.from(Instant.now()));
		// for (int idx = 0; idx < 21000; idx++) {
		// cal.add(Calendar.DATE, 1);
		// calDta.setDate(cal.getTime());
		// calDta.setYear(cal.get(Calendar.YEAR));
		// calDta.setDayNumInYear(cal.get(Calendar.DAY_OF_YEAR));
		// calDta.setDayNumInMonth(cal.get(Calendar.DAY_OF_MONTH));
		// calDta.setDayNumInWeek(cal.get(Calendar.DAY_OF_WEEK));
		// calDta.setMonthName(new SimpleDateFormat("MMMM").format(cal.getTime()));
		// calDta.setMonthNumInYear(cal.get(Calendar.MONTH) + 1);
		// calDta.setQuaterNum((cal.get(Calendar.MONTH) / 3) + 1);
		// cals.save(calDta);
		// }
		//
		// out.println(cals.count());
	}

	public static long dayOfQtr(final Instant date) {

		final LocalDate ld = LocalDate.from(date);
		final LocalDate firstDayOfQtr = LocalDate.of(ld.getYear(), ((TestDb.qtr(ld) - 1) * 3) + 1, 1);
		return ChronoUnit.DAYS.between(firstDayOfQtr, date) + 1;
	}

	public static int qtr(final LocalDate date) {

		return ((date.getMonthValue() - 1) / 3) + 1;
	}

}

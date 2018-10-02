package com.codigo.aplios.repository.core;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBException;

/*import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.domain.XmlCountries;
import com.codigo.aplios.domain.model.domain.XmlCountry;
import com.codigo.aplios.domain.model.locale.Country;*/

public class TestDb {

	public static void main(final String[] args)
			throws InterruptedException, ExecutionException, IOException, JAXBException {
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

		// final GenericRepository<Product> repository = new GenericRepository<>(Product.class, "shopdb");
		// Product entity1 = new Product();
		// entity1.setName("Now123we11233wew4534341");
		// entity1.setCreatedDate(Date.from(Instant.now()));
		// entity1.setCreatedTime(Calendar.getInstance()
		// .getTime());
		// entity1.setPrice(.1043);
		// repository.save(entity1);
		// s
		// Set<Dimension> dim = new HashSet<>();
		// Dimension dim1 = new Dimension();
		// dim1.setDepth(BigDecimal.valueOf(3.3D));
		// dim1.setHeight(BigDecimal.valueOf(.09D));
		// dim1.setWidth(BigDecimal.valueOf(4.09D));
		// dim1.setGirth(BigDecimal.valueOf(0.00D));
		// dim.add(dim1);
		//
		// entity1.setDimension(dim);
		//
		// repository.persist(entity1);
		//
		// // entity1.setName("ścerka do naczyń elastyczna");
		// // entity1.setCreatedDate(Date.from(Instant.now()));
		// // entity1.setPrice(.12);
		// // repository.persist(entity1);
		//
		// repository.findAll().forEach(System.out::println);
		//
		// System.exit(0);
		//
		// GenericRepository<CategoryAttribute> repoCategory = new
		// GenericRepository<>(CategoryAttribute.class, "db");
		// CategoryAttribute ca1 = new CategoryAttribute();
		// ca1.setName("Main");
		// ca1.setSearchable(true);
		// ca1.setValue("1");
		// CategoryAttribute ca2 = new CategoryAttribute();
		// ca2.setName("Product");
		// ca2.setSearchable(true);
		// ca2.setValue("2");
		//
		// // repoCategory.persist(ca1, ca2);
		//
		// System.out.println(repository.count());
		// System.out.println(repository.countAsync());
		//
		// System.out.println(repoCategory.find(1));
		// System.out.println(repoCategory.find(3));
		// GenericRepository<ZipCode> zipCodes = new GenericRepository<>(ZipCode.class, "db");
		//
		// ZipCode zipCode1 = new ZipCode();
		// zipCode1.setZipCity("Warszwa");
		// zipCode1.setZipCode("03-126");
		// zipCode1.setZipState("Tarchomin");
		// zipCode1.setZipLatitude(1.0D);
		// zipCode1.setZipLongitude(1.0D);
		// zipCode1.setZipState("V");
		// zipCodes.save(zipCode1);
		//
		// zipCode1 = new ZipCode();
		// zipCode1.setZipCity("Brzoza");
		// zipCode1.setZipCode("86-061");
		// zipCode1.setZipState("Brzoza");
		// zipCode1.setZipLatitude(2.0D);
		// zipCode1.setZipLongitude(2.0D);
		// zipCode1.setZipState("D");
		// zipCodes.save(zipCode1);
		// System.out.println(zipCodes.find(1).get(0).getZipCode());
		// System.out.println(zipCodes.find(2).get(0).getZipCode());
		// System.out.println(zipCodes.countAsync());
		// GenericRepository<Currency> currencies = new GenericRepository<>(Currency.class, "shopdb");
		//
		// Currency currencyUSD = new Currency();
		// currencyUSD.setName("US Dollar");
		// currencyUSD.setCurrencyCode("USD");
		// currencyUSD.setRate(1.2D);
		// currencyUSD.setDisplayLocale("en-US");
		// currencyUSD.setCustomFormatting("");
		// currencyUSD.setDescription("Dolar amerykański");
		// currencyUSD.setEntityLifeState(new EntityLifeState());
		// currencyUSD.setPublished(true);
		// currencyUSD.setDisplayOrder(1);
		// RUR 3
		// Currency currencyRUR = new Currency();
		// currencyRUR.setName("Russian Rouble");
		// currencyRUR.setCurrencyCode("RUB");
		// currencyRUR.setRate(34.5D);
		// currencyRUR.setDisplayLocale("ru-RU");
		// currencyRUR.setCustomFormatting("");
		// currencyRUR.setEntityLifeState(new EntityLifeState());
		// currencyRUR.setDescription("Rubel Federacji Rosyjskiej");
		// // currencyRUR.setPublished(true);
		// // currencyRUR.setDisplayOrder(3);
		//
		// //currencies.persist(currencyUSD, currencyRUR);
		// Optional<Currency> curr = currencies.get(1);
		// if (curr.isPresent()) {
		// Currency d1 = curr.get();
		// d1.setPublished(true);
		// d1.setDescription("Waluta nie obowiązuje w europie!");
		// EntityLifeState dd = new EntityLifeState();
		// dd.setRecordInfo(EntityRowInfo.APPROVED);
		// d1.setEntityLifeState(dd);
		// currencies.merge(d1);
		// }
		//
		//
		// final GenericRepository<Country> countries = new GenericRepository<>(
		// Country.class, "shopdb");
		// countries.removeAll();
		// final File file = new File(
		// "D:/codigo.warehouse/pulpit/countries.xml");
		// final JAXBContext jaxbContext = JAXBContext.newInstance(XmlCountries.class);
		//
		// final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		// final XmlCountries postCodes = (XmlCountries) jaxbUnmarshaller.unmarshal(file);
		//
		// for (final XmlCountry obj : postCodes.getCountries()) {
		// final Country country = new Country();
		// country.setName(obj.getName());
		// country.setTwoLetterIsoCode(obj.getCode2());
		// country.setThreLetterIsoCode(obj.getCode3());
		// country.setNumericIsoCode(obj.getCode4());
		// country.setDescription("");
		// country.setEntityLifeState(new EntityLifeState());
		// countries.save(country);
		// }

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

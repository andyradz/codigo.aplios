/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.locale;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

/**
 *
 * @author andrzej.radziszewski
 */
public class CountryService implements ICountryService {

	// private IRepository countryRepository;
	final private MutableList<Country> countryRepository = Lists.mutable.with(new Country(), new Country());

	// private final IRepository<Country> countryRepository;
	@Override
	public void deleteCountry(final Country country) {
		if (Objects.isNull(country))
			throw new NullPointerException(
				"country");

		// countryRepository.Delete(country);
		// _requestCache.RemoveByPattern(COUNTRIES_PATTERN_KEY);
		// event notification
		// _eventPublisher.EntityDeleted(country);
	}

	/**
	 *
	 * @param showHidden
	 * @return
	 */
	@Override
	public List<Country> getAllCountries(final boolean showHidden) {

		return this.countryRepository.toList();
	}

	@Override
	public List<Country> getAllCountriesForBilling(final boolean showHidden) {
		return Collections.emptyList();
	}

	@Override
	public List<Country> getAllCountriesForShipping(final boolean showHidden) {
		return Collections.emptyList();
	}

	@Override
	public Country getCountryById(final int countryId) {
		if (countryId == 0)
			return null;
		// emf = Persistence.createEntityManagerFactory("theJavaGeekJPA");
		// em = emf.createEntityManager();
		//
		// EntityTransaction txn = em.getTransaction();
		// txn.begin();
		//
		// Animal animal = new Animal();
		//
		// Bike bike = new Bike();
		//
		// Computer computer = new Computer();
		//
		// em.persist(animal);
		// em.persist(bike);
		// em.persist(computer);
		//
		// int idAnimal = animal.getIdAnimal();
		// int idBike = bike.getIdBike();
		// int idComputer = computer.getIdComputer();
		//
		// txn.commit();
		// txn.begin();
		//
		// Cache cache = emf.getCache();
		//
		// System.out.println("Animal in Cache: "
		// + cache.contains(Animal.class, idAnimal));
		// System.out.println("Bike in Cache : "
		// + cache.contains(Bike.class, idBike));
		// System.out.println("Computer in Cache : "
		// + cache.contains(Computer.class, idComputer));
		// cache.evictAll();
		//
		// System.out.println("Animal in Cache after evict: "
		// + cache.contains(Animal.class, idAnimal));
		// System.out.println("Bike in Cache after evict: "
		// + cache.contains(Bike.class, idBike));
		// System.out.println("Computer in Cache after evict: "
		// + cache.contains(Computer.class, idComputer));
		//
		// txn.commit();
		// em.close();
		// emf.close();
		// return _countryRepository.getById(countryId);
		// return Collections.emptyList();
		return this.countryRepository.get(1);
	}

	@Override
	public Country getCountryByTwoOrThreeLetterIsoCode(final String letterIsoCode) {
		return this.countryRepository.get(1);
	}

	@Override
	public Country getCountryByTwoLetterIsoCode(final String twoLetterIsoCode) {
		return this.countryRepository.get(1);
	}

	@Override
	public Country GetCountryByThreeLetterIsoCode(final String threeLetterIsoCode) {
		return this.countryRepository.get(1);
	}

	@Override
	public void insertCountry(final Country country) {
		this.countryRepository.add(country);
	}

	@Override
	public void updateCountry(final Country country) {
		this.countryRepository.get(1)
				.setName("wielkie sztos");
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.locale;

import java.util.List;

/**
 *
 * @author andrzej.radziszewski
 */
public interface ICountryService {

    void deleteCountry(Country country);

    List<Country> getAllCountries(boolean showHidden);

    List<Country> getAllCountriesForBilling(boolean showHidden);

    List<Country> getAllCountriesForShipping(boolean showHidden);

    Country getCountryById(int countryId);

    Country getCountryByTwoOrThreeLetterIsoCode(String letterIsoCode);

    Country getCountryByTwoLetterIsoCode(String twoLetterIsoCode);

    Country GetCountryByThreeLetterIsoCode(String threeLetterIsoCode);

    void insertCountry(Country country);

    void updateCountry(Country country);

}

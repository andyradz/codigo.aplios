package com.codigo.aplios.domain.model.locale;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.796+0100")
@StaticMetamodel(Country.class)
public class Country_ extends Dictionary_ {
	public static volatile SingularAttribute<Country, String> name;
	public static volatile SingularAttribute<Country, String> twoLetterIsoCode;
	public static volatile SingularAttribute<Country, String> threLetterIsoCode;
	public static volatile SingularAttribute<Country, String> numericIsoCode;
	public static volatile SingularAttribute<Country, Boolean> subjectToVal;
	public static volatile SingularAttribute<Country, Boolean> limitedToStores;
	public static volatile SingularAttribute<Country, Boolean> allowsBilling;
	public static volatile SingularAttribute<Country, Boolean> allowsShipping;
	public static volatile SingularAttribute<Country, byte[]> picture;
}

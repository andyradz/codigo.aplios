package com.codigo.aplios.domain.model.locale;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.799+0100")
@StaticMetamodel(Currency.class)
public class Currency_ extends Dictionary_ {
	public static volatile SingularAttribute<Currency, String> name;
	public static volatile SingularAttribute<Currency, String> currencyCode;
	public static volatile SingularAttribute<Currency, Double> rate;
	public static volatile SingularAttribute<Currency, String> displayLocale;
	public static volatile SingularAttribute<Currency, String> customFormatting;
	public static volatile SingularAttribute<Currency, Boolean> limitedToStores;
	public static volatile SingularAttribute<Currency, String> domainEndings;
}

package com.codigo.aplios.domain.model.stores;

import com.codigo.aplios.domain.model.common.EntityModel_;
import com.codigo.aplios.domain.model.contacts.Address;
import com.codigo.aplios.domain.model.locale.Currency;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.814+0100")
@StaticMetamodel(Store.class)
public class Store_ extends EntityModel_ {
	public static volatile SingularAttribute<Store, String> name;
	public static volatile SingularAttribute<Store, String> number;
	public static volatile SingularAttribute<Store, Boolean> open;
	public static volatile SingularAttribute<Store, String> hours;
	public static volatile SingularAttribute<Store, Address> address;
	public static volatile SingularAttribute<Store, Double> latitude;
	public static volatile SingularAttribute<Store, Double> longitude;
	public static volatile SingularAttribute<Store, String> description;
	public static volatile SingularAttribute<Store, Currency> primaryStoreCurrency;
	public static volatile SingularAttribute<Store, Currency> primaryExchangeRateCurrency;
}

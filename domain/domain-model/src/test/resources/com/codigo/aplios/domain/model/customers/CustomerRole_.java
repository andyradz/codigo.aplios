package com.codigo.aplios.domain.model.customers;

import com.codigo.aplios.domain.model.locale.Dictionary_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.787+0100")
@StaticMetamodel(CustomerRole.class)
public class CustomerRole_ extends Dictionary_ {
	public static volatile SingularAttribute<CustomerRole, String> name;
	public static volatile SingularAttribute<CustomerRole, Boolean> freeShipping;
	public static volatile SingularAttribute<CustomerRole, Boolean> taxExempt;
	public static volatile SingularAttribute<CustomerRole, Integer> taxDisplayType;
	public static volatile SingularAttribute<CustomerRole, Boolean> isSystemRole;
	public static volatile SingularAttribute<CustomerRole, String> systemName;
}

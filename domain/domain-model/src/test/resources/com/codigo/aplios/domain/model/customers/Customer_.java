package com.codigo.aplios.domain.model.customers;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.786+0100")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, CutomerName> officialTitle;
	public static volatile SingularAttribute<Customer, CutomerName> firstName;
	public static volatile SingularAttribute<Customer, CutomerName> lastName;
	public static volatile SingularAttribute<Customer, CutomerName> fullName;
	public static volatile SingularAttribute<Customer, CutomerName> officialSalutation;
}

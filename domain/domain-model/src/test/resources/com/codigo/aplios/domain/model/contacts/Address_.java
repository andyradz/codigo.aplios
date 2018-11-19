package com.codigo.aplios.domain.model.contacts;

import com.codigo.aplios.domain.model.common.EntityModel_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.766+0100")
@StaticMetamodel(Address.class)
public class Address_ extends EntityModel_ {
	public static volatile SingularAttribute<Address, AddressType> addressType;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> buildNumber;
	public static volatile SingularAttribute<Address, String> flatNumber;
	public static volatile SingularAttribute<Address, String> province;
	public static volatile SingularAttribute<Address, String> county;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, String> zipCode;
	public static volatile SingularAttribute<Address, String> postName;
	public static volatile SingularAttribute<Address, String> postBox;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> addressLine1;
	public static volatile SingularAttribute<Address, String> addressLine2;
	public static volatile SingularAttribute<Address, String> addressLine3;
	public static volatile SingularAttribute<Address, String> emailAddress;
	public static volatile SingularAttribute<Address, String> companyName;
}

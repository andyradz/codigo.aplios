package com.codigo.aplios.domain.model.contacts;

import com.codigo.aplios.domain.model.common.EntityModel_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.769+0100")
@StaticMetamodel(Phone.class)
public class Phone_ extends EntityModel_ {
	public static volatile SingularAttribute<Phone, String> countryCode;
	public static volatile SingularAttribute<Phone, String> extension;
	public static volatile SingularAttribute<Phone, String> phoneNumber;
	public static volatile SingularAttribute<Phone, PhoneType> phoneType;
	public static volatile SingularAttribute<Phone, Boolean> isDefault;
	public static volatile SingularAttribute<Phone, Boolean> isActive;
}

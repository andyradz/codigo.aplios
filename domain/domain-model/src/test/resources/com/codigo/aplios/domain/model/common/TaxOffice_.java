package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.contacts.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.763+0100")
@StaticMetamodel(TaxOffice.class)
public class TaxOffice_ {
	public static volatile SingularAttribute<TaxOffice, Long> id;
	public static volatile SingularAttribute<TaxOffice, String> officeType;
	public static volatile SingularAttribute<TaxOffice, String> officeName;
	public static volatile SingularAttribute<TaxOffice, String> officeCode;
	public static volatile SetAttribute<TaxOffice, Address> addresses;
}

package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.contacts.Address;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.739+0100")
@StaticMetamodel(Person.class)
public class Person_ {
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SetAttribute<Person, Address> addresses;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, String> middleName;
	public static volatile SingularAttribute<Person, String> sureName;
	public static volatile SingularAttribute<Person, Date> birthDate;
	public static volatile SingularAttribute<Person, Date> birthTIme;
	public static volatile MapAttribute<Person, String, PersonAttributeImpl> customerAttributes;
}

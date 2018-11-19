package com.codigo.aplios.domain.model.locale;

import com.codigo.aplios.domain.model.common.EntityModel_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.804+0100")
@StaticMetamodel(ZipCode.class)
public class ZipCode_ extends EntityModel_ {
	public static volatile SingularAttribute<ZipCode, String> zipState;
	public static volatile SingularAttribute<ZipCode, String> zipCity;
	public static volatile SingularAttribute<ZipCode, Double> zipLongitude;
	public static volatile SingularAttribute<ZipCode, Double> zipLatitude;
	public static volatile SingularAttribute<ZipCode, Integer> zipcode;
	public static volatile SingularAttribute<ZipCode, String> street;
}

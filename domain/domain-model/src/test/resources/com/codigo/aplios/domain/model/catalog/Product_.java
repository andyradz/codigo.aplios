package com.codigo.aplios.domain.model.catalog;

import com.codigo.aplios.domain.model.common.EntityModel_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.724+0100")
@StaticMetamodel(Product.class)
public class Product_ extends EntityModel_ {
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, Date> createdDate;
	public static volatile SingularAttribute<Product, Date> createdTime;
	public static volatile SetAttribute<Product, Dimension> dimension;
}

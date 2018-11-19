package com.codigo.aplios.domain.model.catalog;

import com.codigo.aplios.domain.model.common.EntityModel_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.714+0100")
@StaticMetamodel(CategoryAttribute.class)
public class CategoryAttribute_ extends EntityModel_ {
	public static volatile SingularAttribute<CategoryAttribute, String> name;
	public static volatile SingularAttribute<CategoryAttribute, String> value;
	public static volatile SingularAttribute<CategoryAttribute, Boolean> searchable;
	public static volatile SingularAttribute<CategoryAttribute, Category> category;
}

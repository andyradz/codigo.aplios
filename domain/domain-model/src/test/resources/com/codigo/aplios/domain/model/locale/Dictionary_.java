package com.codigo.aplios.domain.model.locale;

import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.common.EntityModel_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.801+0100")
@StaticMetamodel(Dictionary.class)
public class Dictionary_ extends EntityModel_ {
	public static volatile SingularAttribute<Dictionary, Long> displayOrder;
	public static volatile SingularAttribute<Dictionary, Boolean> isPublished;
	public static volatile SingularAttribute<Dictionary, String> description;
	public static volatile SingularAttribute<Dictionary, EntityDateTime> entityDateTime;
	public static volatile SingularAttribute<Dictionary, EntityLifeState> entityLifeState;
}

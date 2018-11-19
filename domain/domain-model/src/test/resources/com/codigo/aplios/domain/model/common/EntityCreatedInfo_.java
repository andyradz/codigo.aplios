package com.codigo.aplios.domain.model.common;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.727+0100")
@StaticMetamodel(EntityCreatedInfo.class)
public class EntityCreatedInfo_ {
	public static volatile SingularAttribute<EntityCreatedInfo, Date> createdDateUtc;
	public static volatile SingularAttribute<EntityCreatedInfo, Date> createdTimeUtc;
	public static volatile SingularAttribute<EntityCreatedInfo, String> createdByUser;
}

package com.codigo.aplios.domain.model.orders;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.808+0100")
@StaticMetamodel(Order.class)
public class Order_ {
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, VersionHistory> versioningHistory;
	public static volatile SingularAttribute<Order, OrderVersion> currentVersion;
}

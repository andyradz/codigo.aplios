package com.codigo.aplios.domain.model.orders;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.810+0100")
@StaticMetamodel(OrderVersion.class)
public class OrderVersion_ {
	public static volatile SingularAttribute<OrderVersion, Long> id;
	public static volatile SingularAttribute<OrderVersion, String> customId;
	public static volatile SingularAttribute<OrderVersion, String> name;
	public static volatile SingularAttribute<OrderVersion, Double> price;
	public static volatile SingularAttribute<OrderVersion, Date> date;
}

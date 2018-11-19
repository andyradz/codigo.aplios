package com.codigo.aplios.domain.model.catalog;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.716+0100")
@StaticMetamodel(Dimension.class)
public class Dimension_ {
	public static volatile SingularAttribute<Dimension, BigDecimal> width;
	public static volatile SingularAttribute<Dimension, BigDecimal> height;
	public static volatile SingularAttribute<Dimension, BigDecimal> depth;
	public static volatile SingularAttribute<Dimension, BigDecimal> girth;
	public static volatile SingularAttribute<Dimension, String> size;
	public static volatile SingularAttribute<Dimension, String> container;
	public static volatile SingularAttribute<Dimension, String> dimensionUnitOfMeasure;
}

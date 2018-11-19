package com.codigo.aplios.domain.model.catalog;

import com.codigo.aplios.domain.model.common.Picture;
import com.codigo.aplios.domain.model.locale.Dictionary_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.721+0100")
@StaticMetamodel(Manufacturer.class)
public class Manufacturer_ extends Dictionary_ {
	public static volatile SingularAttribute<Manufacturer, String> name;
	public static volatile SingularAttribute<Manufacturer, Long> manufacturerTemplateId;
	public static volatile SingularAttribute<Manufacturer, String> metaKeywords;
	public static volatile SingularAttribute<Manufacturer, String> metaDescription;
	public static volatile SingularAttribute<Manufacturer, String> metaTitle;
	public static volatile SingularAttribute<Manufacturer, Picture> picture;
	public static volatile SingularAttribute<Manufacturer, Long> pageSize;
	public static volatile SingularAttribute<Manufacturer, Boolean> allowCustomersToSelectPageSize;
	public static volatile SingularAttribute<Manufacturer, String> pageSizeOptions;
	public static volatile SingularAttribute<Manufacturer, String> priceRanges;
	public static volatile SingularAttribute<Manufacturer, Boolean> limitedToStores;
	public static volatile SingularAttribute<Manufacturer, Boolean> hasDiscountsApplied;
}

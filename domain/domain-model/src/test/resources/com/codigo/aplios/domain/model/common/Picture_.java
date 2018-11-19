package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.locale.Dictionary_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.761+0100")
@StaticMetamodel(Picture.class)
public class Picture_ extends Dictionary_ {
	public static volatile SingularAttribute<Picture, byte[]> pictureBinary;
	public static volatile SingularAttribute<Picture, String> mimeType;
	public static volatile SingularAttribute<Picture, String> seoFilename;
	public static volatile SingularAttribute<Picture, Boolean> isNew;
	public static volatile SingularAttribute<Picture, Boolean> isTransient;
	public static volatile SingularAttribute<Picture, Long> mediaStorageId;
	public static volatile SingularAttribute<Picture, Integer> width;
	public static volatile SingularAttribute<Picture, Integer> height;
}

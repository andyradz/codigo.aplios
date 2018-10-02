package com.codigo.aplios.domain.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.locale.Dictionary;

@Entity
@Table(name = "Pictures")
@Customizer(EntityColumnPositionCustomizer.class)
public class Picture extends Dictionary {

	private static final long serialVersionUID = 9077618368862546220L;

	@Column(name = "PictureBinary", columnDefinition = "varbinary(max)", nullable = true)
	private byte[] pictureBinary;

	@Column(name = "MimeType", columnDefinition = "nvarchar(40)", nullable = false)
	private String mimeType;

	@Column(name = "SeoFilename", columnDefinition = "nvarchar(300)", nullable = true)
	private String seoFilename;

	@Column(name = "IsNew", nullable = false)
	private boolean isNew;

	@Column(name = "IsTransient", /*
	                               * columnDefinition = "default (0)",
	                               */ nullable = false)
	private boolean isTransient;

	@Column(name = "MediaStorageId", nullable = true)
	private long mediaStorageId;

	@Column(name = "Width", nullable = true)
	private int width;

	@Column(name = "Height", nullable = true)
	private int height;

	// @OneToOne(fetch = FetchType.LAZY, mappedBy = "picture")
	// private Manufacturer manufacturer;
}

package com.codigo.aplios.domain.model.catalog;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.common.EntityCreatedInfo;
import com.codigo.aplios.domain.model.common.EntityPublishedInfo;
import com.codigo.aplios.domain.model.common.EntityUpdatedInfo;

@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityDateTime {

	@Embedded
	private EntityCreatedInfo entityCreatedInfo;

	@Embedded
	private EntityUpdatedInfo entityUpdatedInfo;

	@Embedded
	private EntityPublishedInfo entityPublishedInfo;

	public EntityCreatedInfo getEntityCreatedInfo() {
		return this.entityCreatedInfo;
	}

	public void setEntityCreatedInfo(final EntityCreatedInfo entityCreatedInfo) {
		this.entityCreatedInfo = entityCreatedInfo;
	}

	public EntityUpdatedInfo getEntityUpdatedInfo() {
		return this.entityUpdatedInfo;
	}

	public void setEntityUpdatedInfo(final EntityUpdatedInfo entityUpdatedInfo) {
		this.entityUpdatedInfo = entityUpdatedInfo;
	}

	public EntityPublishedInfo getEntityPublishedInfo() {
		return this.entityPublishedInfo;
	}

	public void setEntityPublishedInfo(final EntityPublishedInfo entityPublishedInfo) {
		this.entityPublishedInfo = entityPublishedInfo;
	}

}

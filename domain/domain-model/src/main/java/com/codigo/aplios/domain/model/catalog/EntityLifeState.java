package com.codigo.aplios.domain.model.catalog;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.common.EntityRowInfo;
import com.codigo.aplios.domain.model.common.EntityRowState;

@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityLifeState {

	@Enumerated(EnumType.STRING)
	@Column(name = "AccessState", nullable = false)
	private EntityRowState recordState = EntityRowState.ACTIVE;

	@Enumerated(EnumType.STRING)
	@Column(name = "ApprovedState", nullable = false)
	private EntityRowInfo recordInfo = EntityRowInfo.PENDING;

	public EntityRowState getRowState() {
		return this.recordState;
	}

	public void setRowState(EntityRowState rowState) {
		this.recordState = rowState;
	}

	public EntityRowInfo getRecordInfo() {
		return this.recordInfo;
	}

	public void setRecordInfo(EntityRowInfo recordInfo) {
		this.recordInfo = recordInfo;
	}
}

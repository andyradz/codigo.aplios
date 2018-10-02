package com.codigo.aplios.domain.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;

@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class PersonName {

	@Column(name = "FirstName", length = 50, nullable = false)
	@ColumnPosition(position = 1)
	private String firstName;

	@ColumnPosition(position = 2)
	@Column(name = "MiddleName", length = 50, nullable = true)
	private String middleName;

	@ColumnPosition(position = 3)
	@Column(name = "SureName", length = 50, nullable = false)
	private String sureName;

	@ColumnPosition(position = 4)
	@Column(name = "FullName", length = 150)
	private String fullName;

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSureName() {
		return this.sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
}

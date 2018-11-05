package com.codigo.aplios.domain.model.customers;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CutomerName {

	@Column(name = "FullName", length = 25, nullable = false)
	String fullName;

	public String getFullName() {

		return this.fullName;
	}

	public void setFullName(final String fullName) {

		this.fullName = fullName;
	}
}
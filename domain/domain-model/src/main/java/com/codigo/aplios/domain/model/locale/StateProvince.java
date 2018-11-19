package com.codigo.aplios.domain.model.locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Index;

public class StateProvince extends Dictionary {

	private static final long serialVersionUID = 1769688424311131951L;

	@Column(name = "NAME", nullable = false)
	@Index(name = "STATE_NAME_INDEX", columnNames = { "NAME" })
	private String name;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "ID")
	private Country country;

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getAbbreviation() {

		return this.abbreviation;
	}

	public void setAbbreviation(final String abbreviation) {

		this.abbreviation = abbreviation;
	}

	public Country getCountry() {

		return this.country;
	}

	public void setCountry(final Country country) {

		this.country = country;
	}

}

package com.codigo.aplios.domain.model.contacts;

//@XmlEnum()
public enum AddressType {

	// @XmlEnumValue(name = "Business")
	BUSINESS("Adres bazowy firmy"),
	ADMINISTRATIVE("Adres administracyjny siedziby"),
	OTHER("Inny adres "),
	REGISTRATION("Adres siedziby prawnej"),
	RRESIDENTIAL("Adres zamieszkania"),
	POSTAL("Adres pocztowy");

	private String description;

	private AddressType(final String description) {

		this.description = description;
	}

	public String getDescription() {

		return this.description;
	}
}

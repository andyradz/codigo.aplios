package com.codigo.aplios.domain.model.contacts;

public enum AddressType {

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

package data.mapping.location;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum AddressType {

	@XmlEnumValue("ADMINISTRATIVE")
	ADMINISTRATIVE("Adres administracyjny siedziby"),
	
	@XmlEnumValue("OTHER")
	OTHER("OTHER"),
	
	@XmlEnumValue("REGISTRATION")
	REGISTRATION("Adres siedziby prawnej"),
	
	@XmlEnumValue("RRESIDENTIAL")
	RRESIDENTIAL("Adres zamieszkania"),
	
	@XmlEnumValue("POSTAL")
	POSTAL("Adres pocztowy");

	private String description;

	private AddressType(final String description) {

		this.description = description;
	}

	public String getDescription() {

		return this.description;
	}
}

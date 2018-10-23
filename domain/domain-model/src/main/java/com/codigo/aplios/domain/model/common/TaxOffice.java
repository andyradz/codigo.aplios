package com.codigo.aplios.domain.model.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.contacts.Address;

//https://viralpatel.net/blogs/hibernate-self-join-annotations-one-to-many-mapping/
//https://www.tutorialspoint.com/jpa/jpa_advanced_mappings.htm
//https://www.byteslounge.com/tutorials/jpa-entity-versioning-version-and-optimistic-locking

@Entity
@Table(name = "TaxOffice")
public class TaxOffice implements Serializable {

	private static final long serialVersionUID = 6664795676546009929L;

	@Id
	@Column(name = "Id")
	private Long id;

	@Column(name = "OfficeType", length = 5, nullable = false, unique = false)
	private String officeType;

	@Column(name = "OfficeName", length = 100, nullable = false, unique = true)
	private String officeName;

	@Column(name = "OfficeCode", length = 4, nullable = false, unique = true)
	private String officeCode;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Taxoffice_Id")
	private Set<Address> addresses = new HashSet<>();

	public Long getId() {

		return this.id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getOfficeType() {

		return this.officeType;
	}

	public void setOfficeType(final String officeType) {

		this.officeType = officeType;
	}

	public String getOfficeName() {

		return this.officeName;
	}

	public void setOfficeName(final String officeName) {

		this.officeName = officeName;
	}

	public Set<Address> getAddresses() {

		return this.addresses;
	}

	public void setAddresses(final Set<Address> addresses) {

		this.addresses = addresses;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.officeType == null) ? 0 : this.officeType.hashCode());
		result = (prime * result) + ((this.addresses == null) ? 0 : this.addresses.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.officeName == null) ? 0 : this.officeName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TaxOffice other = (TaxOffice) obj;
		if (this.officeType == null) {
			if (other.officeType != null)
				return false;
		}
		else if (!this.officeType.equals(other.officeType))
			return false;
		if (this.addresses == null) {
			if (other.addresses != null)
				return false;
		}
		else if (!this.addresses.equals(other.addresses))
			return false;
		if (this.id == null) {
			if (other.id != null)
				return false;
		}
		else if (!this.id.equals(other.id))
			return false;
		if (this.officeName == null) {
			if (other.officeName != null)
				return false;
		}
		else if (!this.officeName.equals(other.officeName))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "TaxOffice [id=" + this.id + ", OfficeType=" + this.officeType + ", officeName=" + this.officeName
				+ ", addresses=" + this.addresses + "]";
	}

	public String getOfficeCode() {

		return this.officeCode;
	}

	public void setOfficeCode(final String officeCode) {

		this.officeCode = officeCode;
	}
}

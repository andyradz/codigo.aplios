package com.codigo.aplios.domain.model.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.domain.model.contacts.Address;

@Entity
@Table(name = "Person")
public class Person implements Serializable {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID")
	private Set<Address> addresses = new HashSet<>();

	@Column(name = "NAME", length = 50, nullable = false)
	private String name;

	@Column(name = "MIDDLENAME", length = 50, nullable = true)
	private String middleName;

	@Column(name = "SURENAME", length = 100, nullable = false)
	private String sureName;

	@Column(name = "BIRTHDATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "BIRTHTIME", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date birthTIme;

	@OneToMany(mappedBy = "person", targetEntity = PersonAttributeImpl.class, cascade = { CascadeType.ALL })
	@MapKey(name = "name")
	protected Map<String, PersonAttributeImpl> customerAttributes = new HashMap<>();

	public Long getId() {

		return this.id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getMiddleName() {

		return this.middleName;
	}

	public void setMiddleName(final String middleName) {

		this.middleName = middleName;
	}

	public String getSureName() {

		return this.sureName;
	}

	public void setSureName(final String sureName) {

		this.sureName = sureName;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.middleName == null) ? 0 : this.middleName.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.sureName == null) ? 0 : this.sureName.hashCode());
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

		if (this.getClass() != obj.getClass())
			return false;

		final Person other = (Person) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		}
		else if (!this.id.equals(other.id))
			return false;
		if (this.middleName == null) {
			if (other.middleName != null)
				return false;
		}
		else if (!this.middleName.equals(other.middleName))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		}
		else if (!this.name.equals(other.name))
			return false;
		if (this.sureName == null) {
			if (other.sureName != null)
				return false;
		}
		else if (!this.sureName.equals(other.sureName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Person [id=" + this.id + ", name=" + this.name + ", middleName=" + this.middleName + ", sureName="
				+ this.sureName + "]";
	}

	public Set<Address> getAddresses() {

		return this.addresses;
	}

	public void setAddresses(final Set<Address> addresses) {

		this.addresses = addresses;
	}

	public void setAddress(final Address address) {

		this.addresses.add(address);
	}

	public Date getBirthDate() {

		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {

		this.birthDate = birthDate;
	}

	public Date getBirthTIme() {

		return this.birthTIme;
	}

	public void setBirthTIme(final Date birthTIme) {

		this.birthTIme = birthTIme;
	}

	public Map<String, PersonAttributeImpl> getPersonAttributes() {

		return this.customerAttributes;
	}

	public void setCustomerAttributes(final Map<String, PersonAttributeImpl> customerAttributes) {

		this.customerAttributes = customerAttributes;
	}

}

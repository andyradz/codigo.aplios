package com.codigo.aplios.domain.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PersonAttribute")
@Cache(type = CacheType.FULL)
public class PersonAttributeImpl implements Serializable {

	private static final long serialVersionUID = -765040394891214983L;

	/** The id. */
	@Id
	@Column(name = "CUSTOMER_ATTR_ID")
	protected Long id;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	protected String name;

	/** The value. */
	@Column(name = "VALUE")
	protected String value;

	/** The customer. */
	@ManyToOne // (targetEntity = PersonAttributeImpl.class, optional = false)
	@JoinColumn(name = "CUSTOMER_ID")
	protected Person person;

	public Long getId() {

		return this.id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getValue() {

		return this.value;
	}

	public void setValue(final String value) {

		this.value = value;
	}

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	@Override
	public String toString() {

		return this.value;
	}

	public Person getPerson() {

		return this.person;
	}

	public void setPerson(final Person customer) {

		this.person = customer;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.person == null) ? 0 : this.person.hashCode());
		result = (prime * result) + ((this.value == null) ? 0 : this.value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		final PersonAttributeImpl other = (PersonAttributeImpl) obj;

		if ((this.id != null) && (other.id != null))
			return this.id.equals(other.id);

		if (this.name == null) {
			if (other.name != null)
				return false;
		}
		else if (!this.name.equals(other.name))
			return false;
		if (this.person == null) {
			if (other.person != null)
				return false;
		}
		else if (!this.person.equals(other.person))
			return false;
		if (this.value == null) {
			if (other.value != null)
				return false;
		}
		else if (!this.value.equals(other.value))
			return false;
		return true;
	}

}
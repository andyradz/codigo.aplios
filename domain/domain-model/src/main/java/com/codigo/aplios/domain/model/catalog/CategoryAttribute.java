package com.codigo.aplios.domain.model.catalog;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.common.EntityModel;

//@Entity
@Table(name = "CategoryAttribute")
// usage=CacheConcurrenceStarategy.
public class CategoryAttribute extends EntityModel {

	private static final long serialVersionUID = 7651592702745185585L;

	@Column(name = "Name")
	private String name;

	@Column(name = "Value")
	private String value;

	@Column(name = "Searchable")
	private Boolean searchable;

	@ManyToOne(targetEntity = Category.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CategoryId")
	private Category category;

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getValue() {

		return this.value;
	}

	public void setValue(final String value) {

		this.value = value;
	}

	public Boolean getSearchable() {

		return this.searchable == null ? Boolean.FALSE : this.searchable;
	}

	public void setSearchable(final Boolean searchable) {

		this.searchable = searchable;
	}

	public Category getCategory() {

		return this.category;
	}

	public void setCategory(final Category category) {

		this.category = category;
	}

	@Override
	public boolean equals(final Object instance) {

		if (Objects.isNull(instance))
			return false;
		if (this == instance)
			return true;
		final CategoryAttribute cattAttr = CategoryAttribute.class.cast(instance);

		// if (this.id != null && cattAttr.id != null)
		// return this.id.equals(cattAttr.id);
		if (this.name != null)
			if (cattAttr != null) {
				if (cattAttr.category != null)
					return false;
			}
			else if (!this.category.equals(cattAttr.category))
				return false;
		if (this.value == null) {
			if (cattAttr.value != null)
				return false;
		}
		else if (!this.value.equals(cattAttr.value))
			return false;
		return true;
	}

	;

	@Override
	public String toString() {

		return this.name;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.name, this.category, this.value);
	}
}

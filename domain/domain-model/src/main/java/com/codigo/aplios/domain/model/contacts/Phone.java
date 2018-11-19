package com.codigo.aplios.domain.model.contacts;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.common.EntityModel;

@Entity
@Table(name = "Phone")
@Customizer(EntityColumnPositionCustomizer.class)
public class Phone extends EntityModel {

	private static final long serialVersionUID = 6282306950384374334L;

	@ColumnPosition(position = 1)
	@Column(name = "CountryCode")
	private String countryCode;

	@ColumnPosition(position = 2)
	@Column(name = "Extension")
	private String extension;

	@ColumnPosition(position = 3)
	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;

	@ColumnPosition(position = 4)
	@Enumerated(EnumType.STRING)
	@Column(name = "PhoneType")
	private PhoneType phoneType;

	@ColumnPosition(position = 5)
	@Column(name = "IsDefault")
	private boolean isDefault;

	@ColumnPosition(position = 6)
	@Column(name = "IsActive")
	private boolean isActive;

	public String getCountryCode() {

		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {

		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {

		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {

		return this.phoneType;
	}

	public void setPhoneType(final PhoneType phoneType) {

		this.phoneType = phoneType;
	}

	public String getExtension() {

		return this.extension;
	}

	public void setExtension(final String extension) {

		this.extension = extension;
	}

	public boolean isDefault() {

		return this.isDefault;
	}

	public void setDefault(final boolean isDefault) {

		this.isDefault = isDefault;
	}

	public boolean isActive() {

		return this.isActive;
	}

	public void setActive(final boolean isActive) {

		this.isActive = isActive;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.isActive, this.isDefault, this.countryCode, this.phoneNumber, this.extension);
	}

}

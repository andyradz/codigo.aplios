package com.codigo.aplios.domain.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import com.codigo.aplios.domain.model.common.EntityModel;

// TODO: dodać jeszcze powiat,gmina, województwo

@Entity
@Table(name = "ZipCode")
public class ZipCode extends EntityModel implements Serializable, IZipCode {

	private static final long serialVersionUID = 4072853729370151441L;

	@Column(name = "Code", insertable = false, updatable = false)
	@Index(name = "ZIPCODE_ZIP_INDEX", columnNames = { "Code" })
	private Integer zipcode;

	@Column(name = "State", insertable = false, updatable = false)
	@Index(name = "ZIPCODE_STATE_INDEX", columnNames = { "State" })
	private String zipState;

	@Column(name = "City")
	@Index(name = "ZIPCODE_CITY_INDEX", columnNames = { "City" })
	private String zipCity;

	@Column(name = "Longitude")
	@Index(name = "ZIPCODE_LONGITUDE_INDEX", columnNames = { "Longitude" })
	private double zipLongitude;

	@Column(name = "Latitude")
	@Index(name = "ZIPCODE_LATITUDE_INDEX", columnNames = { "Latitude" })
	private double zipLatitude;

	@Column(name = "Street")
	@Index(name = "ZIPCODE_STREET_INDEX", columnNames = { "Street" })
	private String street;

	@Override
	public Integer getZipcode() {

		return this.zipcode;
	}

	@Override
	public void setZipcode(final Integer zipcode) {

		this.zipcode = zipcode;
	}

	@Override
	public String getZipState() {

		return this.zipState;
	}

	@Override
	public void setZipState(final String zipState) {

		this.zipState = zipState;
	}

	@Override
	public String getZipCity() {

		return this.zipCity;
	}

	@Override
	public void setZipCity(final String zipCity) {

		this.zipCity = zipCity;
	}

	@Override
	public double getZipLongitude() {

		return this.zipLongitude;
	}

	@Override
	public void setZipLongitude(final double zipLongitude) {

		this.zipLongitude = zipLongitude;
	}

	@Override
	public double getZipLatitude() {

		return this.zipLatitude;
	}

	@Override
	public void setZipLatitude(final double zipLatitude) {

		this.zipLatitude = zipLatitude;
	}

	@Override
	public String getZipStreet() {

		return this.street;
	}

	@Override
	public void setZipStreet(final String zipStreet) {

		this.street = zipStreet;
	}
}

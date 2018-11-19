/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.stores;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codigo.aplios.domain.model.common.EntityModel;
import com.codigo.aplios.domain.model.contacts.Address;
import com.codigo.aplios.domain.model.locale.Currency;

/**
 *
 * @author andrzej.radziszewski
 */
// @Entity
// @Table(name = "Store")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
// @SQLDelete(sql="UPDATE BLC_STORE SET ARCHIVED = 'Y' WHERE STORE_ID = ?")
// @Inheritance(strategy = InheritanceType.JOINED)
public class Store extends EntityModel implements IStore {

	private static final long serialVersionUID = -2193687439440549282L;

	@Column(name = "Name", nullable = false)
	protected String name;

	@Column(name = "Number")
	protected String number;

	@Column(name = "Open")
	protected Boolean open;

	@Column(name = "Hours")
	protected String hours;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
	@JoinColumn(name = "AddressId")
	protected Address address;

	@Column(name = "Latitude")
	protected Double latitude;

	@Column(name = "Longitude")
	protected Double longitude;

	@Column(name = "Description")
	protected String description;

	@Column(name = "PrimaryStoreCurrency")
	private Currency primaryStoreCurrency;

	@Column(name = "PrimaryExchangeRateCurrency")
	private Currency primaryExchangeRateCurrency;

	// @Embedded
	// protected ArchiveStatus archiveStatus = new ArchiveStatus();

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public void setName(final String name) {

		this.name = name;
	}

	@Override
	public Address getAddress() {

		return this.address;
	}

	@Override
	public void setAddress(final Address address) {

		this.address = address;
	}

	@Override
	public Double getLongitude() {

		return this.longitude;
	}

	@Override
	public void setLongitude(final Double longitude) {

		this.longitude = longitude;
	}

	@Override
	public Double getLatitude() {

		return this.latitude;
	}

	@Override
	public void setLatitude(final Double latitude) {

		this.latitude = latitude;
	}

	@Override
	public String getStoreNumber() {

		return this.number;
	}

	@Override
	public void setStoreNumber(final String storeNumber) {

		this.number = storeNumber;
	}

	@Override
	public Boolean getOpen() {

		return this.open;
	}

	@Override
	public void setOpen(final Boolean open) {

		this.open = open;
	}

	@Override
	public String getStoreHours() {

		return this.hours;
	}

	@Override
	public void setStoreHours(final String storeHours) {

		this.hours = storeHours;
	}

	// @Override
	// public Character getArchived() {
	//
	// ArchiveStatus temp;
	// if (archiveStatus == null) {
	// temp = new ArchiveStatus();
	// } else {
	// temp = archiveStatus;
	// }
	// return temp.getArchived();
	// }

	// @Override
	// public void setArchived(Character archived) {
	//
	// if (archiveStatus == null) {
	// archiveStatus = new ArchiveStatus();
	// }
	// archiveStatus.setArchived(archived);
	// }

	// @Override
	// public boolean isActive() {
	//
	// return 'Y' != getArchived();
	// }

	public static class Presentation {

		public static class Tab {
			public static class Name {
				public static final String Advanced = "StoreImpl_Advanced_Tab";

			}

			public static class Order {
				public static final int Advanced = 7000;
			}
		}

		public static class Group {
			public static class Name {
				public static final String	General		= "General";
				public static final String	Location	= "StoreImpl_Store_Location";
				public static final String	Geocoding	= "StoreImpl_Store_Geocoding";
			}

			public static class Order {
				public static final int	General		= 1000;
				public static final int	Location	= 2000;
				public static final int	Geocoding	= 3000;
			}
		}

		public static class FieldOrder {
			public static final int	NAME		= 1000;
			public static final int	LATITUDE	= 9000;
			public static final int	LONGITUDE	= 10000;
		}
	}

	@Override
	public String getDescription() {

		return this.description;
	}

	@Override
	public void setDescription(final String description) {

		this.description = description;
	}

	/**
	 * @return the primaryStoreCurrency
	 */
	@Override
	public Currency getPrimaryStoreCurrency() {

		return this.primaryStoreCurrency;
	}

	/**
	 * @param primaryStoreCurrency
	 *        the primaryStoreCurrency to set
	 */
	@Override
	public void setPrimaryStoreCurrency(final Currency primaryStoreCurrency) {

		this.primaryStoreCurrency = primaryStoreCurrency;
	}

	/**
	 * @return the primaryExchangeRateCurrency
	 */
	@Override
	public Currency getPrimaryExchangeRateCurrency() {

		return this.primaryExchangeRateCurrency;
	}

	/**
	 * @param primaryExchangeRateCurrency
	 *        the primaryExchangeRateCurrency to set
	 */
	@Override
	public void setPrimaryExchangeRateCurrency(final Currency primaryExchangeRateCurrency) {

		this.primaryExchangeRateCurrency = primaryExchangeRateCurrency;
	}

}

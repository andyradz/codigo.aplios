package com.codigo.aplios.domain.model.contacts;

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
@Table(name = "Address")
@Customizer(EntityColumnPositionCustomizer.class)
public class Address extends EntityModel {

	private static final long serialVersionUID = -2478551510027360371L;

	// @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Person.class)
	// @JoinColumn(nullable = false, name = "PERSON_ID")
	// @PrimaryKeyJoinColumn(name="ID", referencedColumnName="PERSON_ID")
	// @PrimaryKeyJoinColumn(name="ID", referencedColumnName="PERSON_ID")
	// private Person person;

	@Column(name = "AddressTYpe", length = 4, nullable = false)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@Column(name = "Country", length = 50, nullable = false)
	private String country;

	@Column(name = "City", length = 50, nullable = false)
	private String city;

	@Column(name = "Street", length = 50, nullable = false)
	private String street;

	@Column(name = "BuildNo", length = 50, nullable = true)
	private String buildNumber;

	@Column(name = "FlatNo", length = 50, nullable = true)
	private String flatNumber;

	// województwo
	@Column(name = "Province", length = 50, nullable = true)
	private String province;

	// powiat
	@Column(name = "County", length = 50, nullable = true)
	private String county;

	// gmina
	@Column(name = "District", length = 50, nullable = true)
	private String district;

	@Column(name = "ZipCode", length = 50, nullable = true)
	private String zipCode;

	@Column(name = "PostName", length = 50, nullable = true)
	private String postName;

	@Column(name = "PostBox", length = 50, nullable = true)
	private String postBox;

	@Column(name = "PostalCode")
	@ColumnPosition(position = 2)
	private String postalCode;

	/**
	 * @return the addressType
	 */
	public AddressType getAddressType() {

		return this.addressType;
	}

	/**
	 * @param addressType
	 *        the addressType to set
	 */
	public void setAddressType(final AddressType addressType) {

		this.addressType = addressType;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {

		return this.country;
	}

	/**
	 * @param country
	 *        the country to set
	 */
	public void setCountry(final String country) {

		this.country = country;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {

		return this.street;
	}

	/**
	 * @param street
	 *        the street to set
	 */
	public void setStreet(final String street) {

		this.street = street;
	}

	/**
	 * @return the buildNumber
	 */
	public String getBuildNumber() {

		return this.buildNumber;
	}

	/**
	 * @param buildNumber
	 *        the buildNumber to set
	 */
	public void setBuildNumber(final String buildNumber) {

		this.buildNumber = buildNumber;
	}

	/**
	 * @return the flatNumber
	 */
	public String getFlatNumber() {

		return this.flatNumber;
	}

	/**
	 * @param flatNumber
	 *        the flatNumber to set
	 */
	public void setFlatNumber(final String flatNumber) {

		this.flatNumber = flatNumber;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {

		return this.province;
	}

	/**
	 * @param province
	 *        the province to set
	 */
	public void setProvince(final String province) {

		this.province = province;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {

		return this.district;
	}

	/**
	 * @param district
	 *        the district to set
	 */
	public void setDistrict(final String district) {

		this.district = district;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {

		return this.zipCode;
	}

	/**
	 * @param zipCode
	 *        the zipCode to set
	 */
	public void setZipCode(final String zipCode) {

		this.zipCode = zipCode;
	}

	/**
	 * @return the postName
	 */
	public String getPostName() {

		return this.postName;
	}

	/**
	 * @param postName
	 *        the postName to set
	 */
	public void setPostName(final String postName) {

		this.postName = postName;
	}

	/**
	 * @return the postBox
	 */
	public String getPostBox() {

		return this.postBox;
	}

	/**
	 * @param postBox
	 *        the postBox to set
	 */
	public void setPostBox(final String postBox) {

		this.postBox = postBox;
	}

	@Column(name = "AddressLine1")
	@ColumnPosition(position = 3)
	private String addressLine1;

	@ColumnPosition(position = 4)
	@Column(name = "AddressLine2")
	private String addressLine2;

	@ColumnPosition(position = 5)
	@Column(name = "AddressLine3")
	private String addressLine3;

	@ColumnPosition(position = 6)
	@Column(name = "EmailAddress")
	private String emailAddress;

	@ColumnPosition(position = 7)
	@Column(name = "CompanyName")
	private String companyName;

	public String getEmailAddress() {

		return this.emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {

		this.emailAddress = emailAddress;
	}

	public String getCompanyName() {

		return this.companyName;
	}

	public void setCompanyName(final String companyName) {

		this.companyName = companyName;
	}

	public String getAddressLine1() {

		return this.addressLine1;
	}

	public void setAddressLine1(final String addressLine1) {

		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {

		return this.addressLine2;
	}

	public void setAddressLine2(final String addressLine2) {

		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {

		return this.addressLine3;
	}

	public void setAddressLine3(final String addressLine3) {

		this.addressLine3 = addressLine3;
	}

	public String getCity() {

		return this.city;
	}

	public void setCity(final String city) {

		this.city = city;
	}

	public String getPostalCode() {

		return this.postalCode;
	}

	public void setPostalCode(final String postalCode) {

		this.postalCode = postalCode;
	}

	//
	// @Column(name = "ZIP_FOUR")
	// protected String zipFour;
	//
	//
	// @Column(name = "ISO_COUNTRY_SUB")
	// protected String isoCountrySubdivision;
	//
	// @Column(name = "SUB_STATE_PROV_REG")
	// protected String stateProvinceRegion;
	//
	// @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Country.class)
	// @PrimaryKeyJoinColumn(name = "ID")
	// protected Country isoCountryAlpha2;
	// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @PrimaryKeyJoinColumn(name = "ID")
	// protected Phone phonePrimary;
	//
	// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @PrimaryKeyJoinColumn(name = "ID")
	// protected Phone phoneSecondary;
	//
	// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @PrimaryKeyJoinColumn(name = "ID")
	// protected Phone phoneFax;
	//
	// @Column(name = "IS_DEFAULT")
	// protected boolean isDefault = false;
	//
	// @Column(name = "IS_ACTIVE")
	// protected boolean isActive = true;
	//
	// @Column(name = "IS_BUSINESS")
	// protected boolean isBusiness = false;
	//
	// // @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity =
	// // CountryImpl.class)
	// // @JoinColumn(name = "COUNTRY")
	// // @Index(name="ADDRESS_COUNTRY_INDEX", columnNames={"COUNTRY"})
	// // @AdminPresentation(friendlyName = "CountryImpl_Country", order=100, group =
	// // "AddressImpl_Address", prominent = true, translatable = true)
	// // @Deprecated
	// // protected Country country;
	//
	// /**
	// * Intended to be used to differentiate whether this address is a physical address (e.g.
	// street/house) or a
	// mailing
	// * address (e.g. P.O. Box etc..)
	// */
	// @Column(name = "IS_STREET")
	// protected boolean isStreet = false;
	//
	// /**
	// * Intended to be used to differentiate whether this address is a physical address (e.g.
	// street/house) or a
	// mailing
	// * address (e.g. P.O. Box etc..)
	// */
	// @Column(name = "IS_MAILING")
	// protected boolean isMailing = false;
	//
	// /**
	// * This is intented to be used for address verification integrations and should not be modifiable
	// in the admin
	// */
	// @Column(name = "TOKENIZED_ADDRESS")
	// protected String tokenizedAddress;
	//
	// /**
	// * This is intented to be used for address verification integrations and should not be modifiable
	// in the admin
	// */
	// @Column(name = "STANDARDIZED")
	// protected Boolean standardized = Boolean.FALSE;
	//
	// /**
	// * This is intented to be used for address verification integrations and should not be modifiable
	// in the admin
	// */
	// @Column(name = "VERIFICATION_LEVEL")
	// protected String verificationLevel;
	//
	// @Column(name = "PRIMARY_PHONE")
	// @Deprecated
	// protected String primaryPhone;
	//
	// @Column(name = "SECONDARY_PHONE")
	// @Deprecated
	// protected String secondaryPhone;
	//
	// @Column(name = "FAX")
	// @Deprecated
	// protected String fax;
	//
	// public String getMiddleName() {
	//
	// return this.middleName;
	// }
	//
	// public void setMiddleName(String middleName) {
	//
	// this.middleName = middleName;
	// }
	//
}

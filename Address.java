package data.mapping.location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.DatabaseChangeNotificationType;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.TimeOfDay;
import org.eclipse.persistence.config.CacheIsolationType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@Table(name = "Address")
// @EnableJpaAuditing
// @EntityListeners(AuditingEntityListener.class)
// @EqualsAndHashCode(callSuper=true)
@Cache(type = CacheType.SOFT_WEAK, expiryTimeOfDay = @TimeOfDay(hour = 1), disableHits = false, isolation = CacheIsolationType.ISOLATED, alwaysRefresh = true, refreshOnlyIfNewer = true, databaseChangeNotificationType = DatabaseChangeNotificationType.INVALIDATE, coordinationType = CacheCoordinationType.SEND_NEW_OBJECTS_WITH_CHANGES)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// @XmlType( propOrder = { "id"} )
@Index(name = "ADD_COUNTRY_INDEX", columnNames = { "Country", "City" })
public class Address implements Serializable { // XmlAdapter<Address, Address>

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	@XmlAttribute
	private Long id;

	// @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Person.class)
	// @JoinColumn(nullable = false, name = "PERSON_ID")
	// @PrimaryKeyJoinColumn(name="ID", referencedColumnName="PERSON_ID")
	// @PrimaryKeyJoinColumn(name="ID", referencedColumnName="PERSON_ID")
	// private Person person;

	// @XmlInverseReference(mappedBy="person")
	@XmlTransient
	private Map<String, Address> entityMap = new HashMap<String, Address>();

	@XmlTransient
	private List<Address> entityList = new ArrayList<>();

	@Column(name = "AddressTYpe", length = 4, nullable = false)
	@Enumerated(EnumType.STRING)
	@XmlElement
	private AddressType addressType;

	@Column(name = "Country", length = 50, nullable = false)
	@XmlElement
	private String country;

	@Column(name = "City", length = 50, nullable = false)
	@XmlElement
	private String city;

	@Column(name = "Street", length = 50, nullable = false)
	@XmlElement
	private String street;

	@Column(name = "BuildNo", length = 50, nullable = true)
	@XmlElement
	private String buildNumber;

	@Column(name = "FlatNo", length = 50, nullable = true)
	@XmlElement
	private String flatNumber;

	// województwo
	@Column(name = "Province", length = 50, nullable = true)
	@XmlElement
	private String province;

	// powiat
	@Column(name = "County", length = 50, nullable = true)
	@XmlElement
	private String county;

	// gmina
	@Column(name = "District", length = 50, nullable = true)
	@XmlElement
	private String district;

	@Column(name = "ZipCode", length = 50, nullable = true)
	@XmlElement
	private String zipCode;

	@Column(name = "PostName", length = 50, nullable = true)
	@XmlElement
	private String postName;

	@Column(name = "PostBox", length = 50, nullable = true)
	@XmlElement
	private String postBox;

	public Long getId() {

		return this.id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public AddressType getAddressType() {

		return this.addressType;
	}

	public void setAddressType(final AddressType addressType) {

		this.addressType = addressType;
	}

	public String getCountry() {

		return this.country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

	public String getCity() {

		return this.city;
	}

	public void setCity(final String city) {

		this.city = city;
	}

	public String getStreet() {

		return this.street;
	}

	public void setStreet(final String street) {

		this.street = street;
	}

	public String getBuildNumber() {

		return this.buildNumber;
	}

	public void setBuildNumber(final String buildNumber) {

		this.buildNumber = buildNumber;
	}

	public String getFlatNumber() {

		return this.flatNumber;
	}

	public void setFlatNumber(final String flatNumber) {

		this.flatNumber = flatNumber;
	}

	public String getProvince() {

		return this.province;
	}

	public void setProvince(final String province) {

		this.province = province;
	}

	public String getCounty() {

		return this.county;
	}

	public void setCounty(final String county) {

		this.county = county;
	}

	public String getDistrict() {

		return this.district;
	}

	public void setDistrict(final String district) {

		this.district = district;
	}

	public String getZipCode() {

		return this.zipCode;
	}

	public void setZipCode(final String zipCode) {

		this.zipCode = zipCode;
	}

	public String getPostName() {

		return this.postName;
	}

	public void setPostName(final String postName) {

		this.postName = postName;
	}

	public String getPostBox() {

		return this.postBox;
	}

	public void setPostBox(final String postBox) {

		this.postBox = postBox;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.addressType == null) ? 0 : this.addressType.hashCode());
		result = (prime * result) + ((this.buildNumber == null) ? 0 : this.buildNumber.hashCode());
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.county == null) ? 0 : this.county.hashCode());
		result = (prime * result) + ((this.district == null) ? 0 : this.district.hashCode());
		result = (prime * result) + ((this.flatNumber == null) ? 0 : this.flatNumber.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.postBox == null) ? 0 : this.postBox.hashCode());
		result = (prime * result) + ((this.postName == null) ? 0 : this.postName.hashCode());
		result = (prime * result) + ((this.province == null) ? 0 : this.province.hashCode());
		result = (prime * result) + ((this.street == null) ? 0 : this.street.hashCode());
		result = (prime * result) + ((this.zipCode == null) ? 0 : this.zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Address other = (Address) obj;
		if (this.addressType == null) {
			if (other.addressType != null)
				return false;
		} else if (!this.addressType.equals(other.addressType))
			return false;
		if (this.buildNumber == null) {
			if (other.buildNumber != null)
				return false;
		} else if (!this.buildNumber.equals(other.buildNumber))
			return false;
		if (this.city == null) {
			if (other.city != null)
				return false;
		} else if (!this.city.equals(other.city))
			return false;
		if (this.country == null) {
			if (other.country != null)
				return false;
		} else if (!this.country.equals(other.country))
			return false;
		if (this.county == null) {
			if (other.county != null)
				return false;
		} else if (!this.county.equals(other.county))
			return false;
		if (this.district == null) {
			if (other.district != null)
				return false;
		} else if (!this.district.equals(other.district))
			return false;
		if (this.flatNumber == null) {
			if (other.flatNumber != null)
				return false;
		} else if (!this.flatNumber.equals(other.flatNumber))
			return false;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		if (this.postBox == null) {
			if (other.postBox != null)
				return false;
		} else if (!this.postBox.equals(other.postBox))
			return false;
		if (this.postName == null) {
			if (other.postName != null)
				return false;
		} else if (!this.postName.equals(other.postName))
			return false;
		if (this.province == null) {
			if (other.province != null)
				return false;
		} else if (!this.province.equals(other.province))
			return false;
		if (this.street == null) {
			if (other.street != null)
				return false;
		} else if (!this.street.equals(other.street))
			return false;
		if (this.zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!this.zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Address [id=" + this.id + ", addressType=" + this.addressType + ", country=" + this.country + ", city="
				+ this.city + ", street=" + this.street + ", buildNumber=" + this.buildNumber + ", flatNumber="
				+ this.flatNumber + ", province=" + this.province + ", county=" + this.county + ", district="
				+ this.district + ", zipCode=" + this.zipCode + ", postName=" + this.postName + ", postBox="
				+ this.postBox + "]";
	}

	// @Override
	public Address unmarshal(Address v) throws Exception {

		Address entity = entityMap.get(v.city);
		if (null == entity) {
			entity = (Address) v.clone();
			entityMap.put(entity.getCity(), entity);
		}
		return entity;
	}

	// @Override
	public Address marshal(Address v) throws Exception {

		if (v != null) {
			// Check if the entity has been marshalled before.
			// If so return only the reference.
			if (entityList.contains(v)) {
				Address adaptedEntity = new Address();
				adaptedEntity.city = v.getCity();
				return adaptedEntity;
			} else {
				Address adaptedEntity = (Address) v.clone();
				entityList.add(v);
				return adaptedEntity;
			}
		} else {
			return null;
		}
	}

}

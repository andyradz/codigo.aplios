package data.mapping.customer;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import data.mapping.user.Caption;

//@AssociationOverrides({
//    @AssociationOverride(name = "location",
//                joinColumns = @JoinColumn(name = "SUPERVISOR_LOCATION_ID"))

//...https://en.wikibooks.org/wiki/Java_Persistence/Embeddables
//overrride assocation

//many to many with additional table with mapping
//...https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany

@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "Customer")

//@XmlJoinNodes( {
//    @XmlJoinNode(xmlPath="contact/id/text()", referencedXmlPath="id/text()"),
//    @XmlJoinNode(xmlPath="contact/country/text()", referencedXmlPath="country/text()")
//})
public abstract class Customer {

	@Id
	@Column(name = "Id")
	private Long id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "caption", column = @Column(name = "OfficialTitle", length = 50)) })
	private Caption officialTitle;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "caption", column = @Column(name = "FirstName", length = 50)) })
	private Caption firstName;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "caption", column = @Column(name = "LastName", length = 50)) })
	private Caption lastName;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "caption", column = @Column(name = "FullName", length = 100)) })
	private Caption fullName;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "caption", column = @Column(name = "OfficialSalutation", length = 25)) })
	private Caption officialSalutation;

	/**
	 * @return the id
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * @return the officialTitle
	 */
	public Caption getOfficialTitle() {

		return this.officialTitle;
	}

	/**
	 * @param officialTitle the officialTitle to set
	 */
	public void setOfficialTitle(Caption officialTitle) {

		this.officialTitle = officialTitle;
	}

	/**
	 * @return the firstName
	 */
	public Caption getFirstName() {

		return this.firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(Caption firstName) {

		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public Caption getLastName() {

		return this.lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(Caption lastName) {

		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public Caption getFullName() {

		return this.fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(Caption fullName) {

		this.fullName = fullName;
	}

	/**
	 * @return the officialSalutation
	 */
	public Caption getOfficialSalutation() {

		return this.officialSalutation;
	}

	/**
	 * @param officialSalutation the officialSalutation to set
	 */
	public void setOfficialSalutation(Caption officialSalutation) {

		this.officialSalutation = officialSalutation;
	}

}
package com.codigo.aplios.domain.model.customers;

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

//@AssociationOverrides({
//  @AssociationOverride(name = "location",
//              joinColumns = @JoinColumn(name = "SUPERVISOR_LOCATION_ID"))

//...https://en.wikibooks.org/wiki/Java_Persistence/Embeddables
//overrride assocation

//many to many with additional table with mapping
//...https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany

@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Customer")
public class Customer {

	@Id
	@Column(name = "Id")
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "fullName", column = @Column(name = "OfficialTitle", length = 50)) })
	private CutomerName officialTitle;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "fullName", column = @Column(name = "FirstName", length = 50)) })
	private CutomerName firstName;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "fullName", column = @Column(name = "LastName", length = 50)) })
	private CutomerName lastName;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "fullName", column = @Column(name = "FullName", length = 100)) })
	private CutomerName fullName;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "fullName", column = @Column(name = "OfficialSalutation", length = 25)) })
	private CutomerName officialSalutation;

	/**
	 * @return the id
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * @param id
	 *        the id to set
	 */
	public void setId(final Long id) {

		this.id = id;
	}

	/**
	 * @return the officialTitle
	 */
	public CutomerName getOfficialTitle() {

		return this.officialTitle;
	}

	/**
	 * @param officialTitle
	 *        the officialTitle to set
	 */
	public void setOfficialTitle(final CutomerName officialTitle) {

		this.officialTitle = officialTitle;
	}

	/**
	 * @return the firstName
	 */
	public CutomerName getFirstName() {

		return this.firstName;
	}

	/**
	 * @param firstName
	 *        the firstName to set
	 */
	public void setFirstName(final CutomerName firstName) {

		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public CutomerName getLastName() {

		return this.lastName;
	}

	/**
	 * @param lastName
	 *        the lastName to set
	 */
	public void setLastName(final CutomerName lastName) {

		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public CutomerName getFullName() {

		return this.fullName;
	}

	/**
	 * @param fullName
	 *        the fullName to set
	 */
	public void setFullName(final CutomerName fullName) {

		this.fullName = fullName;
	}

	/**
	 * @return the officialSalutation
	 */
	public CutomerName getOfficialSalutation() {

		return this.officialSalutation;
	}

	/**
	 * @param officialSalutation
	 *        the officialSalutation to set
	 */
	public void setOfficialSalutation(final CutomerName officialSalutation) {

		this.officialSalutation = officialSalutation;
	}

}

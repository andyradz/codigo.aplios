package com.codigo.aplios.domain.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.codigo.aplios.domain.model.common.EntityModel;

@Entity
@Table(name = "ZipCodeShortcut")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ZipCodeShortcut extends EntityModel implements Serializable {

	private static final long serialVersionUID = -4630230067427684663L;

	@Column(name = "Code", nullable = false, length = 10)
	@XmlElement
	private String code;

	@Column(name = "Name", nullable = false, length = 50)
	@XmlElement
	private String name;

	@Column(name = "Description", nullable = false, length = 255)
	@XmlElement
	private String description;

	/**
	 * @return the code
	 */
	public String getCode() {

		return this.code;
	}

	/**
	 * @param code
	 *        the code to set
	 */
	public void setCode(final String code) {

		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * @param name
	 *        the name to set
	 */
	public void setName(final String name) {

		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return this.description;
	}

	/**
	 * @param description
	 *        the description to set
	 */
	public void setDescription(final String description) {

		this.description = description;
	}

}

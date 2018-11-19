package com.codigo.aplios.domain.model.common;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Identity implements Serializable {

	// -----------------------------------------------------------------------------------------------------------------

	private static final long serialVersionUID = -8677730113148545468L;

	// -----------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// -----------------------------------------------------------------------------------------------------------------

	public Integer getId() {

		return this.id;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public void setId(final int id) {

		this.id = Integer.valueOf(id);
	}

	// -----------------------------------------------------------------------------------------------------------------
}

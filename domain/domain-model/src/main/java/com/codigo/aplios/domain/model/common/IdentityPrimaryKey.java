package com.codigo.aplios.domain.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

@Embeddable
public class IdentityPrimaryKey implements Serializable {

	private static final long serialVersionUID = -1528223225651711166L;

	// @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ColumnPosition(position = 0)
	private Integer id;

	public Integer getKeyId() {

		return this.id;
	}

	@Override
	public int hashCode() {

		return super.hashCode(); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean equals(final Object obj) {

		return super.equals(obj); // To change body of generated methods, choose Tools | Templates.
	}

}

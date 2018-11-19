package com.codigo.aplios.domain.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntityModel implements Serializable {

	/**
	 * Atrybut klasy określa unikalny globalny identyfikator używany w procesie serializacji i
	 * deserializacji
	 */
	private static final long serialVersionUID = -8677730113148545468L;

	/**
	 * Atrybut obiektu określa unikalny identyfikator encji w zbiorze danych
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, updatable = false)
	@ColumnPosition(position = 0)
	protected Integer id;

	/**
	 * Właściwość pobiera identyfikator encji w zbiorze danych
	 *
	 * @return Wartość numeryczna, identyfikator encji
	 */
	public Integer getId() {

		return this.id;
	}

}

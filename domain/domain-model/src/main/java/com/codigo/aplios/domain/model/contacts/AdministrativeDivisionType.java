package com.codigo.aplios.domain.model.contacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//jednostka podziału administracyjnego

@Entity
@Table(name = "AdministrativeDivisionType")
public class AdministrativeDivisionType {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Code", length = 10, nullable = false, unique = true)
	private String Code;

	@Column(name = "Name", length = 50, nullable = false, unique = false)
	private String name;

}

package com.codigo.aplios.domain.model.customers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.locale.Dictionary;

@Entity
@Table(name = "CustomerRole")
@Customizer(EntityColumnPositionCustomizer.class)
public class CustomerRole extends Dictionary {

	private static final long serialVersionUID = 1801574957344616084L;

	@Column(name = "Name", nullable = false)
	@ColumnPosition(position = 1)
	private String name;

	@Column(name = "FreeShipping", nullable = false)
	@ColumnPosition(position = 2)
	private boolean freeShipping;

	@Column(name = "TaxExempt", nullable = false)
	@ColumnPosition(position = 3)
	private boolean taxExempt;

	@ColumnPosition(position = 4)
	@Column(name = "TaxDisplayType", nullable = true)
	private int taxDisplayType;

	@ColumnPosition(position = 5)
	@Column(name = "isSystemRole", nullable = true)
	private boolean isSystemRole;

	@ColumnPosition(position = 6)
	@Column(name = "SystemName", nullable = true)
	private String systemName;

}

package com.codigo.aplios.domain.model.catalog;

import javax.persistence.Column;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.locale.Dictionary;

//@Entity
@Table(name = "Category")
// , uniqueConstraints = {
// @UniqueConstraint(columnNames =
// {
// "",
// "" }) })
@Customizer(EntityColumnPositionCustomizer.class)
public class Category extends Dictionary { // sownik

	private static final long serialVersionUID = 5195542005041435577L;

	@ColumnPosition(position = 1)
	@Column(name = "Name", nullable = false, unique = true, length = 20)
	private String name;

	@ColumnPosition(position = 2)
	@Column(name = "Alias", nullable = false, unique = true, length = 20)
	private String alias;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}

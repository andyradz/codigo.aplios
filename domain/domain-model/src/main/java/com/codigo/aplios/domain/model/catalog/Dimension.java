package com.codigo.aplios.domain.model.catalog;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

@Embeddable
public class Dimension implements Serializable {

	private static final long serialVersionUID = 1L;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "WIDTH") // columnDefinition = "DECIMAL(3,5)", precision = 5, scale = 3)
	@ColumnPosition(position = 2)
	protected BigDecimal width;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "HEIGHT")
	@ColumnPosition(position = 1)
	protected BigDecimal height;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "DEPTH")
	@ColumnPosition(position = 3)
	protected BigDecimal depth;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "GIRTH")
	@ColumnPosition(position = 4)
	protected BigDecimal girth;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "CONTAINER_SIZE")
	@ColumnPosition(position = 5)
	protected String size;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "CONTAINER_SHAPE")
	@ColumnPosition(position = 6)
	protected String container;

	@Digits(integer = 3, fraction = 5)
	@Column(name = "DIMENSION_UNIT_OF_MEASURE")
	@ColumnPosition(position = 7)
	protected String dimensionUnitOfMeasure;

	public BigDecimal getWidth() {

		return this.width;
	}

	public void setWidth(final BigDecimal width) {

		this.width = width;
	}

	public BigDecimal getHeight() {

		return this.height;
	}

	public void setHeight(final BigDecimal height) {

		this.height = height;
	}

	public BigDecimal getDepth() {

		return this.depth;
	}

	public void setDepth(final BigDecimal depth) {

		this.depth = depth;
	}

	/**
	 * Returns the product dimensions as a String (assumes measurements are in inches)
	 *
	 * @return a String value of the product dimensions
	 */
	public String getDimensionString() {

		return this.height + "Hx" + this.width + "Wx" + this.depth + "D\"";
	}

	public BigDecimal getGirth() {

		return this.girth;
	}

	public void setGirth(final BigDecimal girth) {

		this.girth = girth;
	}

	// public ContainerSizeType getSize() {
	//
	// return ContainerSizeType.getInstance(size);
	// }
	//
	// public void setSize(final ContainerSizeType size) {
	//
	// if (size != null) {
	// this.size = size.getType();
	// }
	// }
	//
	// public ContainerShapeType getContainer() {
	//
	// return ContainerShapeType.getInstance(container);
	// }
	//
	// public void setContainer(final ContainerShapeType container) {
	//
	// if (container != null) {
	// this.container = container.getType();
	// }
	// }
	//
	// public DimensionUnitOfMeasureType getDimensionUnitOfMeasure() {
	//
	// return DimensionUnitOfMeasureType.getInstance(dimensionUnitOfMeasure);
	// }
	//
	// public void setDimensionUnitOfMeasure(final DimensionUnitOfMeasureType
	// dimensionUnitOfMeasure) {
	//
	// if (dimensionUnitOfMeasure != null) {
	// this.dimensionUnitOfMeasure = dimensionUnitOfMeasure.getType();
	// }
	// }
	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!this.getClass()
				.isAssignableFrom(o.getClass()))
			return false;

		final Dimension dimension = (Dimension) o;

		if (this.container != null ? !this.container.equals(dimension.container) : dimension.container != null)
			return false;
		if (this.depth != null ? !this.depth.equals(dimension.depth) : dimension.depth != null)
			return false;
		if (this.dimensionUnitOfMeasure != null ? !this.dimensionUnitOfMeasure.equals(dimension.dimensionUnitOfMeasure)
				: dimension.dimensionUnitOfMeasure != null)
			return false;
		if (this.girth != null ? !this.girth.equals(dimension.girth) : dimension.girth != null)
			return false;
		if (this.height != null ? !this.height.equals(dimension.height) : dimension.height != null)
			return false;
		if (this.size != null ? !this.size.equals(dimension.size) : dimension.size != null)
			return false;
		if (this.width != null ? !this.width.equals(dimension.width) : dimension.width != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {

		int result = this.width != null ? this.width.hashCode() : 0;
		result = (31 * result) + (this.height != null ? this.height.hashCode() : 0);
		result = (31 * result) + (this.depth != null ? this.depth.hashCode() : 0);
		result = (31 * result) + (this.girth != null ? this.girth.hashCode() : 0);
		result = (31 * result) + (this.size != null ? this.size.hashCode() : 0);
		result = (31 * result) + (this.container != null ? this.container.hashCode() : 0);
		result = (31 * result) + (this.dimensionUnitOfMeasure != null ? this.dimensionUnitOfMeasure.hashCode() : 0);
		return result;
	}
}

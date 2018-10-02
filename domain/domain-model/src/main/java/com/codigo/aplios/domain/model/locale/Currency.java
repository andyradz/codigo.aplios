package com.codigo.aplios.domain.model.locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;

@Entity
@Table(name = "Currency")
@Customizer(EntityColumnPositionCustomizer.class)
public class Currency extends Dictionary {

	private static final long serialVersionUID = -7381025110902179653L;

	@ColumnPosition(position = 1)
	@Column(name = "Name", nullable = false, unique = true, length = 50)
	private String name;

	@ColumnPosition(position = 2)
	@Column(name = "CurrencyCode", nullable = false, unique = true, length = 3)
	private String currencyCode;

	@ColumnPosition(position = 3)
	@Column(name = "Rate", columnDefinition = "decimal(18,8)", nullable = false)
	private double rate;

	@ColumnPosition(position = 4)
	@Column(name = "DisplayLocale", nullable = true, length = 50)
	private String displayLocale;

	@ColumnPosition(position = 5)
	@Column(name = "CustomFormatting", nullable = true, length = 50)
	private String customFormatting;

	@ColumnPosition(position = 6)
	@Column(name = "LimitedToStores", nullable = false)
	private boolean limitedToStores = false;

	@ColumnPosition(position = 7)
	@Column(name = "DomainEndings", nullable = true, length = 1000)
	private String domainEndings;

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getCurrencyCode() {

		return this.currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {

		this.currencyCode = currencyCode;
	}

	public double getRate() {

		return this.rate;
	}

	public void setRate(final double rate) {

		this.rate = rate;
	}

	public String getDisplayLocale() {

		return this.displayLocale;
	}

	public void setDisplayLocale(final String displayLocale) {

		this.displayLocale = displayLocale;
	}

	public String getCustomFormatting() {

		return this.customFormatting;
	}

	public void setCustomFormatting(final String customFormatting) {

		this.customFormatting = customFormatting;
	}

	public boolean isLimitedToStores() {

		return this.limitedToStores;
	}

	public void setLimitedToStores(final boolean limitedToStores) {

		this.limitedToStores = limitedToStores;
	}

	public String getDomainEndings() {

		return this.domainEndings;
	}

	public void setDomainEndings(final String domainEndings) {

		this.domainEndings = domainEndings;
	}

	@Override
	public String toString() {

		return this.currencyCode + ';' + this.name + ';' + this.displayLocale + ';';
	}

}

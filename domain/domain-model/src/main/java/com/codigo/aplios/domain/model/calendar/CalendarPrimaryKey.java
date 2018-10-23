package com.codigo.aplios.domain.model.calendar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
// @Access (AccessType.FIELD)
public class CalendarPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1294115891003950983L;

	@Column(name = "YearNumber", nullable = false)
	private int yearNumber;

	@Column(name = "MonthNumber", nullable = false)
	private int monthNumber;

	@Column(name = "DayNumber", nullable = false)
	private int dayNumber;

	public CalendarPrimaryKey() {

	}

	public CalendarPrimaryKey(final int yearNumber, final int monthNumber, final int dayNumber) {

		this.yearNumber = yearNumber;
		this.monthNumber = monthNumber;
		this.dayNumber = dayNumber;
	}

	public int getYearNumber() {

		return this.yearNumber;
	}

	public void setYearNumber(final int yearNumber) {

		this.yearNumber = yearNumber;
	}

	public int getMonthNumber() {

		return this.monthNumber;
	}

	public void setMonthNumber(final int monthNumber) {

		this.monthNumber = monthNumber;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.monthNumber;
		result = (prime * result) + this.yearNumber;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CalendarPrimaryKey other = (CalendarPrimaryKey) obj;
		if (this.monthNumber != other.monthNumber)
			return false;
		if (this.yearNumber != other.yearNumber)
			return false;
		return true;
	}

	public int getDayNumber() {

		return this.dayNumber;
	}

	public void setDayNumber(final int dayNumber) {

		this.dayNumber = dayNumber;
	}
}

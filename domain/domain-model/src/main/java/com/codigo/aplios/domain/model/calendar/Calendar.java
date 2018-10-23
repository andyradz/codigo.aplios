package com.codigo.aplios.domain.model.calendar;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Calendar")
public class Calendar implements Serializable {

	private static final long serialVersionUID = 7149137451110949342L;

	@EmbeddedId
	private CalendarPrimaryKey calendarPrimaryKey;

	@Column(name = "Name", length = 50, nullable = false, unique = true)
	private String name;

	public Calendar() {

	}

	public Calendar(final CalendarPrimaryKey calendarPrimaryKey) {

		this.calendarPrimaryKey = calendarPrimaryKey;
	}

	@OneToOne(optional = false, mappedBy = "calendar", cascade = CascadeType.PERSIST)
	private CalendarDay calendarDay;

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public CalendarDay getCalendarDay() {

		return this.calendarDay;
	}

	public void setCalendarDay(final CalendarDay calendarDay) {

		this.calendarDay = calendarDay;
	}

	@Override
	public String toString() {

		return "Calendar [calendarPrimaryKey=" + this.calendarPrimaryKey + ", name=" + this.name + ", calendarDay="
				+ this.calendarDay + "]";
	}
}

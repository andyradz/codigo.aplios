package com.codigo.aplios.domain.model.calendar;

import java.io.Serializable;
import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

//@Entity
//@Table(name = "author")
//@SecondaryTable(name = "author_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "authorId", referencedColumnName = "id"))
//public class Author { ... }

@Entity
@Table(name = "CalandarDay")
public class CalendarDay implements Serializable {

	private static final long serialVersionUID = -6578023875242659197L;

	@EmbeddedId
	// @AttributeOverrides({ @AttributeOverride(name = "yearNumber", column = @Column(name =
	// "yearNumber")),
	// @AttributeOverride(name = "monthNumber", column = @Column(name = "monthNumber")) })
	private CalendarPrimaryKey calendarPrimaryKey;

	@Column(name = "OrderNumber", nullable = true)
	private Long orderNumber;

	@Column(name = "FirstDayInMonth", nullable = true)
	private Boolean firstDayInMonth;

	@Column(name = "LastDayInMonth", nullable = true)
	private Boolean lastDayInMonth;

	@Column(name = "BeginDayOfYear", nullable = true)
	private Boolean beginDayOfYear;

	@Column(name = "EndDayOfYear", nullable = true)
	private Boolean endDayOfYear;

	@Column(name = "DayOfWeek", nullable = true)
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayName;

	@Column(name = "DayNumberInWeek", nullable = true)
	private Integer dayNumberInWeek;

	@Column(name = "DayNumberInWeekend", nullable = true)
	private Integer dayNumberInWeekend;

	@Column(name = "DayNumberInMonth", nullable = true)
	private Integer dayNumberInMonth;

	@Column(name = "DayNumberInQuoter", nullable = true)
	private Integer dayNumberInQuoter;

	@Column(name = "DayNumberInYear", nullable = true)
	private Integer dayNumberInYear;

	@Column(name = "DayNumberInQaliYear", nullable = true)
	private Integer dayNumberInQaliYear;

	@OneToOne
	@PrimaryKeyJoinColumns({
			@PrimaryKeyJoinColumn(name = "yearNumber", referencedColumnName = "monthNumber"),
			@PrimaryKeyJoinColumn(name = "yearNumber", referencedColumnName = "monthNumber") })
	private Calendar calendar;

	public CalendarDay() {

	}

	public CalendarDay(final int yearNumber, final int monthNumber, final int dayNumber) {

		this.calendarPrimaryKey = new CalendarPrimaryKey(
			yearNumber, monthNumber, dayNumber);
	}

	public CalendarDay(final CalendarPrimaryKey calendarPrimaryKey) {

		this.calendarPrimaryKey = calendarPrimaryKey;
	}

	public DayOfWeek getDayName() {

		return this.dayName;
	}

	public void setDayName(final DayOfWeek dayName) {

		this.dayName = dayName;
	}

	public Integer getDayNumberInWeek() {

		return this.dayNumberInWeek;
	}

	public void setDayNumberInWeek(final Integer dayNumberInWeek) {

		this.dayNumberInWeek = dayNumberInWeek;
	}

	public Integer getDayNumberInWeekend() {

		return this.dayNumberInWeekend;
	}

	public void setDayNumberInWeekend(final Integer dayNumberInWeekend) {

		this.dayNumberInWeekend = dayNumberInWeekend;
	}

	public Integer getDayNumberInMonth() {

		return this.dayNumberInMonth;
	}

	public void setDayNumberInMonth(final Integer dayNumberInMonth) {

		this.dayNumberInMonth = dayNumberInMonth;
	}

	public Integer getDayNumberInQuoter() {

		return this.dayNumberInQuoter;
	}

	public void setDayNumberInQuoter(final Integer dayNumberInQuoter) {

		this.dayNumberInQuoter = dayNumberInQuoter;
	}

	public Integer getDayNumberInQaliYear() {

		return this.dayNumberInQaliYear;
	}

	public void setDayNumberInQaliYear(final Integer dayNumberInQaliYear) {

		this.dayNumberInQaliYear = dayNumberInQaliYear;
	}

	public Calendar getCalendar() {

		return this.calendar;
	}

	public void setCalendar(final Calendar calendar) {

		this.calendar = calendar;
	}

	public Boolean getFirstDayInMonth() {

		return this.firstDayInMonth;
	}

	public void setFirstDayInMonth(final Boolean firstDayInMonth) {

		this.firstDayInMonth = firstDayInMonth;
	}

	public Boolean getLastDayInMonth() {

		return this.lastDayInMonth;
	}

	public void setLastDayInMonth(final Boolean lastDayInMonth) {

		this.lastDayInMonth = lastDayInMonth;
	}

}

package com.codigo.aplios.repository.core.convert;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

/*
 * By setting autoApply=true, the converter will be applied to all attributes of the EntityType and no changes on the
 * entity are required.
 */
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(final LocalDate locDate) {

		return (locDate == null ? null : Date.valueOf(locDate));
	}

	@Override
	public LocalDate convertToEntityAttribute(final Date sqlDate) {

		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

}

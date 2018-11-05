package com.codigo.aplios.repository.core.convert;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/*
 * By setting autoApply=true, the converter will be applied to all attributes of the EntityType and no changes on the
 * entity are required.
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(final LocalDateTime locDateTime) {

		return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(final Timestamp sqlTimestamp) {

		return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
	}
}

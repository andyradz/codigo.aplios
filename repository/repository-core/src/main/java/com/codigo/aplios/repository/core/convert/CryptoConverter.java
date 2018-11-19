package com.codigo.aplios.repository.core.convert;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(final String ccNumber) {

		try {
			final byte[] encodedBytes = Base64.getDecoder()
					.decode(ccNumber.getBytes("UTF-8"));

			return new String(
				encodedBytes);
		}
		catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(
				e);
		}
	}

	@Override
	public String convertToEntityAttribute(final String dbData) {

		try {

			final byte[] encodedBytes = Base64.getEncoder()
					.encode(dbData.getBytes("UTF-8"));
			return new String(
				encodedBytes);
		}
		catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(
				e);
		}
	}

}

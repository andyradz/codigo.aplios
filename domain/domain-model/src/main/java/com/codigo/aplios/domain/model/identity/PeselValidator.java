package com.codigo.aplios.domain.model.identity;

import java.util.Map;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.eclipse.collections.impl.factory.Maps;

/**
 * Klasa realizuje mechanizm validacji numeru Pesel. Implementuje domyślny model validacji standardu
 * JavaBeans.
 *
 * @author Andrzej radziszewski
 * @param <PeselIdentity>
 * @since 1.0
 * @category Validator
 */
public class PeselValidator implements ConstraintValidator<PeselCheck, PeselIdentity> {

	private final byte[] peselIdentity;

	/**
	 * Podstawowy konstruktor obiektu.
	 *
	 * @param peselIdentity
	 *        numer identyfikacyjny regon.
	 * @throws Exception
	 *         powstaje ry nieprawidłowy numer identyfikacji regon.
	 */
	public PeselValidator(final PeselIdentity peselIdentity) throws IllegalArgumentException {

		Objects.requireNonNull(peselIdentity);

		if (peselIdentity.pesel()
				.length() != 11)
			throw new IllegalArgumentException();

		this.peselIdentity = new byte[11];

		int arrIndex = 0;
		for (final String item : peselIdentity.pesel()
				.split(""))
			this.peselIdentity[arrIndex++] = Byte.parseByte(item);
	}

	@Override
	public void initialize(final PeselCheck constraintAnnotation) {

		final Map<String, Object> elements = Maps.mutable.empty();

		elements.put("message", constraintAnnotation.message());
		elements.put("payload", constraintAnnotation.payload());
		elements.put("groups", constraintAnnotation.groups());
	}

	@Override
	public boolean isValid(final PeselIdentity value, final ConstraintValidatorContext context) {

		int checksum = (+1 * this.peselIdentity[0]) + (3 * this.peselIdentity[1]) + (7 * this.peselIdentity[2])
				+ (9 * this.peselIdentity[3]) + (1 * this.peselIdentity[4]) + (3 * this.peselIdentity[5])
				+ (7 * this.peselIdentity[6]) + (9 * this.peselIdentity[7]) + (1 * this.peselIdentity[8])
				+ (3 * this.peselIdentity[9]);

		checksum %= 10;
		checksum = 10 - checksum;
		checksum %= 10;

		return (this.peselIdentity[10] == checksum);
	}
}

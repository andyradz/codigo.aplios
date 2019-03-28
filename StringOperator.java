package com.codigo.aplios.group.sdk.core.helper;

import java.util.Objects;

/**
 * Klasa realizuje wspólne operacje na klasie <code>String</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category operator
 */
public final class StringOperator {

	/**
	 * Podstawowy konstruktor obiektu klasy <code>StringOperator</code>
	 */
	private StringOperator() {

	}

	/**
	 * Metoda sprawdza czy zawartość stringa jest nieokreślona, jest pusta lub
	 * zawiera same spacje
	 *
	 * @param string Ciąg znaków poddany analizie
	 * @return Wartośc logiczna TRUE lub FALSE
	 *
	 */
	public static boolean isNullOrEmpty(final String string) {

		return (Objects.isNull(string) || string.isEmpty()
				|| string.isBlank());
	}
}

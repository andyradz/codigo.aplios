package com.codigo.aplios.sdk.core.constants;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * Klasa dostarcza wartości domyślne dla podstawowych typów klas dostępnych na platformie JVM.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category constant
 */
public final class Default {

	/**
	 * Definicja domyślnych wartości dla typów klas platforny JVM.
	 */
	private static final MutableMap<Class<?>, Object> DEFAULTS;

	/**
	 * Definicja domyślnego typu klasy Enum.
	 */
	private enum DefaultEnum {
		ZERO
	}

	/**
	 * Domyślny statyczny konstruktor obiektu klasy <code>Default</code>.
	 */
	static {
		DEFAULTS = Maps.mutable.empty();
		Default.DEFAULTS.put(Boolean.class, false);
		Default.DEFAULTS.put(boolean.class, false);
		Default.DEFAULTS.put(Character.class, '\u0000');
		Default.DEFAULTS.put(char.class, '\u0000');
		Default.DEFAULTS.put(String.class, "");
		Default.DEFAULTS.put(Byte.class, 0);
		Default.DEFAULTS.put(byte.class, 0);
		Default.DEFAULTS.put(Short.class, 0);
		Default.DEFAULTS.put(short.class, 0);
		Default.DEFAULTS.put(Integer.class, 0);
		Default.DEFAULTS.put(int.class, 0);
		Default.DEFAULTS.put(BigInteger.class, BigInteger.valueOf(0));
		Default.DEFAULTS.put(Long.class, 0);
		Default.DEFAULTS.put(long.class, 0);
		Default.DEFAULTS.put(Float.class, 0);
		Default.DEFAULTS.put(float.class, 0);
		Default.DEFAULTS.put(Double.class, 0);
		Default.DEFAULTS.put(double.class, 0);
		Default.DEFAULTS.put(BigDecimal.class, BigDecimal.valueOf(0));
		Default.DEFAULTS.put(Enum.class, Enum.valueOf(DefaultEnum.class, "ZERO"));
		Default.DEFAULTS.put(Object.class, null);
	}

	/**
	 * Metoda dostarcza domyślną wartość dla wskazanego typu obiektu klasy.
	 *
	 * @param <T>
	 *        Generyczna klasa typu
	 * @param type
	 *        Klasa typu danych
	 * @return Domyślna wartość obiektu danej klasy
	 */
	public static <T> T of(final Class<T> type) {

		return (type.isPrimitive()) ? (T) Default.DEFAULTS.get(type) : type.cast(Default.DEFAULTS.get(type));
	}

}

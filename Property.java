package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Klasa reprezentuje właściwość zarządzająca przekazaną wartością. Umożliwia
 * zapis, odczyt przekazanej wartości.
 *
 * @author andrzej.radziszewski
 *
 * @param <T> typ generyczny wlaściwości
 * @category class
 */
public final class Property<T> implements Comparable<Property<T>> {

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @return Objekt własciwości
	 */
	public static <T> Property<T> from() {

		return new Property<>();
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param value Wartość przekazywana dla właściwości
	 * @return Obiekt właściwości
	 */
	public static <T> Property<T> from(final T value) {

		return new Property<>(
				value);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Obiekt właściwości
	 */
	public static <T> Property<T> from(final boolean isReadOnly) {

		return new Property<>(
				isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param value Wartość przekazywana dla właściwości
	 * @param get   Metoda realizująca odczyt wartości
	 * @param set   Metoda realizująca zapis wartości
	 * @return Obiekt właściwości
	 */
	public static <T> Property<T> from(final T value, final UnaryOperator<T> get, final UnaryOperator<T> set) {

		return new Property<>(
				value,
				get,
				set);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param value      Wartość przekazywana dla właściwości
	 * @param get        Metoda realizująca odczyt wartości
	 * @param set        Metoda realizująca zapis wartości
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Obiekt właściwości
	 */
	public static <T> Property<T> from(final T value, final UnaryOperator<T> get, final UnaryOperator<T> set,
			final boolean isReadOnly) {

		return new Property<>(
				value,
				get,
				set,
				isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param value      Wartość przekazywana dla właściwości
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(final T value, final boolean isReadOnly) {

		return new Property<>(
				value,
				isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param value       Wartość przekazywana dla właściwości
	 * @param throwIfNull Flaga wskazuje czy generować wyjątek gdy wartośc null
	 * @param isReadOnly  Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(final T value, final boolean throwIfNull, final boolean isReadOnly) {

		return new Property<>(
				value,
				throwIfNull,
				isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 *
	 * @param property Właściwość wartości
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(final Property<T> property) {

		if (Objects.nonNull(property))
			return new Property<>(
					property);

		return new Property<>();
	}

	/**
	 * Pole określa wartość reprezentowaną przez właściwość
	 */
	private T value;

	/**
	 * Pole określa czy generować wyjątek podczas próby przypisania wartości null
	 */
	private final boolean throwIfNull;

	/**
	 * Pole określa czy wartość właściwości tylko do odczytu
	 */
	private final boolean isReadOnly;

	/**
	 * Pole określa metodę odczytu wartości
	 */
	private UnaryOperator<T> getMethod;

	/**
	 * Pole określa metodę zapisu wartości
	 */
	private UnaryOperator<T> setMethod;

	/**
	 * Podstawowy konstruktor kopiujący obiekt klasy <code>Property</code>
	 *
	 * @param property Właściwość wartości
	 */
	private Property(final Property<T> property) {

		this.value = property.value;
		this.setMethod = property.setMethod;
		this.getMethod = property.getMethod;
		this.isReadOnly = property.isReadOnly;
		this.throwIfNull = property.throwIfNull;
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value Wartość reprezentowana przez właściwość
	 * @category constructor
	 */
	private Property() {

		this(null, false, false);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy<code>Property</code>
	 *
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @category constructor
	 */
	private Property(final boolean isReadOnly) {

		this(null, isReadOnly);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value Wartość reprezentowana przez właściwość
	 * @category constructor
	 */
	private Property(final T value) {

		this(value, false, false);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value Wartość przekazywana dla właściwości
	 * @param get   Metoda realizująca odczyt wartości
	 * @param set   Metoda realizująca zapis wartości
	 * @category constructor
	 */
	private Property(final T value, final UnaryOperator<T> get, final UnaryOperator<T> set) {

		this(value, get, set, false);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value      Wartość przekazywana dla właściwości
	 * @param get        Metoda realizująca odczyt wartości
	 * @param set        Metoda realizująca zapis wartości
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @category constructor
	 */
	private Property(final T value, final UnaryOperator<T> get, final UnaryOperator<T> set, final boolean isReadOnly) {

		this(value, false, isReadOnly);
		this.setMethod = set;
		this.getMethod = get;
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value      Wartość przekazywana dla właściwości
	 * @param isReadOnly Flaga określa czy wartość własciwości tylko do odczytu
	 * @category constructor
	 */
	private Property(final T value, final boolean isReadOnly) {

		this(value, false, isReadOnly);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Property</code>
	 *
	 * @param value       Wartość przekazywana dla właściwości
	 * @param throwIfNull Flaga określająca czy generować wyjątek podczas próby
	 *                    przypisania wartości null
	 * @param isReadOnly  Flaga określa czy wartość właściwości tylko do odczytu
	 * @category constructor
	 */
	private Property(final T value, final boolean throwIfNull, final boolean isReadOnly) {

		this.getMethod = UnaryOperator.identity();
		this.setMethod = UnaryOperator.identity();
		this.throwIfNull = throwIfNull;
		this.set(value);
		this.isReadOnly = isReadOnly;
	}

	/**
	 * Metoda wykonuje sprawdzenie poprawności wartości
	 *
	 * @param value Wartość właściwości
	 */
	private void checkValue(final T value) {

		this.checkNullableValue(value);
	}

	/**
	 * Metoda wykonuje sprawdzenie czy wartość tylko to odczytu
	 */
	private void checkReadonlyValue() {

		if (this.isReadOnly)
			throw new UnsupportedOperationException(
					"Property value is readonly!");
	}

	/**
	 * Metoda wykonuje sprawdzenie wartości czy jest null
	 *
	 * @param value Wartość właściwości
	 */
	private void checkNullableValue(final T value) {

		if (this.throwIfNull && Objects.isNull(value))
			throw new IllegalArgumentException(
					"Value of property cannot be set null!");
	}

	/**
	 * Właściwość określa czy wartość własciwośći jest tylko do
	 * odczytu(niemutowalna)
	 *
	 * @return Informacja czy wartośc tylko to odczytu
	 */
	public boolean isReadOnly() {

		return this.isReadOnly;
	}

	/**
	 * Właściwość określa czy wartość własciwośći ma wartość nieokreśloną null
	 *
	 * @return Informacja czy wartość jest null
	 */
	public boolean isNull() {

		return Objects.isNull(this.value);
	}

	/**
	 * Metoda Settera ustawiająca wartość właściwości
	 *
	 * @param value Nowa wartość własciwości
	 * @category setter
	 */
	public synchronized void set(final T value) {

		this.checkReadonlyValue();
		this.checkValue(value);

		this.value = this.setMethod.apply(value);
	}

	/**
	 * Metoda Settera ustawiająca wartość właściwości
	 *
	 * @param value      Nowa wartość właściwości
	 * @param predictate Warunek określający czy ustawiać nową wartość właściwości
	 * @category setter
	 */
	public synchronized void set(final T value, final Predicate<T> predictate) {

		if (predictate.test(value))
			this.set(value);
	}

	/**
	 * Metoda Gettera pobierająca wartość właściwości
	 *
	 * @return Wartość właściwości
	 * @category getter
	 */
	public synchronized T get() {

		return this.getMethod.apply(this.value);
	}

	/**
	 * Metoda Gettera zwraca wartość włąsciwość przekonwertowaną poprzez wskazany
	 * konwerter
	 *
	 * @param converter Metoda konwersji wartości właściwości
	 * @return Wartość właściwości po konwersji
	 * @category getter
	 */
	public synchronized <R> R get(final Function<T, R> converter) {

		return (converter.apply(this.get()));
	}

	public synchronized T get(final UnaryOperator<T> operator) {

		if (!this.throwIfNull && this.isNull())
			return (operator.apply(this.get()));

		return this.value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = (prime * result) + ((this.getMethod == null)
				? 0
				: this.getMethod.hashCode());

		result = (prime * result) + (this.isReadOnly
				? 1231
				: 1237);

		result = (prime * result) + ((this.setMethod == null)
				? 0
				: this.setMethod.hashCode());

		result = (prime * result) + (this.throwIfNull
				? 1231
				: 1237);

		result = (prime * result) + ((this.value == null)
				? 0
				: this.value.hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		final Property<?> other = Property.class.cast(obj);
		if (this.getMethod == null) {
			if (other.getMethod != null)
				return false;
		} else if (!this.getMethod.equals(other.getMethod))
			return false;
		if (this.isReadOnly != other.isReadOnly)
			return false;
		if (this.setMethod == null) {
			if (other.setMethod != null)
				return false;
		} else if (!this.setMethod.equals(other.setMethod))
			return false;
		if (this.throwIfNull != other.throwIfNull)
			return false;
		if (this.value == null) {
			if (other.value != null)
				return false;
		} else if (!this.value.equals(other.value))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Property [value=" + this.value
				+ ", throwIfNull="
				+ this.throwIfNull
				+ ", isReadOnly="
				+ this.isReadOnly
				+ ", getMethod="
				+ this.getMethod
				+ ", setMethod="
				+ this.setMethod
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Property<T> property) {

		final Comparator<Property<T>> comparator = (final Property<T> a, final Property<T> b) -> {

			if (a.value == null)
				return (b == null)
						? CompareResult.EQUALS.result()
						: CompareResult.LESSER.result();

			if (b == a)
				return 1;

			final boolean result = a.equals(b);

			return result
					? CompareResult.EQUALS.result()
					: CompareResult.GREATER.result();
		};

		return comparator.compare(this, property);
	}
}

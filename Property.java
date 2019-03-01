package com.codigo.aplios.group.timeline.common.helper;

import static com.codigo.aplios.group.timeline.common.helper.CompareOperator.EQUALS;
import static com.codigo.aplios.group.timeline.common.helper.CompareOperator.EQUALSGREATERTHEN;
import static com.codigo.aplios.group.timeline.common.helper.CompareOperator.GREATERTHEN;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.codigo.aplios.group.timeline.common.range.TimeRange;

/**
 * @author dp0470
 *
 * @param <T>
 */
public final class Property<T> implements Comparable<Property<T>> {

	public static void main(String[] args) {

		Property<String> prop = Property.from("1111");
		prop.set("Andrzej");
		prop.set("Marek", value -> EQUALSGREATERTHEN.compare(value.length(), 7));
		System.out.println(prop);

		Property<String> clone = Property.from(prop);
		// prop.set("Andrzejek");

		prop.compareTo(clone);

		// CompareOperator.EQUALS.compare(prop, clone);

		Property<Integer> prop1 = Property.from(Integer.MAX_VALUE);
		prop1.set(1_000_000_000);
		prop1.set(1_000_000_001);
		System.out.println(prop1);

		Property<Object> prop2 = Property.from("black", false, false);
		prop2.set(null);
		prop2.set(null);
		System.out.println(prop2);

		Property<String> prop3 = Property.from("100");
		System.out.println(prop3);

		Property<Long> prop4 = Property.from(0L);
		Boolean val4 = prop4.get(value -> EQUALS.compare((long) (value % 2), 0L));
		prop4.set(Long.MAX_VALUE + 2L);
		System.out.println(prop4);

		Property<Double> prop5 = Property.from(110.0912121212, false, false);
		Object val5 = prop5.get();
		System.out.println(prop5);

		Property<Boolean> prop6 = Property.from(false, true, false);
		prop6.set(false);
		Object val6 = prop6.get();
		System.out.println(prop6);

		Property<String> property = Property.from("", String::toUpperCase, in -> in + "_test_", false);
		property.set("12");
		System.out.println(property);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @return Objekt własciwości
	 */
	public static <T> Property<T> from() {

		return new Property<>(null);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param value Wartość przekazywana dla właściwości
	 * @return Objekt własciwości
	 */
	public static <T> Property<T> from(T value) {

		return new Property<>(value);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param value Wartość przekazywana dla właściwości
	 * @param get   Metoda realizująca odczyt wartości
	 * @param set   Metoda realizująca zapis wartości
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(T value, UnaryOperator<T> get, UnaryOperator<T> set) {

		return new Property<>(value, get, set);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param value      Wartość przekazywana dla właściwości
	 * @param get        Metoda realizująca odczyt wartości
	 * @param set        Metoda realizująca zapis wartości
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(T value, UnaryOperator<T> get, UnaryOperator<T> set, boolean isReadOnly) {

		return new Property<>(value, get, set, isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param value      Wartość przekazywana dla właściwości
	 * @param isReadOnly Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(T value, boolean isReadOnly) {

		return new Property<>(value, isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param value       Wartość przekazywana dla właściwości
	 * @param throwIfNull Flaga wskazuje czy generować wyjątek gdy wartośc null
	 * @param isReadOnly  Flaga wskazuje czy wartość jest tylko do odczytu
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(T value, boolean throwIfNull, boolean isReadOnly) {

		return new Property<>(value, throwIfNull, isReadOnly);
	}

	/**
	 * Metoda tworzenia instancji obiektu klasy <code>Property</code>
	 * 
	 * @param property Właściwość wartości
	 * @return Objekt właściwości
	 */
	public static <T> Property<T> from(Property<T> property) {

		return new Property<>(property);
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
	private Property(Property<T> property) {

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
	private Property(T value) {

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
	private Property(T value, UnaryOperator<T> get, UnaryOperator<T> set) {

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
	private Property(T value, UnaryOperator<T> get, UnaryOperator<T> set, boolean isReadOnly) {

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
	private Property(T value, boolean isReadOnly) {

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
	private Property(T value, boolean throwIfNull, boolean isReadOnly) {

		this.getMethod = UnaryOperator.identity();
		this.setMethod = UnaryOperator.identity();
		this.throwIfNull = throwIfNull;
		set(value);
		this.isReadOnly = isReadOnly;
	}

	/**
	 * Metoda wykonuje sprawdzenie poprawności wartości
	 * 
	 * @param value Wartość właściwości
	 */
	private void checkValue(T value) {

		checkNullableValue(value);
	}

	/**
	 * Metoda wykonuje sprawdzenie czy wartość tylko to odczytu
	 */
	private void checkReadonlyValue() {

		if (this.isReadOnly)
			throw new UnsupportedOperationException("Property value is readonly!");
	}

	/**
	 * Metoda wykonuje sprawdzenie wartości czy jest null
	 * 
	 * @param value Wartość właściwości
	 */
	private void checkNullableValue(T value) {

		if (this.throwIfNull && Objects.isNull(value))
			throw new IllegalArgumentException("Value of property cannot be set null!");
	}
	
	public boolean isReadOnly() {
		return isReadOnly;
	}

	/**
	 * Metoda Settera ustawiająca wartość właściwości
	 * 
	 * @param value Nowa wartość własciwości
	 * @category setter
	 */
	public synchronized void set(T value) {

		checkReadonlyValue();
		checkValue(value);

		this.value = this.setMethod.apply(value);
	}

	/**
	 * Metoda Settera ustawiająca wartość właściwości
	 * 
	 * @param value      Nowa wartość właściwości
	 * @param predictate Warunek określający czy ustawiać nową wartość właściwości
	 * @category setter
	 */
	public synchronized void set(T value, Predicate<T> predictate) {

		if (predictate.test(value))
			set(value);
	}

	/**
	 * Metoda Gettera pobierająca wartość właściwości
	 * 
	 * @return Wartość właściwości
	 * @category getter
	 */
	public synchronized T get() {

		return this.getMethod.apply(value);
	}

	/**
	 * Metoda Gettera zwraca wartość włąsciwość przekonwertowaną poprzez wskazany
	 * konwerter
	 * 
	 * @param converter Metoda konwersji wartości właściwości
	 * @return Wartość właściwości po konwersji
	 * @category getter
	 */
	public synchronized <R> R get(Function<T, R> converter) {

		return (converter.apply(this.get()));
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

		result = prime * result + ((getMethod == null)
				? 0
				: getMethod.hashCode());

		result = prime * result + (isReadOnly
				? 1231
				: 1237);

		result = prime * result + ((setMethod == null)
				? 0
				: setMethod.hashCode());

		result = prime * result + (throwIfNull
				? 1231
				: 1237);

		result = prime * result + ((value == null)
				? 0
				: value.hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Property<T> other = Property.class.cast(obj);
		if (getMethod == null) {
			if (other.getMethod != null)
				return false;
		} else if (!getMethod.equals(other.getMethod))
			return false;
		if (isReadOnly != other.isReadOnly)
			return false;
		if (setMethod == null) {
			if (other.setMethod != null)
				return false;
		} else if (!setMethod.equals(other.setMethod))
			return false;
		if (throwIfNull != other.throwIfNull)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
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

		return "Property [value=" + value
				+ ", throwIfNull="
				+ throwIfNull
				+ ", isReadOnly="
				+ isReadOnly
				+ ", getMethod="
				+ getMethod
				+ ", setMethod="
				+ setMethod
				+ "]";
	}

	@Override
	public int compareTo(Property<T> property) {

		Comparator<Property<T>> comparator = (r, p) -> {

			boolean result = property.value.equals(this.value);
			return result
					? 0
					: 1;
		};

		Comparator.comparing((Property::get);
			

		return Objects.compare(this, property, comparator);
	}
}

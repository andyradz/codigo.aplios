package com.codigo.aplios.sdk.core.metric;

import java.util.Objects;

//TODO Procent z liczby
//TODO Liczba z procentu
//DONE: Zamiana procentu na ułamek
//DONE: Zamiana ułamka na procent
//TODO dodać jedną wspólną funkcje zaaokrągajaca
//TODO: ddowanie mnożenie dzielenie odejmowanie procentów i porównywanie
<<<<<<< HEAD
//TODO: dodać metode equals
//TODO: ddac metodę hashCode
/**
 * @author andrzej.radziszewski
 *
 */
public final class Percent {

	public static void main(final String[] args) {

		final Percent per = Percent.fromNumber(99);
		double pe = per.percentValue();
		System.out.println(per);

		pe = Percent.Operator.F1.compute(per, 99);
		System.out.println(pe);

		pe = Percent.Operator.F2.compute(per, 99);
		System.out.println(pe);
=======
public final class Percent {

	/**
	 * Metoda wyznacza procent z przekazanej wartości ułamkowej.
	 *
	 * @param number
	 *        Wartość liczbowa odpowiadającą postaci ułamkowej procentu.
	 * @return
	 */
	public static Percent fromDecimal(final double number) {

		final long val = StrictMath.round(number * Percent.PERCENT_MULTIPLER);
		return new Percent(
			val);
>>>>>>> colors
	}

	/**
	 * Metoda wyznacza procent z przekazanej liczby całkowitej.
	 *
	 * @param number
	 *        Wartość liczbowa odpowiadającą postaci całkowitej procentu.
	 * @return
	 */
	public static Percent fromNumber(final long number) {

		final long val = StrictMath.round(number * Percent.PERCENT_MULTIPLER);
		return new Percent(
			val);
	}

	public enum Operator {

		F1// liczy procent z liczby
		{// TODO: zmienić nazwę operacji
			@Override
			public double compute(final Percent percent, final double number) {

				return StrictMath.nextUp(number * percent.toDecimal());
			}
		},
		F2 // liczy liczbe z procentu
		{// TODO: zmienić nazwę operacji
			@Override
			public double compute(final Percent percent, final double number) {

				return StrictMath.nextUp(number * percent.toDecimal() * Percent.PERCENT_MULTIPLER);
			}
		};
		public abstract double compute(Percent percent, double number);
	}

	/**
	 * Wartość tekstowa określająca symbol procentu.
	 */
	private static final String PERCENT_SYMBOL = "%";

	/**
	 * Wartość liczbowa określająca podzielnik procentowy
	 */
<<<<<<< HEAD
	public static Percent fromDecimal(final double number) {

		final long val = StrictMath.round(number * Percent.PERCENT_MULTIPLER);
		return new Percent(
			val);
	}
=======
	private static final double PERCENT_DIVIDER = 1E2;
>>>>>>> colors

	/**
	 * Wartość liczbowa określająca mnożnik procentowy
	 */
	private static final double PERCENT_MULTIPLER = 1E2;

	/**
	 * Atrybut numeryczny reprezentująca wartość procentową.
	 */
<<<<<<< HEAD
	public static Percent fromNumber(final long number) {

		final long val = StrictMath.round(number * Percent.PERCENT_MULTIPLER);
		return new Percent(
			val);
	}
=======
	private final double percent;
>>>>>>> colors

	/**
	 * Atrybut numeryczny podstawy procentu.
	 */
	private final double number;

	/**
	 * Właściwość podaje wartość procentu
	 *
	 * @return Wartość numeryczna z przecinkiem.
	 */
	public double percentValue() {

		return this.percent;
	}

	/**
	 * Właściwość podaje wartość podstawy procentu
	 *
	 * @return Wartość numeryczna z przecinkiem
	 */
	public double numberValue() {

		return this.number;
	}

<<<<<<< HEAD
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda przekształca obiekt do postaci łańcucha znaków. (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.percent + " " + Percent.SYMBOL;
	}

	// -----------------------------------------------------------------------------------------------------------------

=======
>>>>>>> colors
	/**
	 * Metoda wykonuje zamianę wartości procentowej na ułamek.
	 *
	 * @return Wartość liczbowa z przecinkiem.
	 */
	public double toDecimal() {

		return (this.percent / Percent.PERCENT_DIVIDER);
	}

	/**
	 * Podstawowy konstruktor obiektu klasy. Konstruktor prywatny.
	 *
	 * @param number
	 *        Wartość numeryczna, która zamieniana jest na procent.
	 */
	private Percent(final double number) {

		this.number = number;
		this.percent = evalute();
	}

	/**
	 * Metoda wykonuje przeliczenie wartości numerycznej na reprezentacje procentową.
	 *
	 * @return Wartość numeryczna z przecinkiem.
	 */
	private double evalute() {

		return (this.number / Percent.PERCENT_DIVIDER);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.number, this.percent);
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

		if (!(obj instanceof Percent))
			return false;

		final Percent other = (Percent) obj;
		return (Double.doubleToLongBits(this.number) == Double.doubleToLongBits(other.number))
				&& (Double.doubleToLongBits(this.percent) == Double.doubleToLongBits(other.percent));
	}

	/**
	 * Metoda przekształca obiekt do postaci łańcucha znaków. (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.percent + " " + Percent.PERCENT_SYMBOL;
	}
}

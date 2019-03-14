package com.codigo.aplios.group.timeline.common.helper;

/**
 * Typ wyliczeniowy reprezentuje wynik operacji porówania wyrażony w postaci
 * znaczników. Poszczególne znacczniki reprezentują możliwe wyniki operacji
 * porównania.
 *
 * @author andrzej.radziszewski
 * @category enumeration
 * @serial 2017
 */
public enum CompareResult {

	/**
	 * Znacznik reprezentuje wynik wyrażenia mniejszy niż wyrażony jako
	 * <code>x < y</code> Porównanie wzgledem lewego operanda.
	 */
	LESSER((byte) -1),

	/**
	 * Znacznik reprezentuje wynik wyrażenia równości wyrażony jako
	 * <code>x == y</code> Porownanie wzgledem lewego operanda.
	 */
	EQUALS((byte) 0),

	/**
	 * Znacznik reprezentuje wynik wyrażenia większy niż wyrażony jako
	 * <code>x > y</code> Porownanie wzgledem lewego operanda.
	 */
	GREATER((byte) 1);

	/**
	 * Atrybut określa kod wyniku operacji porówania
	 */
	private final byte compareCode;

	/**
	 * Podstawowy kontruktor obiektu klasy <code>CompareResult</code>
	 *
	 * @param compareCode Kod wyniku porównania
	 */
	private CompareResult(final byte compareCode) {

		this.compareCode = compareCode;
	}

	/**
	 * Właściwość wskazuje wartość wyniku operacji porównania
	 *
	 * @return Wartość numeryczna może zwracać wartości (-1, 0, 1)
	 */
	public int result() {

		return this.compareCode;
	}
}

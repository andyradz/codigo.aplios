package com.codigo.aplios.group.sdk.core.compare;

/**
 * Interfejs deklaruje kontrakt opisujący mechanizm porównywania dwóch obiektów.
 * Implementacja interfajsu będzie miała postaci operatora porównania.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category operator
 */
@FunctionalInterface
public interface IComparable {

	/**
	 * @param leftOperand  Lewy operand operatora porównania
	 * @param rightOperand Prawy operand operatora porównania
	 * @return Wynik operacji porównania w postaci logicznej TRUE,FALSE
	 */
	boolean compare(Comparable<?> leftOperand, Comparable<?> rightOperand);
}

package com.codigo.aplios.sdk.core.helpers;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.codigo.aplios.sdk.core.constants.Default;
import com.codigo.aplios.sdk.core.messge.ErrorMessages;

// TODO: doda Paginator dla tablicy elementów
/**
 * Klasa zawiera najczęściej stosowane metody do obsługi kolekcji typu tablica.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.1
 * @since 2017
 * @category helpers
 */
public final class ArrayOperator {

	private static final Logger log = Logger.getLogger(ArrayOperator.class);

	private static final Class<Integer> ARRAYINDEXTYPE = Integer.class;

	enum z1 {
	}

	public static void main(final String[] args) {

		final Integer[] ints = ArrayOperator.empty(Integer[].class);
		ArrayOperator.log.info(ints);

		ArrayOperator.log.info(ArrayOperator.toByteArray(1.00d));

		Default.of(ArrayOperator.ARRAYINDEXTYPE);

		final Integer[] arrResize = { 0, 1, 1, 1, 1, 1, 1, 1, 0, 12, 55 };

		final OptionalInt pos = ArrayOperator.indexOf(arrResize, 0);
		pos.ifPresent(top -> ArrayOperator.log.info("Wartość " + top));

		ArrayOperator.clear(arrResize);

		final int[] int1 = new Random().ints(0, 10)
				.limit(15)
				.toArray();

		final int[] int2 = new Random().ints(-10, 500)
				.limit(10)
				.toArray();

		Arrays.sort(int1);
		Arrays.sort(int2);

		final Integer[] arr1 = IntStream.of(int1)
				.boxed()
				.toArray(Integer[]::new);
		final Integer[] arr2 = IntStream.of(int2)
				.boxed()
				.toArray(Integer[]::new);
		final Integer[] result = ArrayOperator.merge(Integer[].class, arr1, arr2);

		final Iterator<Integer> arrJoin = ArrayOperator.join(arr1, arr2);

		while (arrJoin.hasNext())
			ArrayOperator.log.debug(arrJoin.next() + "|");

		Arrays.stream(result)
				.forEach(ArrayOperator.log::info);

		final String[] str1 = { "a", "b", "c", "d" };
		final String[] str2 = { "aa", "b", "b", "d" };
		Arrays.stream(ArrayOperator.merge(String[].class, str1, str2))
				.forEach(ArrayOperator.log::info);
	}

	/**
	 * Metoda wyznacza średnią wartości numeryczną z elementów kolekcji typu tablica.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Tablica wartości numerycznych
	 * @return Średnia wartość wyznaczona z wartości elementów tablicy
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamieci
	 */
	public static final <E extends Number> double avg(final E[] array) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		return Arrays.stream(array)
				.map(Number::doubleValue)
				.mapToDouble(Double::doubleValue)
				.average()
				.getAsDouble();
	}

	/**
	 * Metoda wyznacza duplikaty wartości z tablicy elementów.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Tablica wartości numerycznych
	 * @return Strumień danych wyznaczający duplikaty z tablicy wartości.
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamieci
	 */
	public static final <E> Stream<E> duplicate(final E[] array) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		return Arrays.stream(array)
				.filter(n -> Arrays.stream(array)
						.filter(x -> x == n)
						.count() > 1)
				.distinct();
	}

	/**
	 * Metoda zwraca numer ostatniego indeksu tablicy dwuwymiarowej
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Kolekcja danych typu tablica
	 * @param dimension
	 *        Wymiar tablicy
	 * @return Numer ostattniego indeksu tablicy dwuwymiarowej
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamieci
	 */
	public static <E> long hbound(final E[][] array, final int dimension) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		final long count = Arrays.stream(array[dimension])
				.count();

		if (count <= Default.of(Long.class))
			throw new ArrayIndexOutOfBoundsException(
				"Tablica elementów nie posiada ostatniego indeksu!");

		return count - 1;
	}

	/**
	 * Metoda zwraca numer ostatniego indeksu tablicy jednowymiarowej
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Kolekcja danych typu tablica
	 * @return Numer ostatniego indeksu tablicy
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamieci
	 */
	public static <E> long hbound(final E[] array) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		return array.length - 1L;
	}

	/**
	 * Metoda zwraca numer pierwszego indeksu tablicy jednowymiarowej
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Kolekcja danych typu tablica
	 * @return Numer pierwszego indeksu tablicy
	 */
	public static <E> long lbound(final E[] array) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		return Default.of(Long.class);
	}

	/**
	 * Metoda wyznacza wspólne elementy dwóch kolekcji typu tablica
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param clazz
	 *        Klasa elementów tablicy
	 * @param firstArray
	 *        Pierwsza kolekcja
	 * @param lastArray
	 *        Kolejna kolekcja
	 * @return Kolekcja elmentów znajdujących sie w obu tablcach elementów
	 */
	public static <E> E[] intersect(final Class<E[]> clazz, final E[] firstArray, final E[] lastArray) {

		if (firstArray == lastArray)
			return firstArray;

		return Arrays.stream(firstArray)
				.filter(left -> Arrays.stream(lastArray)
						.anyMatch(right -> right.equals(left)))
				.toArray(e1 -> clazz.cast(Array.newInstance(clazz.getComponentType(),
						firstArray.length >= lastArray.length ? firstArray.length : lastArray.length)));
	}

	/**
	 * Metoda wykonuje połączenie dwóch kolekcji typu tablica w jedną
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param firstArray
	 *        Pierwsza kolekcja danych
	 * @param lastArray
	 *        Następna kolekcja danych
	 * @return Iterator połączonych kolekcji typu tablica
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamieci
	 */
	public static final <E> Iterator<E> join(final E[] firstArray, final E[] lastArray) {

		if (Objects.isNull(firstArray))
			throw ErrorMessages.getNullPointerExceptioMessage(firstArray);

		if (Objects.isNull(lastArray))
			throw ErrorMessages.getNullPointerExceptioMessage(lastArray);

		return Stream.concat(Arrays.stream(firstArray), Arrays.stream(lastArray))
				.iterator();
	}

	/**
	 * Metoda wyznacza maksymalną wartość elementu tablicy. Maksymalna wartość wyznaczana jest na
	 * podstawie przekazanego komparatora.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Tablica elementów
	 * @param comparator
	 *        Komparator elementów kolekcji
	 * @return Maksymalna wartość elemntu kolkecji
	 * @throws NullPointerException
	 *         Brak instancji tablicy, Brak instancji komparatora
	 */
	public static <E extends Comparable<E>> Optional<E> max(final E[] array, final Comparator<E> comparator) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		if (Objects.isNull(comparator))
			throw ErrorMessages.getNullPointerExceptioMessage(comparator);

		return Arrays.stream(array)
				.max(comparator);
	}

	/**
	 * Metoda wyznacza minimalną wartość elementu tablicy. Minimalna wartość wyznaczana jest na
	 * podstawie przekazanego komparatora.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Tablica elementów
	 * @param comparator
	 *        Komparator elementów kolekcji
	 * @return Minimalna wartość elementu kolekcji
	 * @throws NullPointerException
	 *         Brak instancji tablicy, Brak instancji komparatora
	 */
	public static <E extends Comparable<E>> Optional<E> min(final E[] array, final Comparator<E> comparator) {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		if (Objects.isNull(comparator))
			throw ErrorMessages.getNullPointerExceptioMessage(comparator);

		return Arrays.stream(array)
				.min(comparator);
	}

	/**
	 * Metoda wykonuje zmianę rozmiaru kolekcji
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Kolekcja danych
	 * @param size
	 *        Nowy rozmiar kolekcji danych
	 * @return Kolekcja typu tablica
	 * @throws NullPointerException
	 *         Brak instancji tablicy w pamięci
	 * @throws IllegalArgumentException
	 *         Nieprawidłowa wartość nowego rozmiaru tablicy
	 * @throws OutOfMemoryError
	 *         Brak zasobóW do alokacji tablicy o takim rozmiarze
	 */
	public static <E> E[] resize(final E[] array, final int size) throws OutOfMemoryError {

		if (Objects.isNull(array))
			throw ErrorMessages.getNullPointerExceptioMessage(array);

		if (Default.of(ArrayOperator.ARRAYINDEXTYPE) > size)
			throw new IllegalArgumentException(
				"Rozmiar tablicy nie może być wartością ujemną!");

		if (Default.of(ArrayOperator.ARRAYINDEXTYPE)
				.equals(size))
			return array;

		return Arrays.copyOf(array, size);
	}

	/**
	 * Metoda wykonuje sortowanie elementów kolekcji na podstawie wskazanego komparatora danych.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementów kolekcji
	 * @param array
	 *        Kolekcja danych
	 * @param comparator
	 *        Komparator danych
	 * @return Iterator elementów posortowanej tablicy danych
	 */
	public static final <E> Iterator<E> sort(final E[] array, final Comparator<E> comparator) {

		return Arrays.stream(array)
				.sorted(comparator)
				.iterator();
	}

	/**
	 * Metoda tworzy instancję tablicy bajtów na podstawie przekazanej wartości Double. Rozmiar tablicy
	 * ustalany jest na podstawie ilość bajtów typu Double.
	 *
	 * @param value
	 *        Wartość rzeczywista
	 * @return Tablica bajtów
	 */
	public static byte[] toByteArray(final double value) {

		final byte[] bytes = new byte[Double.SIZE];
		ByteBuffer.wrap(bytes)
				.putDouble(value);

		return bytes;
	}

	/**
	 * Metoda wykonuje połączenie dwóch posortowanych kolekcji w jedną z zachowaniem kolejności
	 * elementów.
	 *
	 * @param <E>
	 *        Generyczny typ elementów kolekcji
	 * @param type
	 *        Klasa elemntów kolekcji
	 * @param firstArray
	 *        Pierwsza kolekcja elementów
	 * @param lastArray
	 *        Nastpna kolekcja elementów
	 * @return Posortowana tablica połączonych dwóch kolekcji elementów
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <E extends Comparable<E>> E[] merge(final Class<E[]> type, final E[] firstArray,
			final E[] lastArray) {

		if (Objects.isNull(firstArray))
			throw ErrorMessages.getNullPointerExceptioMessage(firstArray);

		if (Objects.isNull(lastArray))
			throw ErrorMessages.getNullPointerExceptioMessage(lastArray);

		if (firstArray == lastArray)
			return firstArray;

		if (ArrayOperator.isEmpty(firstArray) && !ArrayOperator.isEmpty(lastArray))
			return lastArray;

		if (ArrayOperator.isEmpty(lastArray) && !ArrayOperator.isEmpty(firstArray))
			return firstArray;

		final E[] mergedArray = type
				.cast(Array.newInstance(type.getComponentType(), firstArray.length + lastArray.length));
		Integer firstArrayIndex, lastArrayIndex, mergedArrayIndex;

		firstArrayIndex = lastArrayIndex = mergedArrayIndex = Default.of(ArrayOperator.ARRAYINDEXTYPE);

		while ((firstArrayIndex < firstArray.length) && (lastArrayIndex < lastArray.length)) {
			mergedArray[mergedArrayIndex] = firstArray[firstArrayIndex].compareTo(lastArray[lastArrayIndex]) <= Default
					.of(ArrayOperator.ARRAYINDEXTYPE) ? firstArray[firstArrayIndex++] : lastArray[lastArrayIndex++];
			++mergedArrayIndex;
		}

		if (firstArrayIndex < firstArray.length)
			System.arraycopy(firstArray, firstArrayIndex, mergedArray, mergedArrayIndex,
					firstArray.length - firstArrayIndex);
		else if (lastArrayIndex < lastArray.length)
			System.arraycopy(lastArray, lastArrayIndex, mergedArray, mergedArrayIndex,
					lastArray.length - lastArrayIndex);
		return mergedArray;
	}

	/**
	 * Metoda sprawdza czy kolekcja jest pusta (nie posiada elementów)
	 *
	 * @param <E>
	 *        type Generyczny typ elementów kolekcji
	 * @param array
	 *        Tablica elementów typu T
	 * @return Wartość logiczna true gdy tablica pusta, wartośc false gdy tablica posiada elementy
	 */
	public static <E> boolean isEmpty(final E[] array) {

		return Default.of(ArrayOperator.ARRAYINDEXTYPE)
				.equals(array.length);
	}

	/**
	 * Metoda zwraca pustą instancj tablice (nie posiada elementów)
	 *
	 * @param <E>
	 *        Generyczny typ elementów kolekcji
	 * @param type
	 *        Typ elementów tablicy
	 * @return Pusta instancja tablicy
	 */
	public static <E> E[] empty(final Class<E[]> type) {

		return type.cast(Array.newInstance(type.getComponentType(), 0));
	}

	/**
	 * Metoda czyści wszystkie elementy kolekcji
	 *
	 * @param <E>
	 *        Generyczny typ elemenów tablicy
	 * @param array
	 *        Kolekcja elementów
	 */
	public static <E> void clear(final E[] array) {

		Arrays.fill(array, null);
	}

	/**
	 * Metoda wyszukuje element kolekcji i wyznacza numer pozycji tego elementu w kolekcji.
	 *
	 * @param <E>
	 *        Generyczny typ klasy elementu kolekcji
	 * @param array
	 *        Kolekcja elementów
	 * @param element
	 *        Szukany element
	 * @return Numer pozycji elementu w kolekcji
	 */
	public static <E> OptionalInt indexOf(final E[] array, final E element) {

		return IntStream.range(Default.of(ArrayOperator.ARRAYINDEXTYPE), array.length)
				.filter(i -> array[i].equals(element))
				.findAny();
	}

}

package com.codigo.aplios.group.timeline.core;

import java.util.function.UnaryOperator;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.group.timeline.common.helper.CompareOperator;
import com.codigo.aplios.group.timeline.common.helper.CompareResult;
import com.codigo.aplios.group.timeline.common.helper.Property;
import com.codigo.aplios.group.timeline.common.helper.PropertyIntegerComparator;
import com.codigo.aplios.group.timeline.common.helper.PropertyStringComparator;

public class PropertyTests {

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością null")
	public void initPropertyFromNull_sholudHasNullObjectValue() {

		final Object object = null;

		// arrange
		final Property<Object> property = Property.from(object);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat(null, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością null i ustawienie wartości float")
	public void setFromFloat_sholudHasFloatObjectValue() {

		final Object object = 100.44f;

		// arrange
		final Property<Object> property = Property.from();
		property.set(object);

		// act
		final var value = property.get();

		// asset
		MatcherAssert.assertThat(100.44f, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypadek powstania wyjątku IllegalArgumentException gdy flaga ThrowIfNull=True")
	public void setPropertyFromStringWithoutNull_sholudGetIllegalArgumentException() {

		// arrange
		final Object object = "";

		// act
		final Property<Object> property = Property.from(object, true, false);

		// assert
		// MatcherAssert.assertThat(property.set(null),
		// CoreMatchers.instanceOf(Property.class));
		Assertions.assertThrows(IllegalArgumentException.class, () -> property.set(null));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością false i ustawienie wartości true")
	public void setFromBoolean_sholudHasBoleanObjectValue() {

		final Boolean object = false;

		// arrange
		final Property<Boolean> property = Property.from(object);
		property.set(true);

		// act
		final Boolean value = property.get();

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypadek powstania wyjątku UnsupportedOperationException gdy flaga ReadOnly=True")
	public void initPropertyFromObjectReadOnly_sholudGetUnsupportedOperationException() {

		// arrange
		final Object object = null;

		// act
		final Property<Object> property = Property.from(true);

		// assert
		Assertions.assertThrows(UnsupportedOperationException.class, () -> property.set(object));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością blank i zwrot wartości brank")
	public void initFromEmptyString_sholudHasEmptyStringValue() {

		final String string = "";

		// arrange
		final Property<String> property = Property.from(string);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat("", CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością integer(0) i zwrot wartości integer(0)")
	public void initFromDefaultInteger_sholudHasDefaultIntegerValue() {

		final Integer number = 0;

		// arrange
		final Property<Integer> property = Property.from(number);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat(0, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością short(0) i zwrot wartości short(0)")
	public void initFromShortInteger_sholudHasDefaultShortValue() {

		final Short number = 0;

		// arrange
		final Property<Short> property = Property.from(number);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat((short) 0, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością byte(0) i zwrot wartości byte(0)")
	public void initFromByteInteger_sholudHasDefaultByteValue() {

		final Byte number = 0;

		// arrange
		final Property<Byte> property = Property.from(number);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat((byte) 0, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek inicjalizacji obiektu wartością long(0) i zwrot wartości long(0)")
	public void initFromLong_sholudHasDefaultLongValue() {

		final Byte number = 0;

		// arrange
		final Property<Byte> property = Property.from(number);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat((byte) 0, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek wielokrotnem modyfikacji wartości string")
	public void setFromStringWithManyChanges_sholudHasStringValue() {

		final String string = null;

		// arrange
		final Property<String> property = Property.from(string);
		property.set("0");
		property.set(property.get() + "1");
		property.set(property.get() + "2");
		property.set(property.get() + "3");
		property.set(property.get() + "4");
		property.set(property.get() + "5");
		property.set(property.get() + "6");
		property.set(property.get() + "7");
		property.set(property.get() + "8");
		property.set(property.get() + "9");
		property.set(property.get() + "");

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat("0123456789", CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek wielokrotnem modyfikacji wartości integer")
	public void setFromIntegerWithkManyChanges_sholudHasIntegerValue() {

		final Integer number = null;

		// arrange
		final Property<Integer> property = Property.from(number);
		property.set(0);
		property.set(property.get() + 1);
		property.set(property.get() + 2);
		property.set(property.get() + 3);
		property.set(property.get() + 4);
		property.set(property.get() + 5);
		property.set(property.get() + 6);
		property.set(property.get() + 7);
		property.set(property.get() + 8);
		property.set(property.get() + 9);

		// act
		final var value = property.get();

		// assert
		MatcherAssert.assertThat(45, CoreMatchers.is(value));
	}

	@Test
	@DisplayName("Test sprawdza przypdek ustawienia flagi readonly=true")
	public void initFromObjectWithReadOnly_sholudHasReadonlyTrue() {

		final Object object = null;

		// arrange
		final Property<?> property = Property.from(object, true);

		// act
		final var readonly = property.isReadOnly();

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(readonly));
	}

	@Test
	@DisplayName("Test sprawdza przypdek atrybutu isnull=true")
	public void initFromObjectWithNull_sholudHasNullTrue() {

		final Object object = null;

		// arrange
		final Property<?> property = Property.from(object);

		// act
		final var readonly = property.isNull();

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(readonly));
	}

	@Test
	@DisplayName("Test sprawdza porównannie dwóch różnych obiektów właściwości typu string")
	public void compareStringAndString_sholudNotEqualsResult() {

		// act
		final Property<String> propertyOne = Property.from("1");
		final Property<String> propertyTwo = Property.from("");

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(CompareOperator.NOTEQUALS.compare(propertyOne, propertyTwo)));
	}

	@Test
	@DisplayName("Test sprawdza porównannie dwóch identycznych obiektów właściwości typu string")
	public void compareStringAndString_sholudEqualsResult() {

		// act
		final Property<String> propertyOne = Property.from("   ąćł12230))))łłł");

		// assert
		final Property<String> propertyTwo = Property.from("   ąćł12230))))łłł");

		MatcherAssert.assertThat(true, CoreMatchers.is(CompareOperator.EQUALS.compare(propertyOne, propertyTwo)));
	}

	@Test
	@DisplayName("Test sprawdza porównannie dwóch różnych obiektów właściwości typu object")
	public void compareNumberObjectAndString_sholudNotEqualsResult() {

		// act
		final Property<Object> propertyOne = Property.from(1);
		final Property<Object> propertyTwo = Property.from("1");

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(CompareOperator.NOTEQUALS.compare(propertyOne, propertyTwo)));
	}

	@Test
	@DisplayName("Test sprawdza porównannie wartość nieokreślonej obiektów właściwości typu object")
	public void compareNullObjectAndNullString_sholudNotEqualsResult() {

		// act
		final Property<Object> propertyOne = Property.from();
		final Property<String> propertyTwo = Property.from();

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(CompareOperator.NOTEQUALS.compare(propertyOne, propertyTwo)));
	}

	@Test
	@DisplayName("Test sprawdza porównannie wartość nieokreślonej i określonej obiektów właściwości typu object")
	public void compareNullObjecAndNumberObject_sholudNotEqualsResult() {

		// act
		final Property<Object> propertyOne = Property.from();
		final Property<Object> propertyTwo = Property.from(1);

		// assert
		MatcherAssert.assertThat(true, CoreMatchers.is(CompareOperator.NOTEQUALS.compare(propertyOne, propertyTwo)));
	}

	@Test
	public void test1() {

		// arrange
		final Property<String> propertyOne = Property.from("2");
		final Property<String> propertyTwo = Property.from("2");

		// act
		final PropertyStringComparator c = new PropertyStringComparator();

		// assert
		MatcherAssert.assertThat(CompareResult.EQUALS.result(),
				CoreMatchers.is(c.compare(propertyOne, propertyTwo)));
	}

	@Test
	public void test2() {

		// arrange
		final Property<Integer> propertyOne = Property.from(-1);
		final Property<Integer> propertyTwo = Property.from(1);

		// act
		final PropertyIntegerComparator c = new PropertyIntegerComparator();

		// assert
		MatcherAssert.assertThat(CompareResult.LESSER.result(),
				CoreMatchers.is(c.compare(propertyOne, propertyTwo)));

	}

	@Test
	public void test4() {

		final Property<Object> property = Property.from(null, UnaryOperator.identity(), UnaryOperator.identity());

		MatcherAssert.assertThat(null, CoreMatchers.is(property.get()));
	}

	@Test
	public void test5() {

		final Property<Object> property = Property.from("", UnaryOperator.identity(), UnaryOperator.identity(), true);

		MatcherAssert.assertThat("", CoreMatchers.is(property.get()));
	}

	@Test
	public void test6() {

		final Property<Object> property1 = Property.from("", UnaryOperator.identity(), UnaryOperator.identity(), true);
		final Property<Object> property2 = Property.from(property1);

		MatcherAssert.assertThat("", CoreMatchers.is(property2.get()));
	}
}
//inal List<Integer> list = Lists.mutable.empty();

//final Property<Integer> counter = Property.from(0);
//
//final Runnable incrementCounter = () -> {
//
//	for (;;)
//
//		synchronized (counter) {
//
//			// if (remain == 0)
//			// Thread.currentThread()
//			// .interrupt();
//
//			counter.set(counter.get() + 1);
//			list.add(counter.get());
//
//			System.out.println(
//					String.format("%s->%010d",
//							Thread.currentThread()
//								.getName(),
//
//							counter.get()));
//		}
//};
//
//final Thread thread1 = new Thread(incrementCounter);
//final Thread thread2 = new Thread(incrementCounter);
//final Thread thread3 = new Thread(incrementCounter);
//thread1.start();
//thread2.start();
//thread3.start();
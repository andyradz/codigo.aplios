package com.codigo.aplios.sdk.core.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class TestUnits1 {

	// ...jednostki
	private static Function<Integer, Integer> functUni1 = in -> ((in / 10) % 10) == 1 ? 0 : in % 10;

	// ...nastki
	private static Function<Integer, Integer> functUni2 = in -> (((in % 100) / 10) == 1) && (((in % 100) % 10) != 0)
			? in % 100
			: 0;

	// dziesiątki
	private static Function<Integer, Integer> functUni3 = in -> ((in % 100) % 10) == 0 ? in % 100 : 0;

	// setki
	private static Function<Integer, Integer> functUni4 = in -> ((in % 1000) % 100) == 0 ? in % 1000 : 0;

	@Test
	public void test11() {

		final var value = 910;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test12() {

		final var value = 10;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test0() {

		final var value = 919;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test1() {

		final var value = 12;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test2() {

		final var value = 22;

		MatcherAssert.assertThat(2, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test3() {

		final var value = 2;

		MatcherAssert.assertThat(2, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test4() {

		final var value = 102;

		MatcherAssert.assertThat(2, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test5() {

		final var value = 112;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni1.apply(value)));
	}

	@Test
	public void test6() {

		final String format = "%03d - %03d";

		final IntConsumer print = e -> {

			final int result = TestUnits1.functUni1.apply(e);

			if (result != 0) {
				MatcherAssert.assertThat(result, IsBetween.between(1, 9));
				System.out.println(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999)
				.forEach(print);
	}

	// testowanie nastek
	// ------------------------------------------------------------------------------------------------------------------

	@Test
	public void testUnit1_1() {

		final var value = 112;

		MatcherAssert.assertThat(12, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_2() {

		final var value = 19;

		MatcherAssert.assertThat(19, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_3() {

		final var value = 1;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_4() {

		final var value = 10;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_5() {

		final var value = 110;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_6() {

		final var value = 120;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_7() {

		final var value = 190;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_8() {

		final var value = 819;

		MatcherAssert.assertThat(19, CoreMatchers.is(TestUnits1.functUni2.apply(value)));
	}

	@Test
	public void testUnit1_9() {

		final String format = "%03d - %03d";

		final IntConsumer print = e -> {

			final int result = TestUnits1.functUni2.apply(e);

			if (result != 0) {

				MatcherAssert.assertThat(result, IsBetween.between(11, 19));
				System.out.println(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999)
				.forEach(print);
	}

	// testowanie dziesiątek, zero nie jest przekazywane
	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void testUnit2_0() {

		final String format = "%03d - %03d";

		final IntConsumer print = e -> {

			final int result = TestUnits1.functUni3.apply(e);

			if (result != 0) {

				MatcherAssert.assertThat(result, IsBetween.between(10, 90));
				System.out.println(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999)
				.forEach(print);
	}

	// testowanie setek, zero nie jest przekazywane
	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void testUnit3_0() {

		final String format = "%03d - %03d";

		final IntConsumer print = e -> {

			final int result = TestUnits1.functUni4.apply(e);

			if (result != 0) {

				MatcherAssert.assertThat(result, IsBetween.between(100, 900));
				System.out.println(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999)
				.forEach(print);
	}

	@Test
	public void testLoadProperties() {

		final Properties properties = new Properties();

		final Map<String, String> map = new HashMap<>();
		try {
			final InputStream input = TestUnits1.class.getClassLoader()
					.getResourceAsStream("moneyinwords.properties");
			final InputStreamReader reader = new InputStreamReader(
				input, "UTF-8");

			properties.load(reader);

			map.putAll(properties.entrySet()
					.stream()
					.collect(Collectors.toMap(e -> e.getKey()
							.toString(),
							e -> e.getValue()
									.toString())));

			final BiConsumer<Object, Object> biConsumer = (key, value) -> System.out
					.println("Key:" + key + " Value:" + value);

			// map.forEach(biConsumer);

			properties.forEach(biConsumer);

		}
		catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TesSign() {

		final double value0 = -.099;
		final double value1 = .099;
		final double value2 = -0;
		final double value3 = Double.NaN;

		Math.signum(value0);
		Math.signum(value1);
		Math.signum(value2);
		Math.signum(value3);

	}

	@Test
	public void TestDivideNumber() {

		final double value = 200_324_199_201_316.15d;

		long number = (long) value;
		Math.round((value - (long) value) * 100);
		while (number != 0) {
			number /= 1E3;
		}
	}
}

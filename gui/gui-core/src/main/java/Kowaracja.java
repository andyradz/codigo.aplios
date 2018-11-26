
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * https://edu.pjwstk.edu.pl/wyklady/poj/scb/Generics/Generics.html
 * https://edu.pjwstk.edu.pl/wyklady/zap/scb/W2/W2.htm
 * instatna informacja o typach konwarientnych
 */
public class Kowaracja {

	public static void main(final String[] args) {

		// ograniczone z góry <? extends X> - oznacza "wszystkie podtypy X"
		final List<? extends Number> integers = new ArrayList<Integer>() {

			{
				this.add(Integer.valueOf(-12));
				this.add(Integer.valueOf(-1));
				this.add(Integer.valueOf(-11));
				this.add(Integer.valueOf(-110));
			}
		};
		integers.forEach(System.out::println);

		// ograniczone z dołu <? super X> - oznacza "wszystkie nadtypy X"
		final List<? super Integer> numbers = new ArrayList<Number>() {

			{
				this.add(100d);
				this.add(200.f);
			}
		};
		numbers.forEach(System.out::println);

		final Para<String, Integer> par1 = new Para<>(
			"Andzej", 2);
		new Para<>(
			par1);

		// kowaracja
		// Kowaracja.test1();
	}

	private static void log1(final List<? extends Number> data) {

		data.forEach(System.out::println);
	}

	private static void log2(final List<? super Integer> data) {

		data.add(10_000);
		data.forEach(System.out::println);
	}

	private static void log3(final List<?> data) {

		data.forEach(System.out::println);
	}

	private static <T> void test1() {

		// nowa klasa
		final List<? super Integer> ints1 = new ArrayList<Number>() {

			{
				this.add(213);
			}
		};
		Kowaracja.log2(ints1);
		// kontrwariacja
		List<? super Integer> ints = new ArrayList<Number>() {

			{
				this.add(12);
				this.add(11);
			};
		};
		Kowaracja.log2(ints);
		ints = new ArrayList<>() {

			{
				this.add(12);
				this.add(11);
			};
		};
		Kowaracja.log2(ints);
		// kowariacja
		List<? extends Number> nums = new ArrayList<Integer>() {

			{
				this.add(1);
				this.add(2);
				this.add(3);
				this.add(4);
			}
		};
		Kowaracja.log1(nums);
		nums = new ArrayList<>() {

			{
				this.add(1);
				this.add(2);
				this.add(3);
				this.add(4);
			}
		};
		Kowaracja.log1(nums);
		nums = new ArrayList<Byte>() {

			{
				this.add((byte) 12);
				this.add((byte) 11);
			};
		};
		Kowaracja.log1(nums);
		// Biwariacja
		List<?> binums = new ArrayList<Integer>() {

			{
				this.add(223);
			}
		};
		binums = new ArrayList<Byte>() {

			{
				this.add((byte) 3);
			}
		};
		binums = new ArrayList<Number>() {

			{
				this.add((byte) 3);
			}
		};
		binums = nums;
		binums = ints;
		binums = new ArrayList<String>() {

			{
				this.add("Andrzej");
			}
		};
		Kowaracja.log3(binums);
		binums.forEach(System.out::println);
	}

	@Override
	public int hashCode() {

		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {

		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {

		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {

		// TODO Auto-generated method stub
		super.finalize();
	}
}

class Para<S, T> {

	S	first;
	T	last;

	public Para() {

	}

	public Para(final S f, final T l) {

		this.first = f;
		this.last = l;
	}

	// konstruktor kopiujący
	public Para(final Para<S, T> p) {

		// nie możemy użyć new, ale możemy zastosować refleksję
		try {
			this.first = (S) this.getInstance(p.first); // unchecked, ale jest gwarancja
			this.last = (T) this.getInstance(p.last); // że typy będą właściwe
		}
		catch (final Exception exc) {
			throw new UnsupportedOperationException(
				"Copy constructor not available", exc.getCause());
		}
	}

	private Object getInstance(final Object o) throws Exception {

		final Class<?> type = o.getClass();
		Constructor<?> c = null;
		Object arg = null;
		try { // czy jest konstruktor kopiujący?
			c = type.getConstructor(type);
			arg = o;
		}
		catch (final Exception exc) { // nie ma kopiującego
			if (type.getSuperclass() == java.lang.Number.class) { // może to boxy?
				c = type.getConstructor(java.lang.String.class);
				arg = o.toString();
			}
		}
		if (c == null)
			// utworzyć obiekt za pomocą konstruktora bezparametrowego (newInstance)
			// pobrać od klasy Properties (PropertyDescriptor[])
			// dla wszystkich setterów wywołać odpowiednie gettery na oryginale,
			// a zwrócone wartości podać jako argumenty setterom
			// (i wołać je po kolei na kopii)
			throw new UnsupportedOperationException(
				"Valid constructor not found in" + type); // bo tego nie robimy
		else
			return c.newInstance(arg);
	}

	// dodawanie par
	public Para<S, T> add(final Para<S, T> p) {

		final Para<S, T> wynik = new Para<>(); // nie można new T(), ale można new X<T>!
		try {
			wynik.first = (S) this.addObjects(this.first, p.first); // unchecked,
			wynik.last = (T) this.addObjects(this.last, p.last); // ale typ jest gwarantowany
			return wynik;
		}
		catch (final Exception exc) {
			throw new UnsupportedOperationException(
				"Addition not allowed", exc.getCause());
		}
	}

	private Object addObjects(final Object o1, final Object o2) throws Exception {

		class me<T extends Number> implements Comparable<T> {

			public me() {

			}

			@Override
			public int compareTo(final T arg0) {

				return 0;
			}
		}
		;

		if (o1 instanceof String)
			return (String) o1 + o2; // konkatenacja

		if (o1 instanceof Number) { // działania na klasach opakowujących typy proste

			final double d = ((Number) o1).doubleValue() + ((Number) o2).doubleValue();
			String s = String.valueOf(d);
			Constructor<?> c = null;
			try { // wynik musi być specyficznego typu (np. Integer)
				c = o1.getClass()
						.getConstructor(java.lang.String.class);
				return c.newInstance(s);
			}
			catch (final Exception exc) { // np. gdy new Integer("1.0");
				final int l = s.indexOf('.'); // bierzemy tylko cyfry przed kropką
				s = s.substring(0, l);
				return c.newInstance(s);
			}
		}

		// Ani String ani Number - więc musi mieć metodę add(...)

		final Class typ = o1.getClass();
		final Method m = typ.getDeclaredMethod("add", typ);
		return m.invoke(o1, o2);
	}

	public S getFirst() {

		return this.first;
	}

	public T getLast() {

		return this.last;
	}

	public void setFirst(final S f) {

		this.first = f;
	}

	public void setLast(final T l) {

		this.last = l;
	}

	public void swap() {

		class Dos {

			public int compareTo(final String o) {

				// TODO Auto-generated method stub
				return 0;
			}
		}
		;

		new Dos();

		// s.compareTo("");

		final T temp = (T) this.first;
		this.first = (S) this.last;
		this.last = temp;
	}

	@Override
	public String toString() {

		return this.first + " " + this.last;
	}

}
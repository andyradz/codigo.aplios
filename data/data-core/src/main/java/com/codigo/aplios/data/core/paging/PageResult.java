package com.codigo.aplios.data.core.paging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.manipulation.Sortable;

import com.codigo.aplios.data.core.model.Person;

class PageResult<E> implements IPageable<E> {

	public static void main(final String[] args) {

		final List<Person> data = new ArrayList<>();

		/*
		 * data.add(new Person( "Andrzej", "Radziszewski", (byte) 39, "male")); data.add(new Person(
		 * "Izabela", "Radziszewska", (byte) 35, "female")); data.add(new Person( "Aleksandra",
		 * "Radziszewska", (byte) 4, "female")); data.add(new Person( "Stanisława", "Radziszewska", (byte)
		 * 67, "female")); data.add(new Person( "Mieczysław", "Radziszewski", (byte) 79, "male"));
		 * data.add(new Person( "Tadeusz", "Radziszewski", (byte) 69, "male")); data.add(new Person(
		 * "Gienek", "Radziszewski", (byte) 79, "male")); data.add(new Person( "Zenon", "Radziszewski",
		 * (byte) 49, "male")); data.add(new Person( "Jan", "Radziszewski", (byte) 49, "male"));
		 */

		// data.stream()
		// .sorted(Comparator.comparing(Person::getGender)
		// .thenComparing(Person::getName)
		// .thenComparing(Person::getSurname)
		// .thenComparing(Person::getAge))
		// .forEach(System.out::println);

		final PageNavigator<Person> per = new PageNavigator<>(
			data.stream()
					.collect(Collectors.toList()),
			4);

		while (per.hasNextPage()) {

			final IPageable<Person> page = per.getNextPage();
			System.out.println("(" + page.getPageNumer() + ")"
					+ "--------------------------------------------------------------------------------------");
			page.iterator()
					.forEachRemaining(System.out::println);

			if (!per.hasNextPage())
				per.getCurrentPage()
						.iterator()
						.forEachRemaining(System.out::println);

		}

		// nav.getCurrentPage()
		// .getContent()
		// .forEachRemaining(System.out::println);
		// System.out.println("------------------------------------------------------------------------------------");
		// nav.getNextPage()
		// .getContent()
		// .forEachRemaining(System.out::println);

	}

	public static <E extends Collection<E>> PageResult<E> ofList(final Collection<E> data, final long pageNumber) {

		return new PageResult<>(
			data, pageNumber);
	}

	private final Collection<E> data;

	private final long pageNumber;

	public PageResult(final Collection<E> data, final long pageNumber) {

		this.data = data;
		this.pageNumber = pageNumber;
	}

	@Override
	public long getPageNumer() {

		return this.pageNumber;
	}

	@Override
	public long getPageSize() {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.data.core.paging.IPageable#getPageCount()
	 */
	@Override
	public long getPageCount() {

		return this.data.stream()
				.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.data.core.paging.IPageable#isEmpty()
	 */
	@Override
	public boolean isEmpty() {

		return 0 == this.data.stream()
				.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.data.core.paging.IPageable#isFullFilled()
	 */
	@Override
	public boolean isFullFilled() {

		return (this.getPageCount() == getPageSize());
	}

	@Override
	public boolean isFirst() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {

		return this.data.iterator();
	}

	@Override
	public Sortable getSorter() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isVisible() {

		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.data.core.paging.IPageable#getPageIndex()
	 */
	@Override
	public long getPageIndex() {

		return this.pageNumber + 1;
	}

}

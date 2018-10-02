package com.codigo.aplios.data.core.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

interface StreamEx<T> extends Stream<T> {

	default <R> Function<T, Stream<R>> select(final Class<R> clazz) {

		return e -> clazz.isInstance(e) ? Stream.of(clazz.cast(e)) : null;
	}

}

class MyStream<T> implements Stream<T>, StreamEx<T> {

	private final Stream<T> delegate;

	public MyStream(final Stream<T> delegate) {

		this.delegate = delegate;
	}

	@Override
	public Stream<T> filter(final Predicate<? super T> predicate) {

		return this.delegate.filter(predicate);
	}

	@Override
	public void forEach(final Consumer<? super T> action) {

		this.delegate.forEach(action);
	}

	@Override
	public Iterator<T> iterator() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spliterator<T> spliterator() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isParallel() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stream<T> sequential() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> parallel() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> unordered() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> onClose(final Runnable closeHandler) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {

		// TODO Auto-generated method stub
	}

	@Override
	public <R> Stream<R> map(final Function<? super T, ? extends R> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntStream mapToInt(final ToIntFunction<? super T> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LongStream mapToLong(final ToLongFunction<? super T> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleStream mapToDouble(final ToDoubleFunction<? super T> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Stream<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntStream flatMapToInt(final Function<? super T, ? extends IntStream> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LongStream flatMapToLong(final Function<? super T, ? extends LongStream> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleStream flatMapToDouble(final Function<? super T, ? extends DoubleStream> mapper) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> distinct() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> sorted() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> sorted(final Comparator<? super T> comparator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> peek(final Consumer<? super T> action) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> limit(final long maxSize) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> skip(final long n) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachOrdered(final Consumer<? super T> action) {

		// TODO Auto-generated method stub
	}

	@Override
	public Object[] toArray() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> A[] toArray(final IntFunction<A[]> generator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T reduce(final T identity, final BinaryOperator<T> accumulator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> reduce(final BinaryOperator<T> accumulator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> U reduce(final U identity, final BiFunction<U, ? super T, U> accumulator,
			final BinaryOperator<U> combiner) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R collect(final Supplier<R> supplier, final BiConsumer<R, ? super T> accumulator,
			final BiConsumer<R, R> combiner) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R, A> R collect(final Collector<? super T, A, R> collector) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> min(final Comparator<? super T> comparator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> max(final Comparator<? super T> comparator) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean anyMatch(final Predicate<? super T> predicate) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean allMatch(final Predicate<? super T> predicate) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noneMatch(final Predicate<? super T> predicate) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<T> findFirst() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> findAny() {

		// TODO Auto-generated method stub
		return null;
	}

	// all other methods from the interface
}

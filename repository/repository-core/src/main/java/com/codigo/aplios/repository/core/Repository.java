package com.codigo.aplios.repository.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.codigo.aplios.domain.model.common.EntityModel;

public interface Repository<T extends EntityModel> {

	default Optional<T> get(final Integer key) {

		return this.get()
				.stream()
				.filter(entity -> entity.getId()
						.equals(key))
				.findAny();
	}

	default Optional<T> get(final int key) {

		return this.get()
				.stream()
				.filter(entity -> entity.getId()
						.equals(Integer.valueOf(key)))
				.findAny();
	}

	default Set<T> get(final Predicate<T> predicate) {

		return this.get()
				.stream()
				.filter(predicate)
				.collect(Collectors.toSet());
	}

	Set<T> get();

	void save(T entity);

	default void persist(final T... entities) {

		this.persist(Arrays.asList(entities));
	}

	default void persist(final Collection<T> entities) {

		entities.forEach(this::save);
	}

	void merge(T entity);

	default void merge(final T... entities) {

		this.update(Arrays.asList(entities));
	}

	default void update(final Collection<T> entities) {

		entities.forEach(this::merge);
	}

	void remove(T entity);

	default void remove(final Iterator<T> entities) {

		entities.forEachRemaining(this::remove);
	}

	default void remove(final Integer keyId) {

		this.remove(entity -> entity.getId()
				.equals(keyId));
	}

	default void remove(final Predicate<T> predicate) {

		this.get()
				.forEach(this::remove);
	}

	default void remove(final Collection<T> entities) {

		entities.forEach(this::remove);
	}

	default void delete(final T... entities) {

		this.remove(Arrays.asList(entities));
	}

	default long count() {

		return this.get()
				.stream()
				.count();
	}

	default Long countAsync() throws InterruptedException, ExecutionException {

		final CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> this.get()
				.stream()
				.count());

		return completableFuture.get();
	}

	long removeAll();

	boolean isAutoCommit();

}

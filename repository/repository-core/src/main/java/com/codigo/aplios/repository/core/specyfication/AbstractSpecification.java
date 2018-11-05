package com.codigo.aplios.repository.core.specyfication;

import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

abstract class AbstractSpecification<T> implements Specification<T> {

	@Override
	public boolean isSatisfiedBy(final T t) {

		return false;
		// throw new NotImplementedException();
	}

	@Override
	public Predicate toPredicate(final Root<T> poll, final CriteriaBuilder cb) {

		return cb.conjunction();
	}

	@Override
	public Specification<T> and(final Specification<T> other) {

		return new AndSpecification<>(
			this, other);
	}

	@Override
	public Class<T> getType() {

		final ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		return (Class<T>) type.getActualTypeArguments()[0];
	}
}

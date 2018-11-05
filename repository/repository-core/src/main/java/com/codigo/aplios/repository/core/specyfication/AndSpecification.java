package com.codigo.aplios.repository.core.specyfication;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AndSpecification<T> extends AbstractSpecification<T> {

	private final Specification<T>	first;
	private final Specification<T>	second;

	public AndSpecification(final Specification<T> first, final Specification<T> second) {

		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfiedBy(final T t) {

		return this.first.isSatisfiedBy(t) && this.second.isSatisfiedBy(t);
	}

	@Override
	public Predicate toPredicate(final Root<T> root, final CriteriaBuilder cb) {

		return cb.and(this.first.toPredicate(root, cb), this.second.toPredicate(root, cb));
	}

	@Override
	public Class<T> getType() {

		return this.first.getType();
	}
}

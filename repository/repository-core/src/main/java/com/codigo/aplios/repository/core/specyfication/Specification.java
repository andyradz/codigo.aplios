package com.codigo.aplios.repository.core.specyfication;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

interface Specification<T> {

	boolean isSatisfiedBy(T t);

	Predicate toPredicate(Root<T> root, CriteriaBuilder cb);

	Specification<T> and(Specification<T> other);

	Class<T> getType();

}

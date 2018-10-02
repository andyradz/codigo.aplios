package com.codigo.aplios.repository.core.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateBuilder<T> {

    Predicate build(CriteriaBuilder criteriaBuilder, Root<T> root);

}

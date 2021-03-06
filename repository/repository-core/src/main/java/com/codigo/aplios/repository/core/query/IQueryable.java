package com.codigo.aplios.repository.core.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface IQueryable<T> {

    CriteriaQuery<T> build(CriteriaBuilder criteriaBuilder, Root<T> root, CriteriaQuery<T> criteriaQuery);

}

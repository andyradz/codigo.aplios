package com.codigo.aplios.repository.core;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.codigo.aplios.repository.core.query.IQueryable;
import com.codigo.aplios.repository.core.query.PredicateBuilder;

/**
 * Implements support for lambda queries. The most important class of the project.
 *
 * @param <T>
 *        Entity class
 * @author janhalasa
 */
public abstract class AbstractDao<T> {

	private final Logger logger = Logger.getLogger(this.getClass()
			.getName());

	private final EntityManager em;

	private final Class<T> entityClass;

	protected AbstractDao(final Class<T> entityClass, final EntityManager em) {

		this.em = em;
		this.entityClass = entityClass;
	}

	public Logger logger() {

		return this.logger;
	}

	public void persist(final T entity) {

		this.logger()
				.log(Level.INFO, "Creating entity {0}", entity);
		this.em.persist(entity);
	}

	public T merge(final T entity) {

		this.logger()
				.log(Level.INFO, "Updating entity {0}", entity);
		return this.em.merge(entity);
	}

	public void remove(final T entity) {

		this.logger()
				.log(Level.INFO, "Deleting entity {0}", entity);
		this.em.remove(entity);
	}

	public T getById(final Long id) {

		this.logger()
				.log(Level.INFO, "Getting entity {0} with ID {1}",
						new Object[] { this.entityClass.getSimpleName(), id });
		return this.em.find(this.entityClass, id);
	}

	public List<T> findAll() {

		return this.findWhere();
	}

	/**
	 * Creates a TypedQuery which can be further customized by calling its methods such as
	 * setMaxResults() or setFirstResult. To get results, call its getResultList() or getSingleResult()
	 * method. Method is private, so it cannot be overridden - it's used by other methods.
	 */
	private TypedQuery<T> createTypedQuery(final IQueryable<T> queryBuilder) {

		final CriteriaBuilder cb = this.em()
				.getCriteriaBuilder();
		final CriteriaQuery<T> q = cb.createQuery(this.entityClass);
		final Root<T> root = q.from(this.entityClass);
		cb.parameter(Integer.class);
		CriteriaQuery<T> criteriaQuery = q.select(root);
		criteriaQuery = queryBuilder.build(cb, root, criteriaQuery);
		final TypedQuery<T> typedQuery = this.em.createQuery(criteriaQuery);
		return typedQuery;
	}

	protected TypedQuery<T> createQuery(final IQueryable<T> queryBuilder) {

		return this.createTypedQuery(queryBuilder);
	}

	public List<T> find(final IQueryable<T> queryBuilder) {

		return this.createQuery(queryBuilder)
				.getResultList();
	}

	/**
	 * @param predicateBuilders
	 *        Restricting query conditions. If you supply more than one predicate, they will be joined
	 *        by conjunction.
	 */
	protected List<T> findWhere(final PredicateBuilder<T>... predicateBuilders) {

		return this
				.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
				.getResultList();
	}

	/**
	 * @param predicateBuilders
	 *        Restricting query conditions. If you supply more than one predicate, they will be joined
	 *        by conjunction.
	 */
	protected T getWhere(final PredicateBuilder<T>... predicateBuilders) {

		return this
				.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
				.getSingleResult();
	}

	/**
	 * @param predicateBuilders
	 *        Restricting query conditions. If you supply more than one predicate, they will be joined
	 *        by conjunction.
	 */
	protected void deleteWhere(final PredicateBuilder<T>... predicateBuilders) {

		final CriteriaBuilder cb = this.em()
				.getCriteriaBuilder();
		final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityClass);
		final Root<T> root = delete.from(this.entityClass);

		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			delete.where(this.buildPredicates(cb, root, predicateBuilders));
		this.em()
				.createQuery(delete)
				.executeUpdate();
	}

	private Predicate[] buildPredicates(final CriteriaBuilder cb, final Root<T> root,
			final PredicateBuilder<T>... predicateBuilders) {

		final List<Predicate> predicates = new LinkedList<>();
		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			for (final PredicateBuilder<T> builder : predicateBuilders)
				predicates.add(builder.build(cb, root));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	protected EntityManager em() {

		return this.em;
	}

}

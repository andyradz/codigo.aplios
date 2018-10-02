package com.codigo.aplios.repository.core;

import com.codigo.aplios.domain.model.common.EntityModel;
import com.codigo.aplios.repository.core.query.IQueryable;
import com.codigo.aplios.repository.core.query.PredicateBuilder;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
public class GenericRepository<T extends EntityModel>
        implements Repository<T> {

    // -----------------------------------------------------------------------------------------------------------------
    private final EntityManagerFactory emf;

    // -----------------------------------------------------------------------------------------------------------------
    private final Class<T> entityType;

    // -----------------------------------------------------------------------------------------------------------------
    private final boolean isAutoCommit;

    // -----------------------------------------------------------------------------------------------------------------
    public GenericRepository(final Class<T> type, final String unitName) {

        this.entityType = type;
        this.isAutoCommit = true;
        this.emf = Persistence.createEntityManagerFactory(unitName);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Set<T> get() {

        final List<T> resultList = this.run(entityManager -> {
            final TypedQuery<T> query = this.createTypedQuery((cb, r, q) -> q);
            return query.getResultList();
        });

        return new HashSet<>(resultList);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void save(final T entity) {

        this.runInTransaction(entityManager -> {
            if (Objects.isNull(entity.getId()))
                entityManager.persist((entity));
            else if (Objects.nonNull(entityManager.find(this.entityType, entity.getId())))
                entityManager.merge((entity));
            return entity;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final Collection<T> entities) {

        this.runInTransaction(entityManager -> {
            entities.forEach(this::save);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void remove(final T entity) {

        this.remove(entity.getId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void remove(final Integer keyId) {

        this.runInTransaction(entityManager -> {
            final T managedEntity = entityManager.find(this.entityType, keyId);
            if (Objects.nonNull(managedEntity))
                entityManager.remove(managedEntity);
        });
    }

    @Override
    public long count() {

        final EntityManager em = this.getEntityManger();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> query = cb.createQuery(Long.class);

        final Root<T> root = query.from(this.entityType);

        // Selecting the count
        query.select(cb.count(root));

        return em.createQuery(query).
                getSingleResult();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // @Override
    // public void remove(Predicate<T> predicate) {
    //
    // this.remove(get(predicate));
    // }
    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void remove(final Collection<T> entities) {

        this.runInTransaction(entityManager -> {
            entities.stream().
                    map(T::getId).
                    map(id -> entityManager.find(this.entityType, id)).
                    filter(Objects::nonNull).
                    forEach(entityManager::remove);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private <R> R runInTransaction(final Function<EntityManager, R> operator) {

        return this.run(entityManager -> {
            final EntityTransaction tran = entityManager.getTransaction();
            tran.begin();
            final R result = operator.apply(entityManager);
            if (this.isAutoCommit())
                entityManager.flush();
            tran.commit();
            return result;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void runInTransaction(final Consumer<EntityManager> operator) {

        this.run(entityManager -> {
            final EntityTransaction tran = entityManager.getTransaction();
            tran.begin();
            operator.accept(entityManager);
            if (this.isAutoCommit())
                entityManager.flush();
            tran.commit();
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private <R> R run(final Function<EntityManager, R> operator) {

        final EntityManager entityManager = this.getEntityManger();
        // entityManager.clear();
        try {
            return operator.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void run(final Consumer<EntityManager> operator) {

        this.run(entityManager -> {
            operator.accept(entityManager);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private TypedQuery<T> createTypedQuery(final IQueryable<T> queryBuilder) {

        // TODO: zweryfikować poprawnośc dwa razy querty
        final EntityManager entityManager = this.getEntityManger();
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> query = builder.createQuery(this.entityType);
        final Root<T> root = query.from(this.entityType);
        CriteriaQuery<T> criteriaQuery = query.select(root);
        criteriaQuery = queryBuilder.build(builder, root, criteriaQuery);
        final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFlushMode(this.isAutoCommit ? FlushModeType.AUTO : FlushModeType.COMMIT);
        return typedQuery;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<T> find(final long keyId) {

        final EntityManager em = this.getEntityManger();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> query = cb.createQuery(this.entityType);

        final Root<T> c = query.from(this.entityType);
        final ParameterExpression<Long> p = cb.parameter(Long.class, "id");
        query.select(c).
                where(cb.equal(c.get("id"), p));

        return em.createQuery(query).
                setParameter("id", keyId).
                getResultList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public long removeAll() {

        final EntityManager em = this.emf.createEntityManager();
        em.close();

        return (this.runInTransaction(entityManager -> {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaDelete<T> query = criteriaBuilder.createCriteriaDelete(this.entityType);
            return entityManager.createQuery(query).
                    executeUpdate();
        })).longValue();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isAutoCommit() {

        return this.isAutoCommit;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void merge(final T entity) {

        this.runInTransaction(entityManager -> {
            entityManager.merge((entity));
        });
    }

    // ------------------------------------------------------------------------------------------------------------------
    @Override
    public void update(final Collection<T> entities) {

        this.runInTransaction(entityManager -> {
            entities.forEach(this::merge);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private EntityManager getEntityManger() {

        final EntityManager em = this.emf.createEntityManager();
        em.setFlushMode(this.isAutoCommit ? FlushModeType.AUTO : FlushModeType.COMMIT);
        return em;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<T> finda(final IQueryable<T> queryBuilder) {

        return this.createQuery(queryBuilder).
                getResultList();
    }
    // https://github.com/janhalasa/JpaCriteriaWithLambdaExpressions/blob/master/src/main/java/com/halasa/criterialambda/dao/builder/QueryBuilder.java

    // -----------------------------------------------------------------------------------------------------------------
    public List<T> findAll() {

        return this.findWhere();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected TypedQuery<T> createQuery(final IQueryable<T> queryBuilder) {

        return this.createTypedQuery(queryBuilder);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<T> find(final IQueryable<T> queryBuilder) {

        return this.createQuery(queryBuilder).
                getResultList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * @param predicateBuilders
     * Restricting query conditions. If you supply more than one predicate, they will be joined by conjunction.
     * @return
     *
     */
    protected List<T> findWhere(final PredicateBuilder<T>... predicateBuilders) {

        return this
                .createTypedQuery((cb, root, query) ->
                        (query.where(this.buildPredicates(cb, root, predicateBuilders)))).
                getResultList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // /**
    // * @param predicateBuilders
    // * Restricting query conditions. If you supply more than one predicate, they will be
    // * joined by conjunction.
    // */
    protected T where(final PredicateBuilder<T>... predicateBuilders) {

        return this
                .createTypedQuery((cb, root, query) ->
                        (query.where(this.buildPredicates(cb, root, predicateBuilders)))).
                getSingleResult();
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // /**
    // * @param predicateBuilders
    // * Restricting query conditions. If you supply more than one predicate, they will be
    // * joined by conjunction.
    // */
    protected void deleteWhere(final PredicateBuilder<T>... predicateBuilders) {

        final CriteriaBuilder cb = this.getEntityManger().
                getCriteriaBuilder();
        final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityType);
        final Root<T> root = delete.from(this.entityType);

        if ((predicateBuilders != null) && (predicateBuilders.length > 0))
            delete.where(this.buildPredicates(cb, root, predicateBuilders));

        this.getEntityManger().
                createQuery(delete).
                executeUpdate();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Predicate[] buildPredicates(final CriteriaBuilder cb, final Root<T> root,
            final PredicateBuilder<T>... predicateBuilders) {

        final List<Predicate> predicates = new LinkedList<>();
        if ((predicateBuilders != null) && (predicateBuilders.length > 0))
            for (final PredicateBuilder<T> builder : predicateBuilders)
                predicates.add(builder.build(cb, root));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    // -----------------------------------------------------------------------------------------------------------------
}

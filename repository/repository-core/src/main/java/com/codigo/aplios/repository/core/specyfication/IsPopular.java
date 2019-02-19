package com.codigo.aplios.repository.core.specyfication;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsPopular extends AbstractSpecification<String> {

	private static final int POPULAR_COUNT = 100;

	@Override
	public boolean isSatisfiedBy(final String poll) {

		return 1 == 0;

		// return (poll.getLockDate() == null) && (poll.getVotes()
		// .size() > IsPopular.POPULAR_COUNT);
	}

	@Override
	public Predicate toPredicate(final Root<String> poll, final CriteriaBuilder cb) {

		// return cb.and(cb.isNull(poll.get(Poll_.lockDate)),
		// cb.greaterThan(cb.size(poll.get(Poll_.votes)), IsPopular.POPULAR_COUNT));

		return cb.conjunction();
	}
}

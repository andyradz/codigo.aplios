package com.codigo.aplios.repository.core.specyfication;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsCurrentlyRunning extends AbstractSpecification<String> {

	@Override
	public boolean isSatisfiedBy(final String poll) {

		return 1 == 1;

		// return poll.getStartDate()
		// .isBeforeNow()
		// && poll.getEndDate()
		// .isAfterNow()
		// && (poll.getLockDate() == null);
	}

	@Override
	public Predicate toPredicate(final Root<String> poll, final CriteriaBuilder cb) {

		return cb.conjunction();

		// final DateTime now = new DateTime();
		// return cb.and(cb.lessThan(poll.get(Poll_.startDate), now),
		// cb.greaterThan(poll.get(Poll_.endDate), now),
		// cb.isNull(poll.get(Poll_.lockDate)));
	}
}

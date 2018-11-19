package com.codigo.aplios.domain.model.evidence.comparator;

import java.util.Comparator;

import com.codigo.aplios.domain.model.common.Person;

/**
 * Klasa realizuje porównywanie dwóch obiektów typu Person. Obiekt jest porównywany po własciwości
 * Wiek.
 *
 * @author andrzej.radziszewski
 */
public final class PersonByAgeComparator implements Comparator<Person> {

	public PersonByAgeComparator() {

	}

	@Override
	public int compare(final Person arg0, final Person arg1) {

		return Integer.compare(arg0.getAge(), arg1.getAge());
	}
}

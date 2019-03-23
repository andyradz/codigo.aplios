package com.codigo.aplios.group.timeline.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

public final class ExceptionMessageMatcher<E extends Throwable>
		extends TypeSafeMatcher<E> {

	private final Matcher<? super String> matcher;

	private ExceptionMessageMatcher(final Matcher<String> matcher) {

		this.matcher = matcher;
	}

	public static <E extends Throwable> Matcher<E> exceptionMessage(final String message) {

		return new ExceptionMessageMatcher<>(
				Matchers.is(message));
	}

	@Override
	protected boolean matchesSafely(final E ex) {

		return this.matcher.matches(ex.getMessage());
	}

	@Override
	public void describeTo(final Description description) {

		description.appendDescriptionOf(this.matcher);
	}

}

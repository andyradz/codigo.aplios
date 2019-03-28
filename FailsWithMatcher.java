package com.codigo.aplios.group.sdk.core.hamcrest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class FailsWithMatcher<E extends Throwable>
		extends TypeSafeMatcher<IThrowingRunnable<E>> {

	private final Matcher<? super E> matcher;

	private FailsWithMatcher(final Matcher<? super E> matcher) {

		this.matcher = matcher;
	}

	public static <E extends Throwable> Matcher<IThrowingRunnable<E>> failsWith(final Class<E> throwableType) {

		return new FailsWithMatcher<>(
				CoreMatchers.instanceOf(throwableType));
	}

	public static <E extends Throwable> Matcher<IThrowingRunnable<E>> failsWith(final Class<E> throwableType,
			final Matcher<? super E> throwableMatcher) {

		return new FailsWithMatcher<>(
				CoreMatchers.allOf(CoreMatchers.instanceOf(throwableType), throwableMatcher));
	}

	@Override
	protected boolean matchesSafely(final IThrowingRunnable<E> runnable) {

		try {
			runnable.run();
			return false;
		} catch (final Throwable ex) {
			return this.matcher.matches(ex);
		}
	}

	@Override
	public void describeTo(final Description description) {

		description.appendText("fails with ")
			.appendDescriptionOf(this.matcher);
	}

}
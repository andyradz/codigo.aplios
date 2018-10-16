package com.codigo.aplios.sdk.core.junit.matchers;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Klasa realizuje mechanizm sprawdzający warunku czy ciąg znaków zawiera dokładnie jedno
 * wystapienie wskazanego ciągu znaków. Klasa korzysta z mechanizmu Hamcrest
 *
 * @author andrzej.radziszewski
 * @category testing
 */
public final class ExactCountMatcher extends TypeSafeDiagnosingMatcher<List<String>> {

	private final Matcher<String> stringMatcher;

	private final int expectedCount;

	private ExactCountMatcher(final Matcher<String> stringMatcher, final int expectedCount) {
		this.stringMatcher = stringMatcher;
		this.expectedCount = expectedCount;
	}

	@Override
	protected boolean matchesSafely(final List<String> items, final Description mismatchDescriptor) {
		int count = 0;

		for (final String item : items)
			if (this.stringMatcher.matches(item))
				count++;

		final boolean okay = count == this.expectedCount;

		if (!okay) {
			mismatchDescriptor.appendText("was matched " + count + " times in the following list:");
			final String separator = String.format("%n") + "          ";
			mismatchDescriptor.appendValueList(separator, separator, "", items);

		}
		return okay;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendDescriptionOf(this.stringMatcher)
				.appendText(" " + this.expectedCount + " times");
	}

	@Factory
	public static Matcher<? super List<String>> hasNoLinesThat(final Matcher<String> stringMatcher) {
		return new ExactCountMatcher(
			stringMatcher, 0);
	}

	@Factory
	public static Matcher<? super List<String>> hasOneLineThat(final Matcher<String> stringMatcher) {
		return new ExactCountMatcher(
			stringMatcher, 1);
	}

}

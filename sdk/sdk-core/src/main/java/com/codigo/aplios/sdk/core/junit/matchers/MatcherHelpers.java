package com.codigo.aplios.sdk.core.junit.matchers;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

/**
 * @author andrzej.radziszewski
 * @category testing
 */
public class MatcherHelpers {

	public static Matcher<String> length(final Matcher<? super Integer> matcher) {
		return new FeatureMatcher<String, Integer>(
			matcher, "a String of length that", "length") {
			@Override
			protected Integer featureValueOf(final String actual) {
				return actual.length();
			}

		};
	}

}

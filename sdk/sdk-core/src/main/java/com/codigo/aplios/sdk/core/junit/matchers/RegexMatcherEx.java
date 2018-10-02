package com.codigo.aplios.sdk.core.junit.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public final class RegexMatcherEx
        extends TypeSafeMatcher<String> {

    private final String regex;

    public RegexMatcherEx(final String regex) {
        this.regex = regex;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches regular expression=`" + this.regex + "`");
    }

    @Override
    public boolean matchesSafely(final String string) {
        return string.matches(this.regex);
    }

    // matcher method you can call on this matcher class
    public static RegexMatcherEx matchesRegex(final String regex) {
        return new RegexMatcherEx(regex);
    }

    // private Matcher<Element> getAllOfMatcher(List<Matcher<? super Element>>
    // matchers) {
    // return CoreMatchers.allOf(matchers);
    // }
}

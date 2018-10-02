package com.codigo.aplios.sdk.core.junit.matchers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class MatcherCombinator<T>
        extends BaseMatcher<T> {

    private final List<Matcher<? super T>> matchers = new ArrayList<>();

    private final List<Matcher<? super T>> failedMatchers = new ArrayList<>();

    private MatcherCombinator(final Matcher<? super T> matcher) {
        this.matchers.add(matcher);
    }

    public MatcherCombinator<T> and(final Matcher<? super T> matcher) {
        this.matchers.add(matcher);
        return this;
    }

    @Override
    public boolean matches(final Object item) {
        boolean matchesAllMatchers = true;
        for (final Matcher<? super T> matcher : this.matchers)
            if (!matcher.matches(item)) {
                this.failedMatchers.add(matcher);
                matchesAllMatchers = false;
            }
        return matchesAllMatchers;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendValueList("\n",
                " " + "and" + "\n",
                "",
                this.matchers);
    }

    @Override
    public void describeMismatch(final Object item, final Description description) {
        description.appendText("\n");
        for (final Iterator<Matcher<? super T>> iterator = this.failedMatchers.iterator(); iterator.hasNext();) {
            final Matcher<? super T> matcher = iterator.next();
            description.appendText("Expected: <");
            description.appendDescriptionOf(matcher)
                    .appendText(" but ");
            matcher.describeMismatch(item,
                    description);
            if (iterator.hasNext())
                description.appendText(">\n");
        }
    }

    public static <LHS> MatcherCombinator<LHS> matches(final Matcher<? super LHS> matcher) {
        return new MatcherCombinator<>(matcher);
    }

}

package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.collect.Iterables;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public final class AssertionUtil {

    private AssertionUtil() {
    }

    public static <T> TypeSafeMatcher<Iterable<T>> equalToList(final T... values) {
        return new TypeSafeMatcher<Iterable<T>>() {
            @Override
            protected final boolean matchesSafely(final Iterable<T> iterable) {
                final List<T> itemList = newArrayList(iterable);
                return itemList.equals(newArrayList(values));
            }

            @Override
            public final void describeTo(Description description) {
                description.appendText("equal to list " + Arrays.toString(values));
            }
        };
    }

    public static TypeSafeMatcher<Iterable<List<Integer>>> empty() {
        return new TypeSafeMatcher<Iterable<List<Integer>>>() {
            @Override
            protected final boolean matchesSafely(final Iterable<List<Integer>> iterable) {
                return Iterables.isEmpty(iterable);
            }

            @Override
            public final void describeTo(Description description) {
                description.appendText("is empty");
            }
        };
    }
}

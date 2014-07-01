package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.collect.Iterables;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.guillaumenargeot.ngramsfrequency.IterableUtil.clumps;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IterableUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowExceptionWhenCalledWithZeroSize() {
        clumps(asList(2, 3, 5), 0);
    }

    @Test
    public final void testClumps() throws Exception {
        assertThat(clumps(asList(2, 3), 1), is(equalToList(
                asList(2),
                asList(3))));
        assertThat(clumps(asList(2, 3), 2), is(equalToList(asList(2, 3))));
        assertThat(clumps(asList(2, 3), 3), is(empty()));

        assertThat(clumps(asList(2, 3, 5, 7, 11), 1), is(equalToList(
                asList(2),
                asList(3),
                asList(5),
                asList(7),
                asList(11))));
        assertThat(clumps(asList(2, 3, 5, 7, 11), 2), is(equalToList(
                asList(2, 3),
                asList(3, 5),
                asList(5, 7),
                asList(7, 11))));
        assertThat(clumps(asList(2, 3, 5, 7, 11), 3), is(equalToList(
                asList(2, 3, 5),
                asList(3, 5, 7),
                asList(5, 7, 11))));
        assertThat(clumps(asList(2, 3, 5, 7, 11), 4), is(equalToList(
                asList(2, 3, 5, 7),
                asList(3, 5, 7, 11))));
        assertThat(clumps(asList(2, 3, 5, 7, 11), 5), is(equalToList(asList(2, 3, 5, 7, 11))));
        assertThat(clumps(asList(2, 3, 5, 7, 11), 6), is(empty()));
    }

    private static <T> TypeSafeMatcher<Iterable<T>> equalToList(final T... values) {
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

    private static TypeSafeMatcher<Iterable<List<Integer>>> empty() {
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

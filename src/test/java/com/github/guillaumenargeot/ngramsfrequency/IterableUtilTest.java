package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import static com.github.guillaumenargeot.ngramsfrequency.AssertionUtil.empty;
import static com.github.guillaumenargeot.ngramsfrequency.AssertionUtil.equalToList;
import static com.github.guillaumenargeot.ngramsfrequency.collection.IterableUtil.clumps;
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
}

package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class MergedNGramTest {

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowAnExceptionWhenInstantiatedWithNullValue() {
        new MergedNGram(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowAnExceptionWhenInstantiatedWithNullZeroCardinality() {
        new MergedNGram("abc", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowAnExceptionWhenInstantiatedWithValueIncludingLeadingSpaces() {
        new MergedNGram(" bc", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowAnExceptionWhenInstantiatedWithValueIncludingTrailingSpaces() {
        new MergedNGram("ab ", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void shouldThrowAnExceptionWhenInstantiatedWithCardinalityNotMatchingValue() {
        new MergedNGram("abc def ghi", 2);
    }

    @Test
    public final void testMergedNGram() {
        assertThat(MergedNGram.of(asList("abc")).value(), is(equalTo("abc")));
        assertThat(MergedNGram.of(asList("abc")).cardinality(), is(equalTo(1)));

        assertThat(MergedNGram.of(asList("abc", "def", "ghi")).value(), is(equalTo("abc def ghi")));
        assertThat(MergedNGram.of(asList("abc", "def", "ghi")).cardinality(), is(equalTo(3)));
    }
}
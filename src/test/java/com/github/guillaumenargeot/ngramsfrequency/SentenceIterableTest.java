package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import static com.github.guillaumenargeot.ngramsfrequency.AssertionUtil.equalToList;
import static com.github.guillaumenargeot.ngramsfrequency.Sentence.Functions.intoValue;
import static com.google.common.collect.Iterables.transform;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SentenceIterableTest {

    @Test
    public final void testSentenceIterable() {
        assertThat(transform(SentenceIterable.of("a, b; c, d."), intoValue()), is(equalToList("a", "b", "c", "d")));
    }
}
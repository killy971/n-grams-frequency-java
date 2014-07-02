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
        assertThat(transform(SentenceIterable.of("42"), intoValue()), is(equalToList("42")));
        assertThat(transform(SentenceIterable.of("2, 3; 5, 7. 11"), intoValue()), is(equalToList("2", "3", "5", "7", "11")));
    }
}
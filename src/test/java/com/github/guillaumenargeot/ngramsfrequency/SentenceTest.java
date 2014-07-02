package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import static com.github.guillaumenargeot.ngramsfrequency.AssertionUtil.equalToList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SentenceTest {

    @Test
    public final void testSentence() {
        assertThat(Sentence.of("23 57 1113").words(), is(equalToList("23", "57", "1113")));
    }
}
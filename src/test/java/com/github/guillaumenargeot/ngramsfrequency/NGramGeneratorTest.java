package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class NGramGeneratorTest {

    /**
     * From Paracelsus, by Robert Browning
     */
    private static final Iterable<String> lines = asList(
            "At times I almost dream.",
            "I too have spent a life the sage's way,",
            "And tread once more familiar paths.",
            "Perchance",
            "I perished in an arrogant self-reliance",
            "Ages ago; and in that act, a prayer.",
            "For one more chance went up so earnest, so",
            "Instinct with better light let in by death,",
            "That life was blotted out - not so completely",
            "But scattered wrecks enough of it remain,",
            "Dim memories, as now, when once more seems",
            "The goal in sight again."
    );
    // Simpler format, where a line cannot be a partial sentence, until SentenceIterable is improved.
    private static final Iterable<String> simpleLines = Collections.singletonList(
            Joiner.on(' ').join(lines)
    );

    @Test
    public final void testFromLines() {
        final Iterable<NGram> nGramsCard2 = NGramGenerator.fromLines(simpleLines, 2);
        assertThat(Iterables.get(nGramsCard2, 0).value(), is(equalTo("at times")));
        assertThat(Iterables.get(nGramsCard2, 1).value(), is(equalTo("times i")));
        assertThat(Iterables.get(nGramsCard2, 2).value(), is(equalTo("i almost")));
        assertThat(Iterables.get(nGramsCard2, 3).value(), is(equalTo("almost dream")));
        assertThat(Iterables.get(nGramsCard2, 4).value(), is(equalTo("i too")));
        assertThat(Iterables.size(nGramsCard2), is(equalTo(69)));

        final Iterable<NGram> nGramsCard3 = NGramGenerator.fromLines(simpleLines, 3);
        assertThat(Iterables.get(nGramsCard3, 0).value(), is(equalTo("at times i")));
        assertThat(Iterables.get(nGramsCard3, 1).value(), is(equalTo("times i almost")));
        assertThat(Iterables.get(nGramsCard3, 2).value(), is(equalTo("i almost dream")));
        assertThat(Iterables.get(nGramsCard3, 3).value(), is(equalTo("i too have")));
        assertThat(Iterables.size(nGramsCard3), is(equalTo(57)));
    }
}
package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import static com.github.guillaumenargeot.ngramsfrequency.AssertionUtil.equalToList;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class FrequencyMapTest {

    @Test
    public final void testFactory() {
        final FrequencyMap<Integer> frequencyMap = FrequencyMap.of(
                asList(5, 2, 2, 2, 2, 3, 2, 7, 3, 5, 5, 5, 5, 5, 7, 7, 5)
        );

        final Iterable<Integer> frequencies = frequencyMap.frequenciesOf(asList(2, 3, 5, 7, 11));

        assertThat(frequencies, is(equalToList(5, 2, 7, 3, 0)));
    }

    @Test
    public final void testMerge() {
        final FrequencyMap<Integer> frequencyMap1 = FrequencyMap.of(
                asList(5, 2, 2, 2, 2, 3, 2, 7, 3, 5, 5, 5, 5, 5, 7, 7, 5)
        );
        final FrequencyMap<Integer> frequencyMap2 = FrequencyMap.of(
                asList(7, 11, 5, 5, 2, 2, 5)
        );
        final FrequencyMap<Integer> frequencyMap3 = FrequencyMap.of(
                asList(3, 5, 7, 11)
        );

        final FrequencyMap<Integer> frequencyMap = FrequencyMap.merge(frequencyMap1, frequencyMap2, frequencyMap3);

        final Iterable<Integer> frequencies = frequencyMap.frequenciesOf(asList(2, 3, 5, 7, 11));

        assertThat(frequencies, is(equalToList(7, 3, 11, 5, 2)));
    }
}
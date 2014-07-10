package com.github.guillaumenargeot.ngramsfrequency;

import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.Iterables.get;
import static java.util.Arrays.asList;

public final class NGramsFrequencyTest {

    @Test
    public final void testNGramFrequencies() {
        final Iterable<String> lines = asList("i j k a b c d e f c d e b c d e f a b c c d e a b c c d e h i f g h i j k c d e");

        final Iterable<Map.Entry<NGram, Integer>> topThreeBigrams = NGramsFrequency.fromLines(lines, 2).topK(3);
        System.out.println(get(topThreeBigrams, 0).getValue() + " : " + get(topThreeBigrams, 0).getKey());
        System.out.println(get(topThreeBigrams, 1).getValue() + " : " + get(topThreeBigrams, 1).getKey());
        System.out.println(get(topThreeBigrams, 2).getValue() + " : " + get(topThreeBigrams, 2).getKey());

        final Iterable<Map.Entry<NGram, Integer>> topThreeTrigrams = NGramsFrequency.fromLines(lines, 3).topK(3);
        System.out.println(get(topThreeTrigrams, 0).getValue() + " : " + get(topThreeTrigrams, 0).getKey());
        System.out.println(get(topThreeTrigrams, 1).getValue() + " : " + get(topThreeTrigrams, 1).getKey());
        System.out.println(get(topThreeTrigrams, 2).getValue() + " : " + get(topThreeTrigrams, 2).getKey());
    }
}
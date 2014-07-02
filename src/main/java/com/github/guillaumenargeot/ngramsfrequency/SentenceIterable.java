package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import java.util.Iterator;

import static java.util.Arrays.asList;

public final class SentenceIterable implements Iterable<Sentence> {

    private final String line;

    private SentenceIterable(final String line) {
        this.line = line;
    }

    public static Iterable<Sentence> of(final String line) {
        return new SentenceIterable(line);
    }

    @Override
    public final Iterator<Sentence> iterator() {
        return Iterables.transform(asList(line.split("[.;,]")), new Function<String, Sentence>() {
            @Override
            public final Sentence apply(final String input) {
                return Sentence.of(input);
            }
        }).iterator();
    }
}

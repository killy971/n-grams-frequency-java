package com.github.guillaumenargeot.ngramsfrequency.string;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import java.util.Iterator;

import static java.util.Arrays.asList;

public final class SentenceIterable implements Iterable<Sentence> {

    private final Iterable<String> lines;

    private SentenceIterable(final Iterable<String> lines) {
        this.lines = lines;
    }

    /**
     * For now, assume that each line is a sentence, or multiple sentences: a line cannot be part of a sentence.
     */
    public static Iterable<Sentence> of(final Iterable<String> lines) {
        return new SentenceIterable(lines);
    }

    @Override
    public final Iterator<Sentence> iterator() {
        return FluentIterable.from(lines).transformAndConcat(new Function<String, Iterable<Sentence>>() {
            @Override
            public Iterable<Sentence> apply(final String input) {
                return Iterables.transform(asList(input.split("[.;,]")), new Function<String, Sentence>() {
                    @Override
                    public final Sentence apply(final String input) {
                        return Sentence.of(input);
                    }
                });
            }
        }).iterator();
    }

    public static final class Functions {
        public static Function<Iterable<String>, Iterable<Sentence>> intoSentenceIterable() {
            return new Function<Iterable<String>, Iterable<Sentence>>() {
                @Override
                public final Iterable<Sentence> apply(final Iterable<String> input) {
                    return SentenceIterable.of(input);
                }
            };
        }
    }
}

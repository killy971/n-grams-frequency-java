package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotNullArgument;

public final class Sentence {

    private final String value;

    private Sentence(final String value) {
        this.value = value.trim();
    }

    public static Sentence of(final String value) {
        checkNotNullArgument(value, "value");
        return new Sentence(value);
    }

    public final String value() {
        return value;
    }

    public final Iterable<String> words() {
        return Iterables.transform(ImmutableList.copyOf(value.split(" ")), new Function<String, String>() {
            @Override
            public final String apply(final String word) {
                return word.trim();
            }
        });
    }

    public static final class Functions {
        public static Function<Sentence, String> intoValue() {
            return new Function<Sentence, String>() {
                @Override
                public String apply(final Sentence input) {
                    return input.value();
                }
            };
        }
    }
}

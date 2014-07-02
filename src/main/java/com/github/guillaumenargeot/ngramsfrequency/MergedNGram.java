package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotEmptyArgument;
import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkStrictlyPositiveArgument;
import static com.google.common.collect.Iterables.size;

public final class MergedNGram implements NGram {
    private static final char SPACE = ' ';
    private static final Joiner JOINER = Joiner.on(SPACE);
    private final String value;
    private final int cardinality;

    public MergedNGram(final String value, final int cardinality) {
        checkNotEmptyArgument(value, "value");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        checkNoLeadingAndTrailingSpaces(value);
        checkCardinality(value, cardinality);
        this.value = value;
        this.cardinality = cardinality;
    }

    private MergedNGram(final Iterable<String> values) {
        this.value = JOINER.join(values);
        this.cardinality = size(values);
    }

    public static NGram of(final Iterable<String> values) {
        checkNotEmptyArgument(values, "values");
        return new MergedNGram(values);
    }

    @Override
    public final String value() {
        return value;
    }

    @Override
    public final int cardinality() {
        return cardinality;
    }

    private static void checkNoLeadingAndTrailingSpaces(final String value) {
        if (value.startsWith(" ")) {
            throw new IllegalArgumentException("value contains leading space(s)");
        }
        if (value.endsWith(" ")) {
            throw new IllegalArgumentException("value contains trailing space(s)");
        }
    }

    private static void checkCardinality(final String value, final int cardinality) {
        final int actualCardinality = size(Splitter.on(' ').split(value));
        if (cardinality != actualCardinality) {
            throw new IllegalArgumentException(String.format("Incorrect cardinality: %d."
                            + "Actual cardinality is %d.",
                    cardinality, actualCardinality));
        }
    }
}

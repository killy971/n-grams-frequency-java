package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import java.util.HashMap;
import java.util.Map;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkStrictlyPositiveArgument;
import static java.util.Arrays.asList;

public final class FrequencyMap<T> {

    private final Map<T, Integer> frequencies = new HashMap<>();

    public static <T> FrequencyMap<T> of(final Iterable<T> values) {
        checkNotNullArgument(values, "values");
        final FrequencyMap<T> frequencyMap = new FrequencyMap<>();
        frequencyMap.increaseFrequencies(values);
        return frequencyMap;
    }

    public static <T> FrequencyMap<T> merge(final FrequencyMap<T>... frequencyMaps) {
        checkNotNullArgument(frequencyMaps, "frequencyMaps");
        return merge(asList(frequencyMaps));
    }

    public static <T> FrequencyMap<T> merge(final Iterable<FrequencyMap<T>> frequencyMaps) {
        checkNotNullArgument(frequencyMaps, "frequencyMaps");
        final FrequencyMap<T> mergedFrequencyMap = new FrequencyMap<>();
        for (final FrequencyMap<T> frequencyMap : frequencyMaps) {
            for (final Map.Entry<T, Integer> frequencyEntry : frequencyMap.frequencyEntries())
                mergedFrequencyMap.increaseFrequency(frequencyEntry.getKey(), frequencyEntry.getValue());
        }
        return mergedFrequencyMap;
    }

    public final void increaseFrequency(final T value) {
        checkNotNullArgument(value, "value");
        increaseFrequency(value, 1);
    }

    public final void increaseFrequency(final T value, final int frequencyIncrease) {
        checkNotNullArgument(value, "value");
        checkStrictlyPositiveArgument(frequencyIncrease, "frequencyIncrease");
        final Integer frequency = frequencies.get(value);
        frequencies.put(value, frequency == null ? frequencyIncrease : frequency + frequencyIncrease);
    }

    public final void increaseFrequencies(final Iterable<T> values) {
        checkNotNullArgument(values, "values");
        for (final T value : values) {
            increaseFrequency(value);
        }
    }

    public final int frequencyOf(final T value) {
        checkNotNullArgument(value, "value");
        Integer frequency = frequencies.get(value);
        return frequency == null ? 0 : frequency;
    }

    public final Iterable<Integer> frequenciesOf(final Iterable<T> values) {
        checkNotNullArgument(values, "values");
        return Iterables.transform(values, intoFrequency());
    }

    public final Iterable<Map.Entry<T, Integer>> frequencyEntries() {
        return frequencies.entrySet();
    }

    private Function<T, Integer> intoFrequency() {
        return new Function<T, Integer>() {
            @Override
            public final Integer apply(final T value) {
                checkNotNullArgument(value, "value");
                return frequencyOf(value);
            }
        };
    }
}

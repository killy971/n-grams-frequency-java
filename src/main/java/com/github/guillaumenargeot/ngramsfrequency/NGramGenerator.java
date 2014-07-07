package com.github.guillaumenargeot.ngramsfrequency;

import com.github.guillaumenargeot.ngramsfrequency.string.Sentence;
import com.github.guillaumenargeot.ngramsfrequency.string.SentenceIterable;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.List;

import static com.github.guillaumenargeot.ngramsfrequency.MergedNGram.Functions.intoMergedNGram;
import static com.github.guillaumenargeot.ngramsfrequency.collection.IterableUtil.intoClumps;
import static com.github.guillaumenargeot.ngramsfrequency.string.Sentence.Functions.intoWords;
import static com.github.guillaumenargeot.ngramsfrequency.util.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.util.Preconditions.checkStrictlyPositiveArgument;
import static com.github.guillaumenargeot.ngramsfrequency.util.StringUtil.intoLowerCase;
import static com.google.common.collect.Iterables.transform;

public final class NGramGenerator {

    private NGramGenerator() {
    }

    public static Iterable<NGram> fromLines(final Iterable<String> lines, final int cardinality) {
        checkNotNullArgument(lines, "lines");
        checkStrictlyPositiveArgument(cardinality, "cardinality");

        final Function<Iterable<String>, Iterable<List<String>>> stringsIntoClumps = intoClumps(cardinality);

        final Iterable<Sentence> sentences = SentenceIterable.of(transform(lines, intoLowerCase()));
        return FluentIterable.from(sentences)
                .transform(intoWords())
                .transformAndConcat(stringsIntoClumps)
                .transform(intoMergedNGram());
    }
}

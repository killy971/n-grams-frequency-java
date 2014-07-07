package com.github.guillaumenargeot.ngramsfrequency;

import com.github.guillaumenargeot.ngramsfrequency.util.FileLineIterable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

import static com.github.guillaumenargeot.ngramsfrequency.util.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.util.Preconditions.checkStrictlyPositiveArgument;

public final class NGramsFrequency {

    private static final Logger logger = LogManager.getLogger(NGramGenerator.class);

    public static FrequencyMap<NGram> fromLines(final Iterable<String> lines, final int cardinality) {
        checkNotNullArgument(lines, "lines");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        final Iterable<NGram> nGrams = NGramGenerator.fromLines(lines, cardinality);
        return FrequencyMap.of(nGrams);
    }

    public static FrequencyMap<NGram> fromFile(final String filename, final int cardinality) {
        checkNotNullArgument(filename, "filename");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        try (final FileLineIterable lines = new FileLineIterable(filename)) {
            return fromLines(lines, cardinality);
        } catch (Exception e) {
            logger.error("An exception occurred while reading the file [" + filename + "]", e);
            return fromLines(Collections.<String>emptyList(), cardinality);
        }
    }
}

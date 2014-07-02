package com.github.guillaumenargeot.ngramsfrequency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkStrictlyPositiveArgument;

public final class NGramGenerator {

    private static final Logger logger = LogManager.getLogger(NGramGenerator.class);

    public static Iterable<NGram> fromLines(final Iterable<String> lines, final int cardinality) {
        checkNotNullArgument(lines, "lines");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        return Collections.emptyList();
    }

    public static Iterable<NGram> fromFile(final String filename, final int cardinality) {
        checkNotNullArgument(filename, "filename");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        try (final FileLineIterable lines = new FileLineIterable(filename)) {
            return fromLines(lines, cardinality);
        } catch (Exception e) {
            logger.error("An exception occurred while reading the file [" + filename + "]", e);
            return Collections.emptyList();
        }
    }
}

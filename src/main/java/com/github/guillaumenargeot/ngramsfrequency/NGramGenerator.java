package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.collect.AbstractIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkStrictlyPositiveArgument;

public final class NGramGenerator {

    private static final Logger logger = LogManager.getLogger(NGramGenerator.class);

    public static Iterable<NGram> fromLines(final Iterable<String> lines, final int cardinality) {
        checkNotNullArgument(lines, "lines");
        checkStrictlyPositiveArgument(cardinality, "cardinality");
        return new NGramIterable(lines, cardinality);
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

    private static final class NGramIterable implements Iterable<NGram> {

        private final Iterable<String> lines;
        private final int cardinality;

        private NGramIterable(final Iterable<String> lines, final int cardinality) {
            this.lines = lines;
            this.cardinality = cardinality;
        }

        @Override
        public final Iterator<NGram> iterator() {
            return new NGramIterator(lines.iterator(), cardinality);
        }

        private static final class NGramIterator extends AbstractIterator<NGram> {

            private final Iterator<String> lines;
            private final int cardinality;

            private NGramIterator(final Iterator<String> lines, final int cardinality) {
                this.lines = lines;
                this.cardinality = cardinality;
            }

            @Override
            protected final NGram computeNext() {
                return endOfData();
            }
        }
    }
}

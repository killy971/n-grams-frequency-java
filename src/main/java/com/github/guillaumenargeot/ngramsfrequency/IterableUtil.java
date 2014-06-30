package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.collect.AbstractIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkNotNullArgument;
import static com.github.guillaumenargeot.ngramsfrequency.Preconditions.checkStrictlyPositiveArgument;

public final class IterableUtil {

    private IterableUtil() {
    }

    public static <T> Iterable<List<T>> clumps(final Iterable<T> values, final int size) {
        checkNotNullArgument(values, "values");
        checkStrictlyPositiveArgument(size, "size");
        return new ClumpIterable<>(values, size);
    }

    private static final class ClumpIterable<T> implements Iterable<List<T>> {

        private final Iterable<T> values;
        private final int size;

        private ClumpIterable(final Iterable<T> values, final int size) {
            this.values = values;
            this.size = size;
        }

        @Override
        public final Iterator<List<T>> iterator() {
            return new ClumpIterator(values.iterator());
        }

        private final class ClumpIterator extends AbstractIterator<List<T>> {

            private final List<T> currentClump = new LinkedList<>();
            private final Iterator<T> iterator;

            private ClumpIterator(final Iterator<T> iterator) {
                this.iterator = iterator;
            }

            @Override
            protected final List<T> computeNext() {
                while (currentClump.size() < size - 1) {
                    if (!iterator.hasNext()) {
                        return endOfData();
                    }
                    currentClump.add(iterator.next());
                }
                if (!iterator.hasNext()) {
                    return endOfData();
                }
                currentClump.add(iterator.next());
                final List<T> clump = new ArrayList<>(currentClump);
                currentClump.remove(0);
                return clump;
            }
        }
    }
}

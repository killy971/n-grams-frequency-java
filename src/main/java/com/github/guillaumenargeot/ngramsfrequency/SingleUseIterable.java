package com.github.guillaumenargeot.ngramsfrequency;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.common.base.Preconditions.checkArgument;

public final class SingleUseIterable<T> implements Iterable<T> {

    private final AtomicBoolean used = new AtomicBoolean(false);
    private final Iterator<T> iterator;

    public SingleUseIterable(final Iterator<T> iterator) {
        checkArgument(iterator != null, "iterator is null");
        this.iterator = iterator;
    }

    @Override
    public final Iterator<T> iterator() {
        if (used.compareAndSet(false, true)) {
            return iterator;
        } else {
            throw new UnsupportedOperationException("iterator() can only be called once");
        }
    }
}
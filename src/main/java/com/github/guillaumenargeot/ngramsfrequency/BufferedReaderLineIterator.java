package com.github.guillaumenargeot.ngramsfrequency;

import com.google.common.collect.AbstractIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkArgument;

public final class BufferedReaderLineIterator extends AbstractIterator<String> {

    private final BufferedReader bufferedReader;

    public BufferedReaderLineIterator(final BufferedReader bufferedReader) {
        checkArgument(bufferedReader != null, "buffered reader is null");
        this.bufferedReader = bufferedReader;
    }


    @Override
    protected final String computeNext() {
        final String line;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new NoSuchElementException(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        if (line != null) {
            return line;
        }
        return endOfData();
    }
}

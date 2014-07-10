package com.github.guillaumenargeot.ngramsfrequency;

public interface NGram extends Comparable<NGram> {
    String value();

    int cardinality();
}

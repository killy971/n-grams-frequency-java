package com.github.guillaumenargeot.ngramsfrequency.util;

import com.google.common.base.Function;

public final class StringUtil {
    private StringUtil() {
    }

    public static Function<String, String> intoLowerCase() {
        return new Function<String, String>() {
            @Override
            public final String apply(final String input) {
                return input.toLowerCase();
            }
        };
    }
}

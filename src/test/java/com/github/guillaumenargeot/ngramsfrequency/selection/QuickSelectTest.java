package com.github.guillaumenargeot.ngramsfrequency.selection;

import org.junit.Test;

import java.util.List;

import static com.github.guillaumenargeot.ngramsfrequency.selection.QuickSelect.selectTopK;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public final class QuickSelectTest {
    @Test
    public final void testSelect1() {
        final List<Integer> list = asList(11, 3, 2, 5, 19, 7, 23, 13, 17);

        assertEquals(asList(23), selectTopK(list, 1));
        assertEquals(asList(23, 19), selectTopK(list, 2));
        assertEquals(asList(23, 19, 17), selectTopK(list, 3));
        assertEquals(asList(23, 19, 17, 13), selectTopK(list, 4));
        assertEquals(asList(23, 19, 17, 13, 11), selectTopK(list, 5));
        assertEquals(asList(23, 19, 17, 13, 11, 7), selectTopK(list, 6));
        assertEquals(asList(23, 19, 17, 13, 11, 7, 5), selectTopK(list, 7));
        assertEquals(asList(23, 19, 17, 13, 11, 7, 5, 3), selectTopK(list, 8));
        assertEquals(asList(23, 19, 17, 13, 11, 7, 5, 3, 2), selectTopK(list, 9));
    }

    @Test
    public final void testSelect2() {
        final List<Integer> list = asList(11, 3, 19, 11, 11, 19, 2, 5, 17, 3, 19, 7, 5, 23, 3, 13, 11, 17);

        assertEquals(asList(23), selectTopK(list, 1));
        assertEquals(asList(23, 19), selectTopK(list, 2));
        assertEquals(asList(23, 19, 19), selectTopK(list, 3));
        assertEquals(asList(23, 19, 19, 19), selectTopK(list, 4));
        assertEquals(asList(23, 19, 19, 19, 17), selectTopK(list, 5));
        assertEquals(asList(23, 19, 19, 19, 17, 17), selectTopK(list, 6));
        assertEquals(asList(23, 19, 19, 19, 17, 17, 13), selectTopK(list, 7));
    }
}

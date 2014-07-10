package com.github.guillaumenargeot.ngramsfrequency.selection;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

public final class QuickSelect {

    private static final Random random = new Random(0);

    public static <T extends Comparable<T>> List<T> selectTopK(final Iterable<T> originalList, final int topK) {
        final List<T> list = newArrayList(originalList);
        final int k = topK - 1;
        int left = 0;
        int right = list.size() - 1;
        if (left == right) {
            return copySorted(list.subList(left, right + 1));
        }
        while (true) {
            final int randomPivotIndex = left + random.nextInt(right - left + 1);
            final int pivotIndex = partition(list, left, right, randomPivotIndex);
            if (k == pivotIndex) {
                return copySorted(list.subList(0, k + 1));
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static <T extends Comparable<T>> int partition(final List<T> list, final int left, final int right, final int pivotIndex) {
        final T pivotValue = list.get(pivotIndex);

        // move pivot to the end
        final T rightValue = list.get(right);
        list.set(right, pivotValue);
        list.set(pivotIndex, rightValue);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (list.get(i).compareTo(pivotValue) > 0) {
                final T storeValue = list.get(storeIndex);
                list.set(storeIndex, list.get(i));
                list.set(i, storeValue);
                storeIndex++;
            }
        }

        // move pivot to its final place
        list.set(right, list.get(storeIndex));
        list.set(storeIndex, pivotValue);
        return storeIndex;
    }

    private static <T extends Comparable<T>> List<T> copySorted(final Iterable<T> unsortedList) {
        final List<T> sortedList = newArrayList(unsortedList);
        Comparator<T> inverseComparator = new Comparator<T>() {
            @Override
            public final int compare(final T o1, final T o2) {
                return -o1.compareTo(o2);
            }
        };
        Collections.sort(sortedList, inverseComparator);
        return sortedList;
    }
}

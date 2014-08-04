package com.github.guillaumenargeot.ngramsfrequency.selection;

import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public final class QuickSelect {

    private QuickSelect() {
    }

    public static <T extends Comparable<T>> List<T> selectTopK(final Iterable<T> originalList, final int topK) {
        final Comparator<T> comparator = comparator();
        return selectTopK(originalList, topK, comparator);
    }

    public static <T> List<T> selectTopK(final Iterable<T> originalList, final int topK, final Comparator<T> comparator) {
        final List<T> list = newArrayList(originalList);
        final int k = topK - 1;
        int left = 0;
        int right = list.size() - 1;
        if (left == right) {
            return asList(list.get(0));
        }
        while (true) {
            final int candidatePivotIndex = simpleMedianPivotIndex(list, left, right, comparator);
            final int pivotIndex = partition(list, left, right, candidatePivotIndex, comparator);
            if (k == pivotIndex) {
                return copySorted(list.subList(0, k + 1), comparator);
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static <T> int simpleMedianPivotIndex(final List<T> list, final int left, final int right, final Comparator<T> comparator) {
        final int mid = (left + right) / 2;
        if (comparator.compare(list.get(left), list.get(mid)) > 1) {
            if (comparator.compare(list.get(mid), list.get(right)) > 1) {
                return mid;
            } else if (comparator.compare(list.get(left), list.get(right)) > 1) {
                return right;
            } else {
                return left;
            }
        } else {
            if (comparator.compare(list.get(left), list.get(right)) > 1) {
                return left;
            } else if (comparator.compare(list.get(mid), list.get(right)) > 1) {
                return right;
            } else {
                return mid;
            }
        }
    }

    private static <T> int partition(final List<T> list, final int left, final int right, final int pivotIndex, final Comparator<T> comparator) {
        final T pivotValue = list.get(pivotIndex);

        // move pivot to the end
        final T rightValue = list.get(right);
        list.set(right, pivotValue);
        list.set(pivotIndex, rightValue);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (comparator.compare(list.get(i), pivotValue) > 0) {
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

    private static <T> List<T> copySorted(final Iterable<T> unsortedList, final Comparator<T> comparator) {
        return Ordering.from(comparator).reverse().sortedCopy(unsortedList);
    }

    private static <T extends Comparable<T>> Comparator<T> comparator() {
        return new Comparator<T>() {
            @Override
            public final int compare(final T o1, final T o2) {
                return o1.compareTo(o2);
            }
        };
    }
}

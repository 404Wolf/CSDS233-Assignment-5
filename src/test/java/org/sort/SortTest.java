package org.sort;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

class SortTest {
    public <T extends Comparable<T>> void testSort(Consumer<List<T>> sorter) {
        SortTestUtils.sortIntegers(sorter);
        SortTestUtils.sortStrings(sorter);
    }

    @Test
    void mergeSort() {
        testSort((arr) -> MergeSort.sort(arr));
        SortTestUtils.testVaryingSizes(MergeSort::sort);
    }

    @Test
    void quickSort() {
        testSort((arr) -> QuickSort.sort(arr));
        SortTestUtils.testVaryingSizes(QuickSort::sort);
    }
}
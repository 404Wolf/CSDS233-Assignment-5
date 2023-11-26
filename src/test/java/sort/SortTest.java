package sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

class SortTest {
    public <T extends Comparable<T>> void testSort(Consumer<ArrayList<T>> sort) {
        SortTestUtils.sortIntegers(sort);
        SortTestUtils.sortStrings(sort);
    }


    @Test
    void mergeSort() {
        testSort((arr) -> MergeSort.sort(arr));
    }

    @Test
    void quickSort() { testSort((arr) -> QuickSort.sort(arr)); }
}
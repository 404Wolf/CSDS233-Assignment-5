package sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortIntegers() {
        SortTestUtils.sortIntegers((arr) -> MergeSort.sort(arr));
    }

    @Test
    void sortStrings() {
        SortTestUtils.sortStrings((arr) -> MergeSort.sort(arr));
    }
}
package sort;

import java.util.List;

public interface Sorter {
    static <T extends Comparable<T>> void sort(List<T> arr) {}
}

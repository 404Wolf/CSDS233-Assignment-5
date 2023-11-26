package org.sort;

import java.util.*;

public class QuickSort {
    public static final Random random = new Random();

    public static <T extends Comparable<T>> void sort(List<T> arr) {
        if (arr.isEmpty())
            return;
        quicksort(
                arr,
                randomInt(0, arr.size() - 1),
                0,
                arr.size() - 1
        );
    }

    private static <T extends Comparable<T>> void quicksort(
            List<T> arr,
            int pivot,
            int left,
            int right
    ) {
        assert pivot >= left && pivot <= right;
        if (right - left >= 1) {
            int splitAt = partition(arr, arr.get(pivot), left, right);
            assert splitAt >= left && splitAt <= right;
            quicksort(arr, randomInt(left, splitAt), left, splitAt);
            if (splitAt + 1 <= right)
                quicksort(arr, randomInt(splitAt + 1, right), splitAt + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(
            List<T> arr,
            T pivot,
            int left,
            int right
    ) {
        int leftPointer = left;
        int rightPointer = right;

        while (true) {
            while (arr.get(leftPointer).compareTo(pivot) < 0)
                leftPointer++;

            while (arr.get(rightPointer).compareTo(pivot) > 0)
                rightPointer--;

            if (rightPointer <= leftPointer)
                return rightPointer;
            else {
                swap(arr, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
    }

    private static <T> void swap(List<T> arr, int index1, int index2) {
        T tmp = arr.get(index2);
        arr.set(index2, arr.get(index1));
        arr.set(index1, tmp);
    }

    private static int randomInt(int min, int max) {
        if (max == min)
            return max;
        assert max > min;
        return random.nextInt(max - min + 1) + min;
    }

    private static int randomInt(int max) {
        return randomInt(0, max);
    }
}
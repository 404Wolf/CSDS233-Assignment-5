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
        if (right > left) {
            int splitAt = partition(arr, pivot, left, right);
            quicksort(arr, (left + splitAt) / 2, left, splitAt);
            quicksort(arr, (right + splitAt) / 2, splitAt + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(
            List<T> arr,
            int pivot,
            int left,
            int right
    ) {
        int leftPointer = left;
        int rightPointer = right;

        while (true) {
            while (arr.get(leftPointer).compareTo(arr.get(pivot)) < 0)
                leftPointer++;

            while (arr.get(rightPointer).compareTo(arr.get(pivot)) > 0)
                rightPointer--;

            if (leftPointer < rightPointer) {
                swap(arr, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            } else
                return rightPointer;
        }
    }

    private static <T> void swap(List<T> arr, int index1, int index2) {
        T tmp = arr.get(index2);
        arr.set(index2, arr.get(index1));
        arr.set(index1, tmp);
    }

    private static int randomInt(int min, int max) {
        return min + random.nextInt(max + 1);
    }

    private static int randomInt(int max) {
        return randomInt(0, max);
    }
}
package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public static <T extends Comparable<T>> void sort (List<T> arr) {
        int initialPivot = chooseInitialPivot(arr);
        quicksort(arr, arr.get(initialPivot), 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void quicksort(
            List<T> arr,
            T pivot,
            int left,
            int right
    ) {
        if (right > left) {
            int splitAt = partition(arr, pivot, left, right);
            quicksort(arr, arr.get(splitAt / 2), left, splitAt);
            quicksort(arr, arr.get((right - splitAt) / 2), splitAt + 1, right);
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
            while (arr.get(leftPointer).compareTo(pivot) <= 0)
                leftPointer++;

            while (arr.get(rightPointer).compareTo(pivot) >= 0)
                rightPointer--;

            if (leftPointer > rightPointer)
                return rightPointer;

            swap(arr, leftPointer, rightPointer);
        }
    }

    private static <T> void swap(List<T> arr, int index1, int index2) {
        T tmp = arr.get(index1);
        arr.set(index2, arr.get(index1));
        arr.set(index1, tmp);
    }

    private static <T extends Comparable<T>> int chooseInitialPivot(List<T> arr) {
        Random random = new Random();

        // If there are less than five elements return a random index to be the start pivot
        if (arr.size() <= 5)
            return random.nextInt(arr.size() + 1);

        // If there are more than five elements return the median of three random indexes to be the initial pivot
        List<Integer> samples = new ArrayList<>(3);
        for (int i = 0; i < 3; i++)
            samples.add(random.nextInt(arr.size() - 1));
        MergeSort.sort(samples);
        return samples.get(1);
    }
}

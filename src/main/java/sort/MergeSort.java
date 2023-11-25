package sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort <T extends Comparable<T>> implements Sorter {
    public static <T extends Comparable<T>> void sort (List<T> arr) {
        mergeSort(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(List<T> arr, int left, int right) {
        if (left < right) {
            int firstHalfEnd = left + ((right - left) / 2);

            mergeSort(arr, left, firstHalfEnd);
            mergeSort(arr, firstHalfEnd + 1, right);

            merge(arr, left, firstHalfEnd, firstHalfEnd + 1, right);
        }
    }

    private static <T extends Comparable<T>> void merge(
            List<T> arr,
            int leftStart,
            int leftEnd,
            int rightStart,
            int rightEnd
    ) {
        List<T> auxArr = new ArrayList<>(rightEnd - leftStart + 1);
        int leftPointer = leftStart;
        int rightPointer = rightStart;

        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {
            // If the left array's i-th element is less place it in the auxiliary array
            if (arr.get(leftPointer).compareTo(arr.get(rightPointer)) < 0) {
                auxArr.add(arr.get(leftPointer));
                leftPointer++;
            }
            // If the right array's j-th element is less place it ini the auxiliary array
            else if (arr.get(leftPointer).compareTo(arr.get(rightPointer)) > 0) {
                auxArr.add(arr.get(rightPointer));
                rightPointer++;
            }
        }

        // Copy remaining elements of first array
        for (int i = leftPointer; i <= leftEnd; i++)
            auxArr.add(arr.get(i));

        // Copy remaining elements of second array
        for (int j = rightPointer; j <= rightEnd; j++)
            auxArr.add(arr.get(j));

        // Copy over the result to the main array
        for (int i = leftStart; i <= rightEnd; i++)
            arr.set(i, auxArr.get(i - leftStart));
    }
}

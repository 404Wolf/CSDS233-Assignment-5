package sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SortTestUtils {
    public static <T extends Comparable<T>> void sortIntegers(Consumer<ArrayList<T>> sorter) {
        // Build a non-sorted array
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(22);
        ints.add(3);
        ints.add(44);
        ints.add(5);
        ints.add(66);

        // Assert that the array is indeed not sorted
        assertFalse(ints.get(1) < ints.get(2));
        assertEquals(5, ints.get(4));
        assertEquals(66, ints.get(5));

        // Sort the array and assert it is sorted
        System.out.println(ints);
        sorter.accept((ArrayList<T>) ints);
        System.out.println(ints);
        assertEquals(1, ints.get(0));
        assertEquals(3, ints.get(1));
        assertEquals(5, ints.get(2));
        assertEquals(22, ints.get(3));
        assertEquals(44, ints.get(4));
        assertEquals(66, ints.get(5));
    }

    public static <T extends Comparable<T>> void sortStrings(Consumer<ArrayList<T>> sorter) {
        // Build a non-sorted array of strings
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("orange");
        words.add("banana");
        words.add("grape");
        words.add("kiwi");
        words.add("pear");
        words.add("peach");
        words.add("pineapple");
        words.add("plum");

        // Assert that the array is indeed not sorted
        assertFalse(words.get(1).compareTo(words.get(2)) < 0);
        assertEquals("kiwi", words.get(4));
        assertEquals("pear", words.get(5));

        // Sort the array and assert it is sorted
        System.out.println(words);
        sorter.accept((ArrayList<T>) words);
        System.out.println(words);
        assertEquals("apple", words.get(0));
        assertEquals("banana", words.get(1));
        assertEquals("grape", words.get(2));
        assertEquals("kiwi", words.get(3));
        assertEquals("orange", words.get(4));
        assertEquals("peach", words.get(5));
        assertEquals("pear", words.get(6));
        assertEquals("pineapple", words.get(7));
        assertEquals("plum", words.get(8));
    }
}

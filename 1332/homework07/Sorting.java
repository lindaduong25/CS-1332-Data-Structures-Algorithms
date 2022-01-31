import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Linda Duong
 * @version 1.0
 * @userid lduong8
 * @GTID 903568287
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and/or the comparator cannot be null.");
        }
        for (int i = 1; i <= arr.length - 1; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j], arr[j - 1]) < 0) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and/or the comparator cannot be null.");
        }
        boolean swapsMade = true;
        int lastSwappedIndex = 0;
        int startInd = 0;
        int endInd = arr.length - 1;
        T temp;
        while (swapsMade) {
            swapsMade = false;
            for (int i = startInd; i < endInd; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapsMade = true;
                    lastSwappedIndex = i;
                }
            }
            endInd = lastSwappedIndex;
            if (swapsMade) {
                swapsMade = false;
                for (int i = endInd; i > startInd; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                        temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;
                        swapsMade = true;
                        lastSwappedIndex = i;
                    }
                }
            }
            startInd = lastSwappedIndex;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and/or the comparator cannot be null.");
        }
        // part 1
        if (arr.length == 1) {
            return;
        }
        int length = arr.length;
        int midIndex = length / 2;
        T[] left = (T[]) new Object[midIndex];
        T[] right = (T[]) new Object[length - midIndex];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[i + left.length];
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        // part 2
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        // part 3
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array cannot be null.");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }
        // finding k (digits) in longest number
        int max = arr[0];
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == Integer.MIN_VALUE) {
                max = Integer.MAX_VALUE;
            } else {
                if (Math.abs(arr[i]) > max) {
                    max = Math.abs(arr[i]);
                }
            }
        }
        while (max >= 10) {
            k++;
            max /= 10;
        }
        int divisor = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length; j++) {
                int digit = (arr[j] / divisor) % 10;
                buckets[digit + 9].add(arr[j]);
            }
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.remove();
                }
            }
            divisor *= 10;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("The array, the comparator, and/or the random object cannot be null.");
        }
        qsH(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Recursive Helper for quickSort method above
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @param start      the index that is the start of arr
     * @param end        the index that is the end of arr
     */
    private static <T> void qsH(T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        // part 1
        if (end - start < 1) {
            return;
        }
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        T pivotVal = arr[pivotIdx];
        arr[pivotIdx] = arr[start];
        arr[start] = pivotVal;
        T temp;
        // part 2 - partitioning
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        // part 3
        temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;
        qsH(arr, comparator, rand, start, j - 1);
        qsH(arr, comparator, rand, j + 1, end);
    }
}

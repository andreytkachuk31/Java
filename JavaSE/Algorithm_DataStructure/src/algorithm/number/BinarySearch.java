package algorithm.number;

/**
 * Binary search
 *
 * @author: andrey.tkachuk
 * @since: 26.07.17
 */
public class BinarySearch {

    /**
     * Iterative method of binary search
     *
     * @param array array
     * @param value element
     * @return finding index
     */
    public static int binarySearch(int[] array, int value) {
        int low = 0;
        int hi = array.length - 1;

        while (low <= hi) {
            int mid = (low + hi) / 2; // OR -> low + (hi - low) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Recursion method of binary search
     *
     * @param array array
     * @param value element
     * @return finding index
     */
    public static int binarySearchRecursion(int[] array, int value) {
        return binarySearchRecursion(array, value, 0, array.length - 1);
    }

    private static int binarySearchRecursion(int[] array, int value, int low, int hi) {
        if (low <= hi) {
            int mid = (low + hi) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                return binarySearchRecursion(array, value, mid + 1, hi);
            } else {
                return binarySearchRecursion(array, value, low, mid - 1);
            }
        }
        return -1;
    }
}
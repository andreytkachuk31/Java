package algorithm.number;

/**
 * Rotate array to the left by k steps
 * Example - {1,2,3,4,5}, k = 2 -> 3,4,5,1,2
 *
 * @author: andrey.tkachuk31
 * @since: 20.01.22
 */
public class RotateArray {

    /**
     * Reversal algorithm
     * Time complexity - O(N), memory complexity - O(1)
     *
     * @param array input array
     * @param k     steps
     */
    public static void rotateArray(int[] array, int k) {
        reverse(array, 0, k);
        reverse(array, k, array.length);
        reverse(array, 0, array.length);
    }

    /**
     * Rotate one by one. Not optimized algorithm
     * Time complexity - O(N*N), memory complexity - O(1)
     *
     * @param array input array
     * @param k     steps
     */
    public static void rotateArray1(int[] array, int k) {
        for (int i = 0; i < k; i++) {
            int currentIndex = 0;
            for (int j = 1; j < array.length; j++) {
                swap(array, currentIndex++, j);
            }
        }
    }

    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end - 1);
            start++;
            end--;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

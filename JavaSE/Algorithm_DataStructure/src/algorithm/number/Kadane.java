package algorithm.number;

/**
 * Kadane's algorithm is an algorithm in which we search for a maximum sum contiguous sub array within a numeric array
 * A[] = {1, 2, 3, -2, 1, 10}
 * 15 is maximum sum (whole array)
 * A[] = {1, 2, 3, -8, 1, 10}
 * 11 is maximum sum (1, 10)
 * A[] = {-4, -3, -2, -1}
 * -1 is maximum sum (-1)
 *
 * @author andrey.tkachuk
 * @since 25.10.21
 */
public class Kadane {

    /**
     * Kadane’s algorithm is to look for all positive contiguous segments of the array
     * And keep track of maximum sum contiguous segment among all positive segments
     *
     * @param array array
     * @return max sum of sub array
     */
    public static int kadane(int[] array) {
        int currentSum = 0;
        int maxSum = array[0];
        for (int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * Another approach of Kadane’s algorithm
     *
     * @param array array
     * @return max sum of sub array
     */
/*    public static int kadane(int[] array) {
        int currentSum = array[0];
        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(array[i], currentSum + array[i]);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }*/
}

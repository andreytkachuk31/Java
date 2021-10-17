package algorithm.number;

import java.util.Arrays;

/**
 * Equilibrium point is an index of array that the sum of elements at lower indexes is equal to the sum of elements at higher indexes
 * A[] = {-7, 1, 5, 2, -4, 3, 0}
 * 3 is an equilibrium index, because: A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 *
 * @author andrey.tkachuk
 * @since 17.10.21
 */
public class EquilibriumPoint {

    /**
     * My solution to find equilibrium point
     *
     * @param array array
     * @return equilibrium point
     */
    public static int equilibriumPoint(int[] array) {
        int totalSum = Arrays.stream(array).sum();
        int leftSum = array[0];

        for (int i = 1; i < array.length - 1; i++) {
            long rightSum = totalSum - leftSum - array[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += array[i];
        }

        return -1;
    }

    /**
     * Solution is taken from internet to find equilibrium point
     * There is broken testcase if leftSum == sum for first element
     *
     * @param array array
     * @return equilibrium point
     */
/*    public static int equilibriumPoint1(int[] array) {
        int sum = Arrays.stream(array).sum();
        int leftSum = 0;

        for (int i = 0; i < array.length; ++i) {
            sum -= array[i]; // sum is now right sum for index i

            if (leftSum == sum)
                return i;

            leftSum += array[i];
        }

        return -1;
    }*/
}

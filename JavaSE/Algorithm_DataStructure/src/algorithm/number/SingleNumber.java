package algorithm.number;

import java.util.Arrays;

/**
 * Find the element that appears once
 *
 * @author: andrey.tkachuk31
 * @since: 31.12.21
 */
public class SingleNumber {

    /**
     * Find the element that appears once
     * Example: {2, 2, 1} -> 1
     * Time complexity - O(N), memory complexity - O(1)
     *
     * @param nums input array
     * @return single number
     */
    public int getSingle(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    /**
     * Find the element that appears once
     * Example: {2, 2, 1} -> 1
     * Time complexity - O(N* log(N)), memory complexity - O(1)
     *
     * @param nums input array
     * @return single number
     */
    public int getSingle1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}

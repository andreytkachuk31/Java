package sort;

import sort.utils.SortUtils;
import java.util.Random;

/**
 * BogoSort
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class BogoSort {

    public static void sort(Comparable[] data) {
        while (!inOrder(data)) {
            shuffle(data);
        }
    }

    private static boolean inOrder(Comparable[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (SortUtils.less(data[i + 1], data[i])) {
                return false;
            }
        }
        return true;
    }

    public static void shuffle(Comparable[] data) {
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            int swapPosition = random.nextInt(i + 1);
            SortUtils.swap(data, i, swapPosition);
        }
    }
}

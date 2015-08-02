package sort;

import sort.utils.SortUtils;

/**
 * Глупая сортировка
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class StupidSort {

    public static void sort(Comparable[] data) {
        int i = 0;
        while (i < data.length - 1) {
            if (SortUtils.less(data[i + 1], data[i])) {
                SortUtils.swap(data, i, i + 1);
                i = 0;
            } else {
                i++;
            }
        }
    }
}

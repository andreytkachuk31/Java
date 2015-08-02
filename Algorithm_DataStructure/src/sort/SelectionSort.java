package sort;

import sort.utils.SortUtils;

/**
 * Сортировка выбором
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class SelectionSort {

    public static void sort(Comparable[] data) {
        for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (SortUtils.less(data[j], data[min])) {
                    min = j;
                }
            }
            if (i != min) {
                SortUtils.swap(data, i, min);
            }
        }
    }
}

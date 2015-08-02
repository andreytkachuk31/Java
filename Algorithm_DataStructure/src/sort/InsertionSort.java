package sort;

import sort.utils.SortUtils;

/**
 * Сортировка вставками
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class InsertionSort {

    public static void sort(Comparable[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0 && SortUtils.less(data[j], data[j - 1]); j--) {
                SortUtils.swap(data, j, j - 1);
            }
        }
    }

    /**
     * Мой вариант сортирвки который предполагает сначала поиск места а потом вставка
     */
    public static void sort1(Comparable[] data) {
        int j;
        for (int i = 1; i < data.length; i++) {
            Comparable curr = data[i];
            for (j = i - 1; j >= 0 && SortUtils.less(curr, data[j]); j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = curr;
        }
    }
}

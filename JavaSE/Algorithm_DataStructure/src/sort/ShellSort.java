package sort;

import sort.utils.SortUtils;

/**
 *
 * Сортировка Шелла
 *
 * @author: andrey.tkachuk31
 * @since : 21.05.13
 */
public class ShellSort {

    public static void sort(Comparable[] data) {
        int h = 1;
        while (h < data.length / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < data.length; i++) {
                for (int j = i; j >= h && SortUtils.less(data[j], data[j - h]); j -= h) {
                    SortUtils.swap(data, j, j - h);
                }
            }
            h /= 3;
        }
    }
}

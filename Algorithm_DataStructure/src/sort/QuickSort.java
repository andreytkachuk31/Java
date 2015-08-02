package sort;

import sort.utils.SortUtils;

/**
 * Быстрая сортировка
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class QuickSort {

    public static void sort(Comparable[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(Comparable[] data, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(data, lo, hi);
        sort(data, lo, mid);
        sort(data, mid + 1, hi);
    }

    private static int partition(Comparable[] data, int lo, int hi) {
        Comparable centralElement = data[lo];
        int i = lo + 1;
        int j = hi;
        while (i <= j) {
            while (SortUtils.less(data[i], centralElement)) i++;
            while (SortUtils.less(centralElement, data[j])) j--;
            if (i <= j) {
                SortUtils.swap(data, i, j);
                i++;
                j--;
            }
        }
        SortUtils.swap(data, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Comparable[] data = new Integer[]{7, 1, 5, 10, 4, 8};
        sort(data);
        SortUtils.show(data);
    }
}

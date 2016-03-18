package sort;

import sort.utils.SortUtils;

/**
 * Пирамидальная сортировка
 *
 * @author: Андрей
 * @since: 14.12.13
 */
public class HeapSort {

    public static void sort(Comparable[] data) {
        int size = data.length;
        /*Создание пирамиды (последние n/2 елементов уже являються пирамидой, так как это листья дерева)*/
        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(data, i, size);
        }
        /*Сортировка*/
        while (size > 0) {
            SortUtils.swap(data, 0, --size);
            sink(data, 0, size);
        }
    }

    /**
     * Низходящее восстановление пирамидальности
     */
    private static void sink(Comparable[] data, int k, int size) {
        while ((2 * k + 1) < size) {
            /*Нахождение максимального потомка*/
            int j = 2 * k + 1;
            if (j < size - 1 && SortUtils.less(data[j], data[j + 1])) j++;
            /*Обмен*/
            if (!SortUtils.less(data[k], data[j])) break;
            SortUtils.swap(data, k, j);
            k = j;
        }
    }
}

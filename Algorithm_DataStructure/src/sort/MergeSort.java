package sort;

import sort.utils.SortUtils;

/**
 * Сортировка слиянием
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class MergeSort {

    /**
     * Слияние двух упорядоченных массивов в один (с использованием вспомагательного массива)
     * Сложность O(n+m), где n и m - соответственно размеры заданных массивов.
     */
    private static int[] merge(int a[], int b[]) {
        int[] result = new int[a.length + b.length];
        /*Заведем два индекса, указывающих на первый необработанный элемент первого и второго массивов*/
        int i = 0;
        int j = 0;
        int index = 0; // Заведем индекс массива-результата, который указывает позицию, которая будет заполнена на текущем шаге
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[index] = a[i++];
            } else {
                result[index] = b[j++];
            }
            index++;
        }
        /*После выполнения предыдущего цикла все элементы одного из массивов будут обработаны,
          тогда оставшиеся элементы другого массива нужно просто дописать
         в массив-результа*/
        while (i < a.length) {
            result[index++] = a[i++];
        }
        while (j < b.length) {
            result[index++] = b[j++];
        }
        return result;
    }

    /**
     *  Слияние двух упорядоченных массивов в один (на месте)
     */

    private static void merge(Comparable data[], int lo, int mid, int hi) {
        Comparable result[] = new Comparable[data.length];
        int i = lo;
        int j = mid + 1;
        int index = lo;
        for (int k = lo; k <= hi; k++) {
            result[k] = data[k];
        }

        /*Тоже самое что в предыдущем методе merge, но только дозаполенение массива происходит в одном цикле с
        * помощью if-ов*/
        for (int k = lo; k <= hi; k++){
            if ( i > mid ) data[k] = result[j++];
            else if ( j > hi ) data[k] = result[i++];
            else if (SortUtils.less(result[i], result[j])) data[k] = result[i++];
            else data[k] = result[j++];
        }
    }

    public static void sort(Comparable[] data){
       sort(data, 0, data.length-1);
    }

    private static void sort(Comparable[] data, int lo, int hi){
       if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(data, lo, mid);
        sort(data, mid + 1, hi);
        merge(data, lo, mid, hi);
    }
}

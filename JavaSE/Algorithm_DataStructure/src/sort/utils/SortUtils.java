package sort.utils;

/**
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class SortUtils {

    private static final Integer DEFAULT_SIZE = 20;
    private static final String SEPARATOR_SPACE = " ";

    public static Comparable[] input() {
        Comparable[] data = new Integer[DEFAULT_SIZE];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 20);
        }
        return data;
    }

    public static void swap(Comparable[] data, int i, int j) {
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static Comparable max(Comparable v, Comparable w) {
       return less(v, w) ? w : v;
    }

    public static void show(Comparable[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}

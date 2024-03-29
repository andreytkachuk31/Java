package algorithm.number;

/**
 * Найти пропущенные числа в ряде 1...N
 *
 * @author: andrey.tkachuk31
 * @since: 29.10.13
 */
public class NSumma {

    /**
     * Найти пропущенное число в ряде 1...N
     */
    public static int oneMissingNumber(int[] array, int N) {
        int summa = (N * (N + 1)) / 2;
        int summaRes = 0;
        for (int i = 0; i < array.length; i++) {
            summaRes += array[i];
        }
        return summa - summaRes;
    }
}

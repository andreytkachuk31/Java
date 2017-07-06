package algorithm.number;

/**
 * Нахождение частоты встречания числа в массиве
 *
 * @author: Андрей
 * @since: 19.11.13
 */
public class Frequency {

    public static int[] frequency(int input[]) {
        int frequency[] = new int[max(input) + 1];
        for (int i = 0; i < input.length; i++) {
            frequency[input[i]]++;
        }
        return frequency;
    }

    private static int max(int input[]) {
        int max = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }
}

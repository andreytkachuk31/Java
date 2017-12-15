package algorithm.number;

import java.util.HashSet;
import java.util.Set;

/**
 * Определяет есть ли в последовательности пара чисел, сумма которых равна заданому числу
 *
 * @since: 12.04.14
 * @author: Андрей
 */
public class SumPair {

    /**
     * Определяет есть ли в последовательности пара чисел, сумма которых равна заданому числу
     *
     * @param array входящий массив
     * @return true, если в последовательности есть пара чисел, сумма которых равна заданому числу
     */
    public boolean hasPairWithSum(int[] array, int number) {
        Set<Integer> differences = new HashSet<>();
        for (int element : array) {
            if (differences.contains(element)) {
                return true;
            }
            differences.add(number - element);
        }
        return false;
    }
}

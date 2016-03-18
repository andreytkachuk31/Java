package algorithm;

/**
 * Определяет максимальное кол-во подряд идущих элементов
 *
 * @since: 12.04.14
 * @author: Андрей
 */
public class MaxRepeat {

    /**
     * Определяет максимальное кол-во подряд идущих элементов
     *
     * @param array входящий массив
     * @return массив в котором первым елементов идет индекс посл-ти, а вторым - количество элементов в посл-ти
     */
    public static int[] defineMaxRepeatNumbers(int array[]) {
        int maxCount = 0;
        int startIndex = 0;
        int count = 0;
        int i = 0;
        do {
            if (i < array.length - 1) {
                if (array[i] == array[i + 1]) {
                    count++;
                } else {
                    if (count > maxCount) {
                        maxCount = count; // количество эл-тов в последовательности
                        startIndex = i - maxCount; // начало последовательности
                    }
                    count = 0;
                }
            }
        } while (i < array.length - 1);
        return new int[]{startIndex, startIndex + maxCount};
    }

}

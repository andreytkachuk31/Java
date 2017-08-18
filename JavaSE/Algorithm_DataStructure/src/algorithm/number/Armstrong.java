package algorithm.number;

/**
 * Определяет являеться ли число - числом Армстронга
 */
public class Armstrong {

    /**
     * Method check whether number is armstrong number <br/>
     * Example1: 153 -> pow (1, 3) + pow(5, 3) + pow (3, 3) - armstrong number<br/>
     * Example2: 17  -> pow (1, 2) + pow(7, 2) - not armstrong number
     *
     * @param number number
     * @return true, if number is armstrong number
     */
    public boolean isArmstrongNumber(int number) {
        int result = 0;
        int storeNumber = number;
        int count = getCount(number);
        while (number != 0) {
            int digit = number % 10;
            result += pow(digit, count);
            number /= 10;
        }
        return storeNumber == result;
    }

    private int getCount(int number) {
        int count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    private int pow(int digit, int exponent) {
        int res = 1;
        for (int i = 0; i < exponent; i++) {
            res *= digit;
        }
        return res;
    }
}

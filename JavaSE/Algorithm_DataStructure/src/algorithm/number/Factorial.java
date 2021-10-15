package algorithm.number;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Computes factorial of number
 * Formula - n! = n * (n - 1)!
 * Example - 5! = 5 * 4 * 3 * 2 * 1 = 120
 *
 * @author andrey.tkachuk
 * @since 11.05.13
 */
public class Factorial {

    private static Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>();

    /* int => 14; long => 18 приблизительно */
    public static BigInteger factorial(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        BigInteger res = BigInteger.ONE;
        for (int i = n; i > 0; i--) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        cache.put(n, res);
        return res;
    }

    public static int factorialRecursion(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursion(n - 1);
    }
}

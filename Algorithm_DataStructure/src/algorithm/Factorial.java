package algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 11.05.13
 *
 * @author andrey.tkachuk
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

}

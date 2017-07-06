package algorithm.number;

/**
 * НОД (наибольший общий делитель)
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class GCD {

    /*Алгоритм Евклида (рекурсия)*/
    public static int gcd(int a, int b) {
        if ( b == 0 ) return a;
        return gcd(b, a % b);
    }

    /*Алгоритм Евклида*/
/*
    public static int gcd(int a, int b) {
        int temp;
        while (b != 0){
            temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
*/

    /*Алгоритм основан на простом переборе*/
/*    public static int gcdSimple(int a, int b) {
        for (int i = Math.min(a, b); i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }
*/
}

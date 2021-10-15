package algorithm.number;

/**
 * Computes and prints the first n Fibonacci numbers
 * Formula - f(n) = f(n - 1) + f(n - 2)
 * Example of first 10 fibonacci numbers - 0 1 1 2 3 5 8 13 21 34
 *
 * @author: andrey.tkachuk
 * @since: 14.10.21
 */
public class Fibonacci {

    public static void fibonacci(int n) {
        int f1 = 0;
        int f2 = 1;

        System.out.print(f1 + " ");
        for (int i = 1; i < n; i++) {
            System.out.print(f2 + " ");
            int next = f1 + f2;
            f1 = f2;
            f2 = next;
        }
    }

    /**
     * Method gets the n-th Fibonacci number
     */
    public static int fibonacciRecursion(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }
}

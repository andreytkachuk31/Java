package algorithm.number;

/**
 * Check if number is palindrome
 * Example - 151 is palindrome, 152 - not
 *
 * @author: andrey.tkachuk31
 * @since: 15.10.21
 */
public class Palindrome {

    public static boolean isPalindrome(int number) {
        int givenNumber = number;
        int reverseNumber = 0;
        while (number > 0) {
            int digit = number % 10;
            reverseNumber = (reverseNumber * 10) + digit;
            number /= 10;
        }
        return givenNumber == reverseNumber;
    }
}

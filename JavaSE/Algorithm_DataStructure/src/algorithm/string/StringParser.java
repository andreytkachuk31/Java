package algorithm.string;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class StringParser {

    private static final int ZERO_CODE_POINT = (int) '0';

    private static final Map<Character, Integer> TABLE_DIGITS = new HashMap<>();

    static {
        TABLE_DIGITS.put('0', 0);
        TABLE_DIGITS.put('1', 1);
        TABLE_DIGITS.put('2', 2);
        TABLE_DIGITS.put('3', 3);
        TABLE_DIGITS.put('4', 4);
        TABLE_DIGITS.put('5', 5);
        TABLE_DIGITS.put('6', 6);
        TABLE_DIGITS.put('7', 7);
        TABLE_DIGITS.put('8', 8);
        TABLE_DIGITS.put('9', 9);
    }

    /**
     * Method that parse input string to integer
     *
     * @param input, input string
     * @return parsed integer
     * @throws ParseException if string has non-digit symbols
     */
    public int parseString(String input) throws ParseException {
        //int result = parseStringUsingTable(input);
        int result = parseStringUsingCodePoint(input);

        return result;
    }

    private int parseStringUsingTable(String input) throws ParseException {
        int result = 0;

        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char symbol = charArray[i];
            Integer digit = TABLE_DIGITS.get(symbol);
            if (digit == null) {
                throw new ParseException(String.format("We can't parse string - %s", input), 0);
            }
            result += (int) (digit * Math.pow(10, input.length() - i - 1));
        }

        return result;
    }

    private int parseStringUsingCodePoint(String input) throws ParseException {
        int result = 0;

        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char symbol = charArray[i];
            int codePoint = (int) symbol;
            int digit = codePoint - ZERO_CODE_POINT;
            if (digit < 0 || digit > 9) {
                throw new ParseException(String.format("We can't parse string - %s", input), 0);
            }
            result += (int) (digit * Math.pow(10, input.length() - i - 1));
        }

        return result;
    }
}

package algorithm.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Проверяет есть ли в строке дубликаты
 *
 * @author : andrey.tkachuk31
 * @since : 14.07.17
 */
public class StringDuplicater {

    /**
     * Method that check duplicate characters from String <br/>
     * Example: "hello" -> true, "helo" -> false
     *
     * @param input, input string
     * @return true, if input string contains duplicate characters
     */
    public boolean containsDuplicateCharacters(String input) {
        boolean result = containsByCharacters(input);
        //boolean result = containsUsingCollection(input);

        return result;
    }

    /**
     * Algorithm has O(n*n) complexity
     */
    private boolean containsByCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Algorithm has O(n) complexity
     */
    private boolean containsUsingCollection(String input) {
        Set<Character> result = new HashSet<>();
        for (char symbol : input.toCharArray()) {
            if (result.contains(symbol)) {
                return true;
            }
            result.add(symbol);
        }
        return false;
    }
}

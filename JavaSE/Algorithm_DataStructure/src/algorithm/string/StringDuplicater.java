package algorithm.string;

import java.util.HashSet;
import java.util.Set;

public class StringDuplicater {

    /**
     * Method that check input string on duplicate characters
     *
     * @param input, input string
     * @return true, if input string contains duplicate characters
     */
    public boolean containsDuplicateCharacters(String input) {
        boolean result = searchByCharacters(input);
        //boolean result = searchUsingCollection(input);

        return result;
    }

    /**
     * Algorithm has O(n*n) complexity
     */
    private boolean searchByCharacters(String input) {
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
    private boolean searchUsingCollection(String input) {
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

package algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Find one extra character in a string
 *
 * @author : andrey.tkachuk31
 * @since : 19.10.21
 */
public class StringExtraCharacter {

    /**
     * Find one extra character in a string
     * Example: ("abcd", "abcde") -> 'e', ("caed", "caaed") -> 'a'
     *
     * @param s input string
     * @param t string with  extra character
     * @return extra character
     */
    public static char findOneExtraCharacter(String s, String t) {
        Map<Character, Integer> frequencyS = frequency(s);
        Map<Character, Integer> frequencyT = frequency(t);

        for (Map.Entry<Character, Integer> entry : frequencyT.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            if (frequencyS.containsKey(key)) {
                if (value != frequencyS.get(key)) {
                    return key;
                }
            } else {
                return key;
            }
        }

        return Character.MIN_VALUE;
    }

    private static Map<Character, Integer> frequency(String s) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (char letter : s.toCharArray()) {
            if (frequency.containsKey(letter)) {
                frequency.put(letter, frequency.get(letter) + 1);
            } else {
                frequency.put(letter, 1);
            }
        }

        return frequency;
    }
}

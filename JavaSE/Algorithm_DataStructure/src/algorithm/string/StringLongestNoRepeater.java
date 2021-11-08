package algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Find longest substring without repeating characters
 */
public class StringLongestNoRepeater {

    /**
     * Find longest substring without repeating characters
     * Example - {"abcabcbb" -> "abc"}
     *
     * @param str input string
     * @return longest substring
     */
    public String findLongestNoRepeating(String str) {
        Map<Character, Integer> result = new HashMap<>();
        int maxLen = 0;
        int startFinal = 0;
        int start = 0;
        int end = 0;
        for (end = 0; end < str.length(); end++) {
            char symbol = str.charAt(end);
            if (result.containsKey(symbol)) {
                Integer newStart = result.get(symbol);
                if (newStart >= start) {
                    int currLen = end - start;
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        startFinal = start;
                    }
                    start = newStart + 1;
                }
            }
            result.put(symbol, end);
        }
        if (maxLen < end - start) {
            maxLen = end - start;
            startFinal = start;
        }
        return str.substring(startFinal, startFinal + maxLen);
    }
}

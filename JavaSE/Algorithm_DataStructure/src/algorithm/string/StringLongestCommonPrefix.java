package algorithm.string;

/**
 * Find the longest common prefix
 *
 * @author : andrey.tkachuk31
 * @since : 14.12.21
 */
public class StringLongestCommonPrefix {

    /**
     * Find the longest common prefix
     * Example - {"flower","flow","flight" -> "fl"}
     *
     * @param strs array of strings
     * @return longest common prefix
     */
    public String findLongestCommonPrefix(String[] strs) {
        String prefix = "";
        int minLength = findMinLength(strs);
        for (int i = 0; i < minLength; i++) {
            char symbol = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (symbol != strs[j].charAt(i)) {
                    return prefix;
                }
            }
            prefix += symbol;
        }
        return prefix.equals("") ? "-1" : prefix;
    }

    private int findMinLength(String[] strs) {
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            int length = strs[i].length();
            if (length < minLength) {
                minLength = length;
            }
        }
        return minLength;
    }
}

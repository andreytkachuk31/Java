package algorithm.string;

/**
 * Find the longest substring which is palindrome.
 *
 * @author : andrey.tkachuk31
 * @since : 08.12.21
 */
public class StringLongestPalindrome {

    /**
     * Find the longest substring which is palindrome
     * Example - {"aaaabbaa" -> "aabbaa"}
     *
     * @param s input string
     * @return longest palindrome substring
     */
    public String findLongestPalindrome(String s) {
        int start = 0;
        int end = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                start = i - (len - 1) / 2;
                end = i + (len / 2);
                maxLen = len;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

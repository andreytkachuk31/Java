package algorithm.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringLongestPalindromeTest {

    private StringLongestPalindrome subject = new StringLongestPalindrome();

    @Test
    public void longestPalindromeInStart() {
        String result = subject.findLongestPalindrome("babad");

        assertEquals("bab", result);
    }

    @Test
    public void longestPalindromeWithOneCharacter() {
        String result = subject.findLongestPalindrome("abc");

        assertEquals("a", result);
    }

    @Test
    public void longestPalindromeInMiddle() {
        String result = subject.findLongestPalindrome("aaaabbaa");

        assertEquals("aabbaa", result);
    }
}
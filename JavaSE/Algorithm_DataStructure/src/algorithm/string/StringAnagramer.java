package algorithm.string;

import java.util.Arrays;

/**
 * Проверяет являються ли два числа анаграммами
 *
 * @author : andrey.tkachuk31
 * @since : 18.07.17
 */
public class StringAnagramer {

    /**
     * Method check if two strings are anagrams of each other <br/>
     * Example: ("cat", "act") -> true, ("dog", "door") -> false
     *
     * @param word input word
     * @param anagram anagram of word
     * @return true, if words are anagram
     */
    public boolean isAnagram(String word, String anagram) {
        boolean result = isAnagramUsingSorting(word, anagram);
        //boolean result = isAnagramUsingBuilder(word, anagram);

        return result;
    }

    private boolean isAnagramUsingSorting(String word, String anagram) {
        char[] wordChars = word.toCharArray();
        char[] anagramChars = anagram.toCharArray();

        Arrays.sort(wordChars);
        Arrays.sort(anagramChars);

        return Arrays.equals(wordChars, anagramChars);
    }

    private boolean isAnagramUsingBuilder(String word, String anagram) {
        char[] wordChars = word.toCharArray();
        StringBuilder sbAnagram = new StringBuilder(anagram);
        for (char symbol : wordChars) {
            int index = sbAnagram.indexOf(String.valueOf(symbol));
            if (index != -1) {
                sbAnagram.deleteCharAt(index);
            } else {
                return false;
            }
        }
        return sbAnagram.length() == 0;
    }
}
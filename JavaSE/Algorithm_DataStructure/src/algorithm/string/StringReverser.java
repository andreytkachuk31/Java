package algorithm.string;

/**
 * Переварачивает строку
 *
 * @author : andrey.tkachuk31
 * @since : 19.07.17
 */
public class StringReverser {

    /**
     * Method that reverse input string
     * Example: "hello" -> "olleh"
     *
     * @param input input string
     * @return reverse string
     */
    public String reverse(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            swap(chars, i, chars.length - i - 1);
        }
        return new String(chars);
    }

    /**
     * Recursive method {@code} reverse
     *
     * @param input input string
     * @return reverse string
     */
    public String reverseRecursive(String input) {
        return null;
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
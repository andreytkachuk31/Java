package algorithm.string;

/**
 * * Проверяет являються ли одна строка подстрокой другой
 *
 * @author: Андрей
 * @since: 29.07.17
 */
public class StringContains {

    /**
     * Method check one string is substring of other
     *
     * @param input input string
     * @param substring substring
     * @return true, if input string contains substring
     */
    public boolean contains(String input, String substring) {
        if (input.length() < substring.length()) {
            return false;
        }
        int startIndex = 0;
        int previousIndex = 0;
        for (int i = 0; i < substring.length(); i++) {
            char symbol = substring.charAt(i);
            int index = indexOf(input, symbol, startIndex);
            if (index == -1) {
                return false;
            } else {
                if (i > 0 && (index - previousIndex) > 1) {
                    return false;
                }
                previousIndex = index;
                startIndex = index + 1;
            }
        }
        return true;
    }

    private int indexOf(String input, char symbol, int startIndex) {
        for (int i = startIndex; i < input.length(); i++) {
            if (input.charAt(i) == symbol) {
                return i;
            }
        }
        return -1;
    }
}

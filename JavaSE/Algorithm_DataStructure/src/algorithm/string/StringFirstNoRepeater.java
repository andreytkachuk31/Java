package algorithm.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringFirstNoRepeater {

    public char findFirstNoRepeatCharacter(String input) {
        Map<Character, Integer> frequency = frequency(input);

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            char symbol = entry.getKey();
            int frequencyValue = entry.getValue();
            if (frequencyValue == 1) {
                return symbol;
            }
        }

        return Character.MIN_VALUE;
    }

    private Map<Character, Integer> frequency(String input) {
        Map<Character, Integer> frequency = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (frequency.containsKey(symbol)) {
                frequency.put(symbol, frequency.get(symbol) + 1);
            } else {
                frequency.put(symbol, 1);
            }
        }

        return frequency;
    }
}

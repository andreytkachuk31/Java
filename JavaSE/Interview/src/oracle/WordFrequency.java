package oracle;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequency {

    public List<String> findFrequencyWords(List<String> words) {
        Map<String, Long> frequencyMap = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequencyMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }
}

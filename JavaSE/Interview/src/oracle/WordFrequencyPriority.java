package oracle;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordFrequencyPriority {

    public List<String> findFrequencyWords(List<String> words, int k) {
        Map<String, Long> frequency = words
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Map.Entry<String, Long>> priorityQueue = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        priorityQueue.addAll(frequency.entrySet());

        return IntStream.range(0, k)
                .mapToObj(i -> priorityQueue.poll().getKey())
                .collect(Collectors.toList());
    }
}

package algorithm.number;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Return K most frequent elements
 * Example - {1,1,1,2,2,3} -> 1,2
 *
 * @since: 12.04.14
 * @author: Андрей
 */
public class TopFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequency.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(k)
                .mapToInt(num -> num)
                .toArray();
    }

    public int[] topKFrequentUsingQueue(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Map.Entry<Integer, Long>> priorityQueue = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        priorityQueue.addAll(frequency.entrySet());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }
}

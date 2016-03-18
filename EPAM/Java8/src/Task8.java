import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author Andrii_Tkachuk
 * @since 10/5/2015
 */
public class Task8 {

    private static final int TOP_COUNT = 10;
    private static final int LIMIT_70000 = 70000;
    private static final int TOP_LENGTH = 25;
    private static final int BOTTOM_LENGTH = 5;
    private static final String NOT_ALPHABETIC_PATTERN = "[^a-zA-Z]";
    private static final String EMPTY = "";

    public static List<String> task1Java7(List<String> words) {
        return words.parallelStream()
                .filter(word -> word.length() >= BOTTOM_LENGTH && word.length() < TOP_LENGTH)
                .map(Task8::removeNotAlphabeticSymbols)
                .map(String::toLowerCase)
                .collect(toList());
    }

    public static List<String> task2Java7(List<String> words) {
        return task1Java7(words).stream()
                .sorted(new WordComparator())
                .limit(LIMIT_70000)
                .collect(toList());
    }

    public static List<String> task3Java7(Path... paths) throws IOException {
        if (paths.length == 0) {
            return Collections.emptyList();
        } else {
            Map<String, Long> frequentlyWords = Arrays.stream(paths)
                    .flatMap(Task8::readFiles)
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .map(String::toLowerCase)
                    .collect(groupingBy(word -> word, counting()));

            List<String> result = frequentlyWords.entrySet().stream()
                    .sorted((word, word1) -> word1.getValue().compareTo(word.getValue()))
                    .limit(TOP_COUNT)
                    .map(Map.Entry::getKey)
                    .collect(toList());
            return result;
        }
    }

    private static String removeNotAlphabeticSymbols(String word) {
        return word.replaceAll(NOT_ALPHABETIC_PATTERN, EMPTY);
    }

    private static Stream<String> readFiles(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class WordComparator implements Comparator<String> {

        @Override
        public int compare(String word1, String word2) {
            int compare = word2.length() - word1.length();
            if (compare == 0) {
                return word1.compareTo(word2);
            } else {
                return compare;
            }
        }
    }
}

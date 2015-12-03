import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Andrii_Tkachuk
 * @since 10/5/2015
 */
public class Task7 {

    private static final int TOP_COUNT = 10;
    private static final int LIMIT_70000 = 70000;
    private static final int TOP_LENGTH = 25;
    private static final int BOTTOM_LENGTH = 5;
    private static final String NOT_ALPHABETIC_PATTERN = "[^a-zA-Z]";
    private static final String EMPTY = "";

    public static List<String> task1Java7(List<String> words) {
        List<String> result = new ArrayList<String>();
        for (String word : words) {
            if (word.length() >= BOTTOM_LENGTH && word.length() < TOP_LENGTH) {
                result.add(removeNotAlphabeticSymbols(word).toLowerCase());
            }
        }
        return result;
    }

    public static List<String> task2Java7(List<String> words) {
        List<String> resultWords = task1Java7(words);
        Collections.sort(resultWords, new WordComparator());
        return limitTo70000(resultWords);
    }

    public static List<String> task3Java7(Path... paths) {
        if (paths.length == 0) {
            return Collections.emptyList();
        } else {
            List<String> words = readFiles(paths);
            Map<String, Integer> frequentlyWords = calculateFrequentlyWords(words);
            List<String> result = top10FrequentlyWords(frequentlyWords);
            return result;
        }
    }

    private static String removeNotAlphabeticSymbols(String word) {
        return word.replaceAll(NOT_ALPHABETIC_PATTERN, EMPTY);
    }

    private static List<String> limitTo70000(List<String> resultWords) {
        if (resultWords.size() > LIMIT_70000) {
            resultWords = resultWords.subList(0, LIMIT_70000);
        }
        return resultWords;
    }

    private static List<String> readFiles(Path... path) {
        StringBuilder content = new StringBuilder();
        for (Path currentPath : path) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = Files.newBufferedReader(currentPath);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return Arrays.asList(content.toString().split("\\s+"));
    }

    private static Map<String, Integer> calculateFrequentlyWords(List<String> words) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String word : words) {
            word = word.toLowerCase();
            if (result.containsKey(word)) {
                result.put(word, result.get(word) + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }

    private static List<String> top10FrequentlyWords(Map<String, Integer> frequentlyWords) {
        List<String> result = new ArrayList<String>();
        ValueComparator valueComparator = new ValueComparator(frequentlyWords);
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(valueComparator);
        sortedMap.putAll(frequentlyWords);
        Iterator<String> iteratorWord = sortedMap.keySet().iterator();
        for (int i = 0; i < TOP_COUNT; i++) {
            if (iteratorWord.hasNext()) {
                result.add(iteratorWord.next());
            }
        }
        return result;
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

    private static class ValueComparator implements Comparator<String> {

        private Map<String, Integer> baseMap;

        public ValueComparator(Map<String, Integer> baseMap) {
            this.baseMap = baseMap;
        }

        @Override
        public int compare(String word1, String word2) {
            if(baseMap.get(word1) >= baseMap.get(word2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

/**
 * @author Andrii_Tkachuk
 * @since 10/13/2015
 */
public class MicroBenchmark {

    private static final int TEST_COUNT = 10;

    public static void main(String[] args) throws IOException {
        List<String> words = loadWords();
        System.out.println("--------TASK1----------");
        test("Task1, Java 7", () -> Task7.task1Java7(words));
        System.out.println("------------------------");
        test("Task1, Java 8", () -> Task8.task1Java7(words));
        System.out.println();
        System.out.println("--------TASK2----------");
        test("Task2, Java 7", () -> Task7.task2Java7(words));
        System.out.println("------------------------");
        test("Task2, Java 8", () -> Task8.task2Java7(words));
    }

    public static <T> void test(String label, Supplier<T> task) {
        for (int i = 0; i < TEST_COUNT; i++) {
            System.out.printf("%s: time - %d\n", label, oneRun(task));
        }
    }

    public static <T> long oneRun(Supplier<T> task) {
        long time = System.currentTimeMillis();

        task.get();

        return System.currentTimeMillis() - time;
    }

    private static List<String> loadWords() throws IOException {
        return Files.lines(Paths.get("samples/wordslist.txt")).collect(toList());
    }
}

package oracle;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordFrequencyTest {

    private WordFrequency subject = new WordFrequency();

    @Test
    public void findFrequencyWords () {
        List<String> words = subject.findFrequencyWords(Arrays.asList("hello", "hello", "hello", "test", "world", "help", "me", "help", "me", "help", "hello"));

        assertEquals(words.get(0), "hello");
        assertEquals(words.get(1), "help");
        assertEquals(words.get(2), "me");
    }
}
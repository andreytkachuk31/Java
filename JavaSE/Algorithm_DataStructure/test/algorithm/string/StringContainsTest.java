package algorithm.string;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringContainsTest {

    private StringContains subject = new StringContains();

    @Test
    public void containsInStart() {
        boolean result = subject.contains("hello world", "he");

        assertTrue(result);
    }

    @Test
    public void containsInMiddle() {
        boolean result = subject.contains("hello world", "llo");

        assertTrue(result);
    }

    @Test
    public void containsInEnd() {
        boolean result = subject.contains("hello world", "world");

        assertTrue(result);
    }

    @Test
    public void notContains() {
        boolean result = subject.contains("hello world", "wrld");

        assertFalse(result);
    }

    @Test
    public void notContainsDueToLength() {
        boolean result = subject.contains("hello world", "hello world more");

        assertFalse(result);
    }
}

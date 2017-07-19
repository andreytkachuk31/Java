package algorithm.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringReverserTest {

    private StringReverser subject = new StringReverser();

    @Test
    public void reverseOddLength() {
        String input = "hello";

        String reverse = this.subject.reverse(input);

        assertEquals("olleh", reverse);
    }

    @Test
    public void reverseEvenLength() {
        String input = "mytext";

        String reverse = this.subject.reverse(input);

        assertEquals("txetym", reverse);
    }
}
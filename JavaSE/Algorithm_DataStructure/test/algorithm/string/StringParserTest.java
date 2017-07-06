package algorithm.string;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class StringParserTest {

    private StringParser subject = new StringParser();

    @Test
    public void correctParseString() throws ParseException {
        String input = "123";

        int result = this.subject.parseString(input);

        assertEquals(123, result);
    }

    @Test(expected = ParseException.class)
    public void inCorrectParseString() throws ParseException {
        this.subject.parseString("123a");
    }
}
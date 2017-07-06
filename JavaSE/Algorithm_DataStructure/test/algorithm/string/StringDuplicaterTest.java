package algorithm.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringDuplicaterTest {

    private StringDuplicater subject = new StringDuplicater();

    @Test
    public void containsDuplicateCharactersInMiddle() {
        String input = "tessla";

        boolean result = this.subject.containsDuplicateCharacters(input);

        assertTrue(result);
    }

    @Test
    public void containsDuplicateCharactersInStart() {
        String input = "ttesla";

        boolean result = this.subject.containsDuplicateCharacters(input);

        assertTrue(result);
    }

    @Test
    public void containsDuplicateCharactersInEnd() {
        String input = "teslaa";

        boolean result = this.subject.containsDuplicateCharacters(input);

        assertTrue(result);
    }

    @Test
    public void notContainsDuplicateCharacters() {
        String input = "tesla";

        boolean result = this.subject.containsDuplicateCharacters(input);

        assertFalse(result);
    }
}
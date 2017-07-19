package algorithm.string;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringAnagramerTest {

    private StringAnagramer subject = new StringAnagramer();

    @Test
    public void isAnagram() {
        boolean result = this.subject.isAnagram("lun", "nul");

        assertTrue(result);
    }

    @Test
    public void isNotAnagram() {
        boolean result = this.subject.isAnagram("text", "notanagram");

        assertFalse(result);
    }
}
package algorithm.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArmstrongTest {

    private Armstrong subject = new Armstrong();

    @Test
    public void isArmstrongNumber() {
        assertTrue(this.subject.isArmstrongNumber(153));
    }

    @Test
    public void isNotArmstrongNumber() {
        assertFalse(this.subject.isArmstrongNumber(154));
    }
}
package algorithm.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumPairTest {

    private SumPair subject = new SumPair();

    @Test
    public void notHasPairWithSum() {
        assertFalse(this.subject.hasPairWithSum(new int[]{1, 2, 3, 9}, 8));
    }

    @Test
    public void hasPairWithSum() {
        assertTrue(this.subject.hasPairWithSum(new int[]{1, 2, 4, 4}, 8));
    }

    @Test
    public void hasPairWithSum_1() {
        assertTrue(this.subject.hasPairWithSum(new int[]{1, 2, 4}, 6));
    }
}
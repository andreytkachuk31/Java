package algorithm.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KadaneTest {

    private Kadane subject = new Kadane();

    @Test
    public void maxSumWholeArray() {
        assertEquals(this.subject.kadane(new int[]{1, 2, 3, -2, 5}), 9);
    }

    @Test
    public void maxSumWholeArray1() {
        assertEquals(this.subject.kadane(new int[]{1, 2, 3, -2, 1, 10}), 15);
    }

    @Test
    public void maxSumAllNegative() {
        assertEquals(this.subject.kadane(new int[]{-4, -3, -2, -1}), -1);
    }

    @Test
    public void maxSumSubArray() {
        assertEquals(this.subject.kadane(new int[]{1, 2, 3, -8, 1, 10}), 11);
    }

    @Test
    public void maxSumSubArray1() {
        assertEquals(this.subject.kadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
    }

    @Test
    public void maxSumSubArray2() {
        assertEquals(this.subject.kadane(new int[]{74, -72, 94, -53, -59, -3, -66, 36, -13, 22, 73, 15, -52, 75}), 156);
    }

    @Test
    public void maxSumSubArray3() {
        assertEquals(this.subject.kadane(new int[]{-47, 43, 94, -94, -93, -59, 31, -86}), 137);
    }

    @Test
    public void maxSumSubArray4() {
        assertEquals(this.subject.kadane(new int[]{-34, 95, -75, -17, 59, 53, -100, 59, 42, -24, -27, -61, -95, -96, 29}), 116);
    }
}
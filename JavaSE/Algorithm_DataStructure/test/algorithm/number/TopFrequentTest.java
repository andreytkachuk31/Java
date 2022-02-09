package algorithm.number;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TopFrequentTest {

    private TopFrequent subject = new TopFrequent();

    @Test
    public void topFrequent() {
        assertArrayEquals(new int[]{1, 2}, this.subject.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }

    @Test
    public void topSingleFrequent() {
        assertArrayEquals(new int[]{1}, this.subject.topKFrequent(new int[]{1}, 1));
    }
}
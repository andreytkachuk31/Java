package algorithm.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    private BinarySearch subject = new BinarySearch();

    @Test
    public void binarySearchOdd() {
        int array[] = {1, 4, 5, 7, 10};

        int index = subject.binarySearch(array, 4);

        assertEquals(1, index);
    }

    @Test
    public void binarySearchEven() {
        int array[] = {1, 4, 5, 7, 10, 11};

        int index = subject.binarySearch(array, 10);

        assertEquals(4, index);
    }

    @Test
    public void binarySearchNotFind() {
        int array[] = {1, 4, 5, 7};

        int index = subject.binarySearch(array, 20);

        assertEquals(-1, index);
    }
}
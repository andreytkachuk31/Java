package structure.tree.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeRekursiaTest {

    private BinaryTreeRekursia<Integer, Integer> subject = new BinaryTreeRekursia();

    @Test
    public void findDeepLevelThird() {
        this.subject.put(30, 30);
        this.subject.put(40, 40);
        this.subject.put(39, 39);

        int result = this.subject.findDeepLevel();

        assertEquals(3, result);
    }

    @Test
    public void findDeepLevelFour() {
        this.subject.put(30, 30);
        this.subject.put(21, 21);
        this.subject.put(18, 18);
        this.subject.put(20, 20);
        this.subject.put(40, 40);
        this.subject.put(39, 39);

        int result = this.subject.findDeepLevel();

        assertEquals(4, result);
    }

    @Test
    public void findDeepLevelFive() {
        this.subject.put(30, 30);
        this.subject.put(21, 21);
        this.subject.put(18, 18);
        this.subject.put(20, 20);
        this.subject.put(40, 40);
        this.subject.put(39, 39);
        this.subject.put(19, 19);

        int result = this.subject.findDeepLevel();

        assertEquals(5, result);
    }
}
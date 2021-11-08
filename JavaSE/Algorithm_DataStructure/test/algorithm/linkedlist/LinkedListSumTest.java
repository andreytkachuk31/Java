package algorithm.linkedlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedListSumTest {

    private LinkedListSum subject = new LinkedListSum();

    @Test
    public void addTwoListsWithoutTransfer() {
        Node first = createList(new int[]{3, 4, 1});
        Node second = createList(new int[]{4, 5});
        Node result = subject.sumTwoLists(first, second);
        assertEquals(3, result.data);
        assertEquals(8, result.next.data);
        assertEquals(6, result.next.next.data);
    }

    @Test
    public void addTwoListsWithTransfer() {
        Node first = createList(new int[]{3, 4, 5});
        Node second = createList(new int[]{4, 5});
        Node result = subject.sumTwoLists(first, second);
        assertEquals(3, result.data);
        assertEquals(9, result.next.data);
        assertEquals(0, result.next.next.data);
    }

    @Test
    public void addTwoListsWithTransferInFirstElement() {
        Node first = createList(new int[]{3, 4, 5});
        Node second = createList(new int[]{8, 5});
        Node result = subject.sumTwoLists(first, second);
        assertEquals(4, result.data);
        assertEquals(3, result.next.data);
        assertEquals(0, result.next.next.data);
    }

    @Test
    public void addTwoListsWithNewElement() {
        Node first = createList(new int[]{9, 4, 5});
        Node second = createList(new int[]{8, 5});
        Node result = subject.sumTwoLists(first, second);
        assertEquals(1, result.data);
        assertEquals(0, result.next.data);
        assertEquals(3, result.next.next.data);
        assertEquals(0, result.next.next.next.data);
    }

    private static Node createList(int[] values) {
        Node root = new Node(values[0]);
        Node curr = root;
        for (int i = 1; i < values.length; i++) {
            Node node = new Node(values[i]);
            curr.next = node;
            curr = node;
        }
        return root;
    }

    private static void printLinkedList(Node root) {
        Node node = root;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }
}
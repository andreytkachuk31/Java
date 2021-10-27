package algorithm.linkedlist;

public class LinkedListMiddle {

    public static int getMiddle(Node head) {
        Node singleCurrentNode = head;
        Node doubleCurrentNode = head;
        while (doubleCurrentNode != null && doubleCurrentNode.next != null) {
            singleCurrentNode = singleCurrentNode.next;
            doubleCurrentNode = doubleCurrentNode.next.next;
        }
        return singleCurrentNode.data;
    }
}

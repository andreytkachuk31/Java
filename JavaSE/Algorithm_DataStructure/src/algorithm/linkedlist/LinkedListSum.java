package algorithm.linkedlist;

public class LinkedListSum {

    public static Node sumTwoLists(Node first, Node second) {
        Node[] firstAndSecond = addPrecedingZeros(first, second);
        Node result = new Node(0);
        if (sumTwoLists(firstAndSecond[0], firstAndSecond[1], result) == 1) {
            Node node = new Node(1);
            node.next = result.next;
            result.next = node;
        }
        return result.next;
    }

    private static int sumTwoLists(Node first, Node second, Node result) {
        if (first == null) {
            return 0;
        }
        Node node = new Node(0);
        result.next = node;
        result = node;
        int carry = sumTwoLists(first.next, second.next, result);
        int resultSum = first.data + second.data + carry;
        result.data = resultSum % 10;
        return resultSum / 10;
    }

    private static Node[] addPrecedingZeros(Node first, Node second) {
        Node nodeFirst = first;
        Node nodeSecond = second;
        while (nodeFirst != null || nodeSecond != null) {
            if (nodeFirst == null) {
                Node curr = new Node(0);
                curr.next = first;
                first = curr;
            } else {
                nodeFirst = nodeFirst.next;
            }
            if (nodeSecond == null) {
                Node curr = new Node(0);
                curr.next = second;
                second = curr;
            } else {
                nodeSecond = nodeSecond.next;
            }
        }
        return new Node[]{first, second};
    }
}

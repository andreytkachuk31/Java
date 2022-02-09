package algorithm.linkedlist;

public class LinkedListReverse {

    public static Node reverseList(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node head = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return head;
    }

/*    public static Node reverseList(Node root) {
        Node prev = null;
        Node curr = root;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        root = prev;

        return root;
    }

    public static Node reverseListUsingStack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node.next != null) {
            stack.push(node);
            node = node.next;
        }

        Node head = node;
        while (!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }

        node.next = null;

        return head;
    }*/
}

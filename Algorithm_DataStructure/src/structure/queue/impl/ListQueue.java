package structure.queue.impl;

import structure.queue.Queue;

import java.util.Iterator;

/**
 * Очередь на основе списка
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ListQueue<Item> implements Queue<Item> {

    private Node first;
    private Node last;
    private int size;

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size();
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class Node {
        private Item item;
        private Node next;
    }

    private class ListIterator<Item> implements Iterator<Item> {

        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

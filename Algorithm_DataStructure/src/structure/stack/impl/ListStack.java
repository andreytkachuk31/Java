package structure.stack.impl;

import structure.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Стек на основе списка
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ListStack<Item> implements Stack<Item> {

    private Node first;
    private int size;

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = (Item) first.item;
        first = first.next;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class Node {
        Item item;
        Node next;
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

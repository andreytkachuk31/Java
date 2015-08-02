package structure.queue.impl;

import structure.queue.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Очередь на основе массива
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ArrayQueue<Item> implements Queue<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] queue;
    private int head;
    private int size;

    public ArrayQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        queue = new Object[capacity];
    }


    public void enqueue(Item item) {
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[(head + size) % queue.length] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = (Item) queue[head];
        queue[head] = null;
        head = (head + 1) % queue.length;
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
        return new QueueIterator<Item>();
    }

    private void resize(int newCapacity) {
        Object[] temp = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
        head = 0;
    }

    private class QueueIterator<Item> implements Iterator<Item> {

        private int cursor = head;
        private int tail = head + size;

        public boolean hasNext() {
            return cursor < tail;
        }

        public Item next() {
            return (Item) queue[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

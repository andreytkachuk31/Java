package structure.queue.impl.priority;

import sort.utils.SortUtils;
import structure.queue.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Очередь c приоритетами на основе массива (не упорядоченное) (не циклическая)
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ArrayPriorityQueue1<Item extends Comparable> implements Queue<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] queue;
    private int head;
    private int tail;

    public ArrayPriorityQueue1() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayPriorityQueue1(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        queue = new Object[capacity];
    }

    public void enqueue(Item item) {
        if (tail == queue.length) {
            resize(queue.length * 2);
        }
        queue[tail++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int min = head;
        for (int i = head + 1; i < tail; i++) {
            if (SortUtils.less((Item) queue[i], (Item) queue[min])) {
                min = i;
            }
        }
        if (head != min) {
            swap(queue, head, min);
        }
        Item item = (Item) queue[head];
        queue[head++] = null;
        return item;
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public int size() {
        return tail - head;
    }

    public Iterator iterator() {
        return new QueueIterator<Item>();
    }

    private void resize(int newCapacity) {
        Object[] temp = new Object[newCapacity];
        for (int i = head; i < tail; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    private void swap(Object[] data, int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private class QueueIterator<Item extends Comparable> implements Iterator<Item> {

        private int cursor = head;
        private Object array[] = queue;

        public boolean hasNext() {
            return cursor < tail;
        }

        public Item next() {
            int min = cursor;
            for (int i = cursor + 1; i < tail; i++) {
                if (SortUtils.less((Item) queue[i], (Item) queue[min])) {
                    min = i;
                }
            }
            if (cursor != min) {
                swap(array, cursor, min);
            }
            return (Item) queue[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

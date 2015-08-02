package structure.queue.impl.priority;

import sort.utils.SortUtils;
import structure.queue.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Очередь c приоритетами на основе массива (упорядоченное) (не циклическая)
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ArrayPriorityQueue<Item extends Comparable> implements Queue<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] queue;
    private int head;
    private int tail;

    public ArrayPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayPriorityQueue(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        queue = new Object[capacity];
    }

    public void enqueue(Item item) {
        if (tail == queue.length) {
            resize(queue.length * 2);
        }
        if (tail == 0) {
            queue[0] = item;
        } else {
            int i;
            for (i = tail - 1; i >= head && SortUtils.less(item, (Comparable) queue[i]); i--) {
                queue[i + 1] = queue[i];
            }
            queue[i + 1] = item;
        }
        tail++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
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

    private class QueueIterator<Item extends Comparable> implements Iterator<Item> {

        private int cursor = head;

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

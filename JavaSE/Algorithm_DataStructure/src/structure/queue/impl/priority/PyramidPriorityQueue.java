package structure.queue.impl.priority;

import sort.utils.SortUtils;
import structure.queue.Queue;
import java.util.Iterator;

/**
 * Очередь c приоритетами на основе пирамиды
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class PyramidPriorityQueue<Item extends Comparable<Item>> implements Queue<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] queue;
    private int head = 1;
    private int size;

    public PyramidPriorityQueue() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public PyramidPriorityQueue(int capacity) {
        queue = new Object[capacity + 1];
    }

    public void enqueue(Item item) {
        int tail = head + size;
        queue[tail] = item;
        swim(tail);
        size++;
    }

    public Item dequeue() {
        int tail = head + size;
        Item item = (Item) queue[head];
        swap(queue, head, tail - 1);
        queue[tail] = null;
        size--;
        sink(head);
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

    private class QueueIterator<Item> implements Iterator<Item> {

        private int cursor = head + 1;
        private int tail = head + size;

        public boolean hasNext() {
            return cursor < tail;
        }

        public Item next() {
            cursor++;
            return (Item) dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Возходящее восстановление пирамидальности
     */
    private void swim(int k) {
        while (k > 1 && SortUtils.less((Item) queue[k / 2], (Item) queue[k])) {
            swap(queue, k, k / 2);
            k /= 2;
        }
    }

    /**
     * Низходящее восстановление пирамидальности
     */
    private void sink(int k) {
        while (2 * k <= size) {
            /*Нахождение максимального потомка*/
            int j = 2 * k;
            if (j < size && SortUtils.less((Item) queue[j], (Item) queue[j + 1])) j++;
            /*Обмен*/
            if (!SortUtils.less((Item) queue[k], (Item) queue[j])) break;
            swap(queue, k, j);
            k = j;
        }
    }

    private void swap(Object[] data, int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

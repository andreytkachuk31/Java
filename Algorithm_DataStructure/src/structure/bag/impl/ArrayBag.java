package structure.bag.impl;

import structure.bag.Bag;

import java.util.Iterator;

/**
 * Контейнер на основе массиве
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ArrayBag<Item> implements Bag<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] bag;
    private int size;

    public ArrayBag() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBag(int capacity) {
        bag = new Object[capacity];
    }

    public void add(Item item) {
        if (size == bag.length) {
            resize(bag.length * 2);
        }
        bag[size++] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator<Item>();
    }

    private void resize(int newCapacity) {
        Object[] temp = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = bag[i];
        }
        bag = temp;
    }

    private class ReverseArrayIterator<Item> implements Iterator<Item>{

        private int cursor = size;

        public boolean hasNext() {
            return cursor > 0;
        }

        public Item next() {
            return (Item) bag[--cursor];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

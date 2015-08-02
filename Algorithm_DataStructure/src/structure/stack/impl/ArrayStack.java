package structure.stack.impl;

import structure.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Стек c динамическим размером массива
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class ArrayStack<Item> implements Stack<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] stack;
    private int size;

    public ArrayStack() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        stack = new Object[capacity];
    }

    public void push(Item item) {
        if (size == stack.length) {
            resize(stack.length * 2);
        }
        stack[size++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = (Item) stack[--size];
        stack[size] = null;
        if (size > 0 && size == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
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
            temp[i] = stack[i];
        }
        stack = temp;
    }

    private class ReverseArrayIterator<Item> implements Iterator<Item>{

        private int cursor = size;

        public boolean hasNext() {
            return cursor > 0;
        }

        public Item next() {
            return (Item) stack[--cursor];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

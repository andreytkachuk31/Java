package structure.stack.impl;

import structure.stack.Stack;

import java.util.Iterator;

/**
 * Стек c фиксированным размером массива
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public class FixedArrayStack<Item> implements Stack<Item> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] stack;
    private int size;

    public FixedArrayStack() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public FixedArrayStack(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        stack = new Object[capacity];
    }

    public void push(Item item) {
        if (isFull()) {
            throw new RuntimeException();
        }
        stack[size++] = item;
    }

    public Item pop() {
        if (isEmpty())
            throw new RuntimeException();
        return (Item) stack[--size];
    }

    public boolean isFull() {
        return size == stack.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return null;
    }
}

package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */

public class LinkedQueue<T> implements Queue<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     *
     * Constructs LinkedQueue with default capacity;
     *
     */

    LinkedQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     *
     * Constructs LinkedQueue with custom capacity;
     *
     */

    LinkedQueue(int capacity) {
        if (capacity < 0) {
            elements = new Object[size];
        }
        else {
            throw new IllegalArgumentException("Size can't be negative: " + size);
        }
    }

    /**
     *
     * Adding element to LinkedQueue, if it's full create a new array with bigger capacity.
     *
     */

    @Override
    public void add(T element) {
        if (size < elements.length) {
        elements[size] = element;
        }
        else
            {
            Object[] temp = new Object[(size()*3)/2 + 1];
            System.arraycopy(elements, 0, temp, 0, size());
            elements = temp;
            elements[size] = element;
        }
        size++;

    }

    /**
     *
     * Poll element from LinkedQueue and shift queue with System.arraycopy;
     * If queue is empty return null;
     */

    @Override
    public T poll() {
        T element = null;
        if(size() != 0) {
            element = (T) elements[0];
            System.arraycopy(elements, 1, elements, 0, size()-1);
            size--;

        }
        return element;
    }

    /**
     *
     * Return size() of LinkedQueue;
     *
     */

    @Override
    public int size() {
        return size;
    }

    /**
     *
     * Check LinkedQueue, if it's empty return true;
     *
     */

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

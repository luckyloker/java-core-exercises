package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */

public class LinkedQueue<T> implements Queue<T> {

    private Node<T> tail;
    private Node<T> head;
    private int size;

    private class Node<T> {
        T element;
        Node<T> next;

        private Node(T element) {
            this.element = element;
        }
    }

    @Override
    public void add(T element) {
        Node<T> tempNode = new Node<>(element);
        if(isEmpty()) {
            head = tempNode;
        }
        else {
            tail.next = tempNode;
        }
        tail = tempNode;
        size++;
    }

    @Override
    public T poll() {
        if(!isEmpty()) {
            T element = head.element;
            head = head.next;
            size--;
            return element;
        }
        else {
            return null;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}


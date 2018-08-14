package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    private static final class Node<T> {
        T data;
        Node<T> next;

        private Node(T data) {
            this.data = data;
        }

        static <T> Node<T> valueOf(T data) {
            return new Node<>(data);
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T element) {
        Node<T> node = Node.valueOf(element);
        if (isEmpty()) {
            first = last = node;
            size++;
        } else {
            last.next = node;
            last = last.next;
            size++;
        }
    }

    @Override
    public T poll() {
        if (first != null) {
            T data = first.data;
            first = first.next;
            size--;
            return data;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }
}

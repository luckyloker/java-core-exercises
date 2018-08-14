package com.bobocode;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

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
    private int size;

    public LinkedList() {
        first = null;
        size = 0;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    @SafeVarargs
    static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        //Stream.of(elements).forEach(list::add);
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> node = Node.valueOf(element);
        if (isEmpty()) {
            first = node;
        } else {
            Node<T> last = findLast(first);
            last.next = node;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        checkBoundsToAddAt(index);
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            if (first != null) {
                newNode.next = first;
            }
            first = newNode;
        } else {
            Node<T> node = findNodeByIndex(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    private Node<T> findLast(Node<T> first) {
        Node<T> current = Objects.requireNonNull(first);
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    private void checkBoundsToAddAt(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> findNodeByIndex(int index) {
        verifyElementExistAt(index);
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void verifyElementExistAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean exists(T element) {
        Node<T> current = first;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        verifyElementExistAt(index);
        if (index == 0) {
            if (first == null) {
                first = Node.valueOf(element);
                size++;
            } else {
                first.data = element;
            }
        } else {
            Node<T> node = findNodeByIndex(index);
            node.data = element;
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        Node<T> node = findNodeByIndex(index);
        return node.data;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public void remove(int index) {
        verifyElementExistAt(index);
        if (index == 0) {
            first = first.next;
        } else {
            Node<T> previous = findNodeByIndex(index - 1);
            previous.next = previous.next.next;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (first == null) {
            return false;
        } else {
            return exists(element);
        }
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }
}

package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    static private class Node<T> {
        private T element;
        private Node<T> next;

        private Node(T element) {
            this.element = element;
        }

    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
       LinkedList<T> list = new LinkedList<>();
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
        Node<T> tempNode = new Node<>(element);
        if (isEmpty()) {
            head = tempNode;
        }
        else {
            Node<T> tailNode = head;
            while(tailNode.next!=null) {
                tailNode = tailNode.next;
            }
            tailNode.next = tempNode;
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
        indexBoundCheck(index);
        Node<T> tempNode = new Node<>(element);
        if (index == 0) {
            tempNode.next = head;
            head = tempNode;
            size++;
        } else if (index == size) {
            Node<T> indexNode = findIndex(index - 1);
            indexNode.next = tempNode;
            size++;
        } else {
            Node<T> indexNode = findIndex(index-1);
            tempNode.next = indexNode.next;
            indexNode.next = tempNode;
            size++;
            }
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
        indexBoundCheck(index);
        if(index == size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> tempNode = new Node<>(element);
        Node<T> indexNode = findIndex(index);
        indexNode.element = tempNode.element;
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
        indexBoundCheck(index);
        if (size == 0 || index == size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> indexNode = findIndex(index);
            return indexNode.element;
        }
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
        indexBoundCheck(index);
        int count = 0;
        Node<T> indexNode = head;
        if(index == 0) {
            head = head.next;
        }
        else {
        while(count != index - 1) {
            indexNode = indexNode.next;
            count++;
        }
        indexNode.next = indexNode.next.next;
        size--;
    }
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
       Node<T> indexNode = head;
       if (size == 0) {
           return false;
       }
       do {
           if(indexNode.element.equals(element)) {
               return true;
           }
           indexNode = indexNode.next;
       }
        while (indexNode.next != null);
       return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        head = null;
        size = 0;
    }

    private void indexBoundCheck(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> findIndex(int index) {
        Node<T> indexNode = head;
        int count = 0;
        while (count != index) {
            indexNode = indexNode.next;
            count++;
        }
        return indexNode;
    }
}

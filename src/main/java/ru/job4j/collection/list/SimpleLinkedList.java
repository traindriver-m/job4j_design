package ru.job4j.collection.list;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;
    private Node<E> current;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tempNode;
        if (index <= size / 2) {
            tempNode = first;
            for (int i = 0; i < index; i++) {
                tempNode = tempNode.next;
            }
        } else {
            tempNode = last;
            for (int i = size - 1; i > index; i--) {
                tempNode = tempNode.prev;
            }
        }
        return tempNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        current = first;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = current.item;
                current = current.next;
                return rsl;
            }
        };
    }
}
package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int outSize;


    public T poll() {
        if (size == 0 && outSize == 0) {
            throw new NoSuchElementException();
        }
        if (outSize == 0) {
            while (size > 0) {
                out.push(in.pop());
                outSize++;
                size--;
            }
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
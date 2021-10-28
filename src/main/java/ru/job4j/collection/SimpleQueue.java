package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int outSize;


    public T poll() {
        if (outSize == 0) {
            while (outSize != size) {
                out.push(in.pop());
                outSize++;
            }
            size = 0;
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
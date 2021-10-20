package ru.job4j.iterator;

import java.util.*;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
    /*Класс FlatMap принимает объект вложенных итераторов.

        В классе нужно реализовать два метода: next и hasNext.

        Метод next должен последовательно вернуть числа из вложенных итераторов.

        В этом задании нельзя копировать элементы во временный список.

        Ниже приведен код, как делать нельзя.

    private List<T> temp = new ArrayList<>();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        while (data.hasNext()) {
            Iterator<T> inner = data.next();
            while (inner.hasNext()) {
                temp.add(inner.next());
            }
        }
    } */
package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private int index;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = mapEntry;
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        System.arraycopy(table, 0, tempTable, 0, table.length);
        table = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : tempTable) {
            if (kvMapEntry != null) {
                put(kvMapEntry.key, kvMapEntry.value);
                count--;
            }
        }
    }

    @Override
    public V get(K key) {
        V result;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && index < capacity) {
            result = table[index].value;
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && index < capacity) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        index = 0;
        final int expectedModCount = modCount;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K next = null;
                if (table[index] != null) {
                    next = table[index].key;
                }
                index++;
                return next;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}



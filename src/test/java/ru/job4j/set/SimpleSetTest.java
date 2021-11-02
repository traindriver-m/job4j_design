package ru.job4j.set;

import org.junit.Test;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(3));
        assertTrue(set.add(null));
        assertTrue( set.add(6));
        assertTrue(set.add(12));
        assertTrue(set.contains(null));
        assertTrue(set.contains(6));
        assertFalse(set.add(null));
        System.out.println(set.iterator().next());
        for (Integer integer : set) {
            System.out.println(integer);
        }

    }

}
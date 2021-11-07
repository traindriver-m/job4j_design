package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public <K, V> void whenPutTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("2", "TWO"));
        assertTrue(map.put("3", "THREE"));
        assertTrue(map.put("4", "FOUR"));
    }

    @Test
    public <K, V> void whenPutFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("2", "TWO"));
        assertFalse(map.put("2", "TWO-TWO"));
    }

    @Test
    public <K, V> void whenMapExpand() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("4", "FOUR"));
        assertTrue(map.put("2", "TWO"));
        assertTrue(map.put("7", "SEVEN"));
        assertTrue(map.put("3", "THREE"));
        assertTrue(map.put("5", "FIVE"));
        assertTrue(map.put("6", "SIX"));
        assertTrue(map.put("8", "EIGHT"));
        assertTrue(map.put("9", "NINE"));
        assertTrue(map.put("10", "TEN"));
        assertThat("TEN", is(map.get("10")));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("4", "FOUR"));
        assertTrue(map.put("5", "FIVE"));
        assertTrue(map.put("6", "SIX"));
        assertThat("FOUR", is(map.get("4")));
    }

    @Test
    public void whenGetIsNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("4", "FOUR"));
        assertNull(map.get("6"));
    }

    @Test
    public void whenRemoveIsTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("2", "TWO"));
        assertTrue(map.put("3", "THREE"));
        assertTrue(map.put("4", "FOUR"));
        assertTrue(map.remove("2"));
        assertNull(map.get("2"));
    }

    @Test
    public void whenRemoveIsFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("2", "TWO"));
        assertTrue(map.put("3", "THREE"));
        assertTrue(map.put("4", "FOUR"));
        assertFalse(map.remove("5"));
    }

    @Test
    public void iterator() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("0", "ZERO"));
        assertTrue(map.put("1", "ONE"));
        assertTrue(map.put("3", "THREE"));
        Iterator<String> it = map.iterator();
        assertThat(it.next(), Is.is("0"));
        assertThat(it.next(), Is.is("1"));

    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementExeption() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("0", "ZERO"));
        Iterator<String> it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModify() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("0", "ZERO"));
        assertTrue(map.put("1", "ONE"));
        Iterator<String> it = map.iterator();
        assertTrue(map.put("4", "FOUR"));
        it.next();
    }
}
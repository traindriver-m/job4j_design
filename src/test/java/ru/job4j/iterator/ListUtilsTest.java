package ru.job4j.iterator;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.addAfter(input, 2, 10);
        assertThat(input, is(Arrays.asList(0, 1, 2, 10, 3, 4)));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenDeleteByPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, s -> s > 2);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void whenReplaceByPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, s -> s < 3, 100);
        assertThat(input, is(Arrays.asList(100, 100, 100, 3, 4)));
    }

    @Test
    public void whenRemoveAllByElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 4)));
    }

}
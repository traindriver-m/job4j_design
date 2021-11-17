package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairContainsComment() {
        String path = "./data/comment.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairBreaksPattern() {
        String path = "./data/pattern.properties";
        Config config = new Config(path);
        config.load();
    }

}
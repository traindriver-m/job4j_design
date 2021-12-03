package ru.job4j.serialization.json;

public class Driver {
    private final String name;
    private final int category;

    public Driver(String name, int category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "name='" + name + '\''
                + ", category=" + category
                + '}';
    }
}

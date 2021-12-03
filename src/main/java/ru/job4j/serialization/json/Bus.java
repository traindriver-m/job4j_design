package ru.job4j.serialization.json;

import java.util.Arrays;

public class Bus {
    private final String model;
    private final int release;
    private final boolean petrol;
    private final Driver driver;
    private final int[] routes;

    public Bus(String model, int release, boolean petrol, Driver driver, int[] routes) {
        this.model = model;
        this.release = release;
        this.petrol = petrol;
        this.driver = driver;
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Bus{"
                + "model='" + model + '\''
                + ", release=" + release
                + ", petrol=" + petrol
                + ", driver=" + driver
                + ", routes=" + Arrays.toString(routes)
                + '}';
    }
}

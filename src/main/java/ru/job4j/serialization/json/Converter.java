package ru.job4j.serialization.json;

import org.json.JSONObject;

public class Converter {
    public static void main(String[] args) {
        final Bus bus = new Bus("Ikarus", 1984, false, new Driver("Vasiliy", 1),
                new int[]{738, 742, 740});
        JSONObject jsonBus = new JSONObject(bus);
        System.out.println(jsonBus.toString());
    }
}

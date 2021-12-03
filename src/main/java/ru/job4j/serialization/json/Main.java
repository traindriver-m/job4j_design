package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Bus bus = new Bus("Ikarus", 1984, false, new Driver("Vasiliy", 1),
                new int[]{738, 742, 740});
        final Gson gson = new GsonBuilder().create();
        final String busToJson = gson.toJson(bus);
        System.out.println(busToJson);
        final Bus busMod = gson.fromJson(busToJson, Bus.class);
        System.out.println(busMod);
    }
}

package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private static final Map<String, String> VALUES = new HashMap<>();

    public String get(String key) {
        return VALUES.get(key);
    }

    public static Map<String, String> getValues() {
        return VALUES;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Incorrect number of parameters to run.");
        }
        validation(args);
    }

    private static Map<String, String> validation(String[] args) {
        for (String string : args) {
            if (!string.contains("=") && !string.startsWith("-") && string.endsWith("=") && string.charAt(1) == '=') {
                throw new IllegalArgumentException("Incorrect program launch parameters.");
            }
            String[] parameter = string.split("=");
            String key = parameter[0];
            String value = parameter[1];
            key = key.replaceFirst("-", "");
            VALUES.put(key, value);
        }
        return VALUES;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        validation(args);
        return names;
    }
}

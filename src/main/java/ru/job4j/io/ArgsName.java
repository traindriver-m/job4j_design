package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Incorrect number of parameters to run.");
        }
        validation(args);
    }

    private Map<String, String> validation(String[] args) {
        for (String string : args) {
            if (!string.contains("=") && !string.startsWith("-") && string.endsWith("=") && string.charAt(1) == '=') {
                throw new IllegalArgumentException("Incorrect program launch parameters.");
            }
            String[] parameter = string.split("=");
            String key = parameter[0];
            String value = parameter[1];
            key = key.replaceFirst("-", "");
            values.put(key, value);
        }
        return values;
    }

    public ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        validation(args);
        return names;
    }
}

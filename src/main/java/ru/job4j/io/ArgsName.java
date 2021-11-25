package ru.job4j.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect number of parameters to run.");
        }
        validation(args);
    }

    private void validation(String[] args) {
        for (String string : args) {
            if (string.contains("=") && string.startsWith("-") && !string.endsWith("=") && string.charAt(1) != '=') {
                String[] parameter = string.split("=");
                String key = parameter[0];

                String value = parameter[1];
                key = key.replace("-", "");
                if (key.equals("d") || key.equals("e") || key.equals("o")) {
                    values.put(key, value);
                } else {
                    throw new IllegalArgumentException("Incorrect program launch parameters.");
                }
            } else {
                throw new IllegalArgumentException("Incorrect program launch parameters.");
            }
        }
        checkDirectory();
        Search.filter(values);
    }

    private void checkDirectory() {
        File file = new File(values.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public void of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
    }
}

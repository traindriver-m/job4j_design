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
            if (!string.contains("=") && !string.startsWith("-") && string.endsWith("=") && string.charAt(1) == '=') {
                throw new IllegalArgumentException("Incorrect program launch parameters.");
            }
                String[] parameter = string.split("=");
                String key = parameter[0];
                String value = parameter[1];
                key = key.replace("-", "");
                if ("d".equals(key) || "e".equals(key) || "o".equals(key)) {
                    values.put(key, value);
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

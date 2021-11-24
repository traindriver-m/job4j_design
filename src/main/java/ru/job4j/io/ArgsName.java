package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length > 0) {
            validation(args);
        } else {
            throw new IllegalArgumentException("There are no parameters to run.");
        }
    }

    private void validation(String[] args) {
        for (String string : args) {
            if (string.contains("=") && string.startsWith("-") && !string.endsWith("=") && string.charAt(1) != '=') {
                String[] parameter = string.split("=");
                String key = parameter[0];
                String value = parameter[1];
                key = key.replace("-", "");
                values.put(key, value);
            } else {
                throw new IllegalArgumentException("Incorrect program launch parameters.");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        ArgsName error = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(error.get("out"));
    }
}

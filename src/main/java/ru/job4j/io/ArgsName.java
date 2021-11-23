package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        String key;
        String value;
        String[] parameter;
        if (args.length > 0) {
            for (String string : args) {
                parameter = string.split("=");
                if (parameter.length < 2) {
                    throw new IllegalArgumentException();
                }
                key = parameter[0];
                value = parameter[1];
                key = key.replace("-", "");
                values.put(key, value);
            }
        } else {
            throw new IllegalArgumentException();
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
    }
}

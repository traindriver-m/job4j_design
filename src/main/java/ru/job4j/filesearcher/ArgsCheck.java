package ru.job4j.filesearcher;

import java.util.HashMap;
import java.util.Map;

public class ArgsCheck {
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
        argsValidation(args);
    }

    private static Map<String, String> argsValidation(String[] args) {
        for (String string : args) {
            if (!string.contains("=") || !string.startsWith("-") || string.endsWith("=") || string.charAt(1) == '=') {
                throw new IllegalArgumentException("Incorrect program launch parameters" + System.lineSeparator()
                        + "Parameters for launching the program:\n"
                        + "-d= directory to start the search.\n"
                        + "-n=file name, mask, regular expression.\n"
                        + "-t=search type(name/mask/regex).\n"
                        + "-o=the name of the file to record the result.");
            }
            String[] parameter = string.split("=");
            if (parameter.length != 2) {
                throw new IllegalArgumentException("Incorrect program startup parameters."
                        + " The parameter should have the form: -key=value");
            }
            String key = parameter[0];
            String value = parameter[1];
            key = key.replaceFirst("-", "");
            VALUES.put(key, value);
        }
        return VALUES;
    }

    public static ArgsCheck of(String[] args) {
        ArgsCheck checkResult = new ArgsCheck();
        checkResult.parse(args);
        argsValidation(args);
        return checkResult;
    }
}

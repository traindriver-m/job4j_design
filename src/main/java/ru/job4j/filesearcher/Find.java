package ru.job4j.filesearcher;

import java.util.Map;

public class Find {
    public static void main(String[] args) {
        ArgsCheck.of(args);
        Map<String, String> argsMap = ArgsCheck.getValues();
        Search.validationKeys(argsMap);
    }
}

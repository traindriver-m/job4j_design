package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class Search {

    public static void validationKeys(Map<String, String> values) {
        if (values.size() != 3 || !values.containsKey("d") || !values.containsKey("e") || !values.containsKey("o")) {
            throw new IllegalArgumentException("Incorrect program launch parameters.");
        }
        filter(values);
    }

    public static void filter(Map<String, String> values) {
        Path start = Paths.get(values.get("d"));
        List<Path> paths = search(start, p -> !p.toFile().getName().endsWith(values.get("e")));
        File target = new File(values.get("o"));
        checkDirectory(values);
        Zip.packFiles(paths, target);
    }

    private static void checkDirectory(Map<String, String> values) {
        File file = new File(values.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public static void check(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        } else if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters. "
                    + "Set two parameters to run the program.");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static void main(String[] args) {
        check(args);
    }
}


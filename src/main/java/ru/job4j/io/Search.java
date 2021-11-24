package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class Search {

    public static void filter(Map<String, String> values) {
        Path start = Paths.get(values.get("d"));
        List<Path> paths = search(start, p -> !p.toFile().getName().endsWith(values.get("e")));
        File target = new File(values.get("o"));
        Zip.packFiles(paths, target);

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
}


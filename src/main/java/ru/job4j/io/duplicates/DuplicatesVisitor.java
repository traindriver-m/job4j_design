package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, HashSet<Path>> allFilePropertyMap = new HashMap<>();
    private final Map<FileProperty, HashSet<Path>> resultMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        HashSet<Path> pathSet = new HashSet<>();
        if (allFilePropertyMap.containsKey(fileProperty)) {
            pathSet = allFilePropertyMap.get(fileProperty);
            pathSet.add(file.toAbsolutePath());
            allFilePropertyMap.put(fileProperty, pathSet);
            resultMap.put(fileProperty, pathSet);
        } else {
            pathSet.add(file.toAbsolutePath());
            allFilePropertyMap.put(fileProperty, pathSet);
        }
        return super.visitFile(file, attrs);
    }

    public void printResult() {
        resultMap.entrySet().stream()
                .flatMap(s -> s.getValue().stream())
                .forEach(System.out::println);
    }
}
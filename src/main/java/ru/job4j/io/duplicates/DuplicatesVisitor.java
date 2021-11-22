package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> filePropertyMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (filePropertyMap.containsKey(fileProperty)) {
            System.out.println(filePropertyMap.get(fileProperty) + "\n" + file.toAbsolutePath());
        }
        filePropertyMap.put(fileProperty, file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}
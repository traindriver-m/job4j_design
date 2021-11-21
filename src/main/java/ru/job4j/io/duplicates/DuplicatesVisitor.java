package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    List<FileProperty> filePropertyList = new LinkedList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (filePropertyList.contains(fileProperty)) {
            System.out.println("This is duplicate: " + fileProperty.getName()
                    + " (size: " + fileProperty.getSize() + ")");
        }
        filePropertyList.add(fileProperty);
        return super.visitFile(file, attrs);
    }
}
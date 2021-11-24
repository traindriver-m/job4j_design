package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        packSingleFile(sources, target);
    }

    public static void packSingleFile(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) { // в его конструктор передается потоk вывода
            for (Path path : sources) {
                ZipEntry zipEntry = new ZipEntry(path.toString()); // в конструктор передается имя архивируемого файла
                zip.putNextEntry(zipEntry);  // добавляет каждый объект ZipEntry в архив
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());  // добавляем содержимое к архиву
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        packSingleFile(
//                new File("./pom.xml"),
//                new File("./pom.zip")
//        );

        ArgsName argsName = new ArgsName();
        argsName.of(args);
    }
}

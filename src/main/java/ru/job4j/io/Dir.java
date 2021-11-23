package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        } else if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters. "
                    + "Set two parameters to run the program.");
        }
        File file = new File(args[0]);
        String extension = args[1];

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            if (subfile.getName().endsWith(extension)) {
                System.out.println(subfile.getName() + " (" + subfile.length() + " bytes).");
            }
        }
    }
}

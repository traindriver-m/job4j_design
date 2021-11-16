package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int[][] multiTable = new int[10][10];
        try (FileOutputStream out = new FileOutputStream("multiTable.txt")) {
            for (int i = 0; i < 10; i++) {
                for (int z = 0; z < 10; z++) {
                    multiTable[i][z] = (i + 1) * (z + 1);
                    out.write((multiTable[i][z] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

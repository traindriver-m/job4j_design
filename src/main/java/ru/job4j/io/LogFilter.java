package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class LogFilter {
    public static List<String> filter(String file) {
        List<String> filterList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] splitLine = line.split(" ");
                if (splitLine[splitLine.length - 2].equals("404")) {
                    filterList.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterList;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}

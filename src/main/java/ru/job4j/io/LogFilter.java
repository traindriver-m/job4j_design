package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LogFilter {
    public static List<String> filter(String file) {
        List<String> filterList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] splitLine = line.split(" ");
                if ("404".equals(splitLine[splitLine.length - 2])) {
                    filterList.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterList;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String string : log) {
                out.printf("%s%n", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}

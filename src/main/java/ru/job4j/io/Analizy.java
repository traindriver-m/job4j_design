package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;

public class Analizy {

    public void unavailable(String source, String target) {
        ArrayList<String> statusList = new ArrayList<>();
        boolean mark = false;
        StringBuilder resultLine = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] statusLine = line.split(" ");
                String status = statusLine[0];
                String date = statusLine[1];
                if (!mark && ("400".equals(status) || "500".equals(status))) {
                    resultLine.append(date);
                    resultLine.append(";");
                    mark = true;
                }
                if (mark && !("400".equals(status) || "500".equals(status))) {
                    resultLine.append(date);
                    resultLine.append(";");
                    mark = false;
                    statusList.add(resultLine.toString());
                    resultLine = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String string : statusList) {
                out.printf("%s%n", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./sourse.log";
        String target = "./target.csv";
        String path1 = "./sourse1.log";
        String target1 = "./target1.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(path, target);
        analizy.unavailable(path1, target1);
    }
}

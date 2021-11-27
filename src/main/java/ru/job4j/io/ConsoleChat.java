package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        ArrayList<String> answers = readPhrases();
        ArrayList<String> logList = new ArrayList<>();
        boolean marker = true;
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!OUT.equals(input)) {
            input = scanner.nextLine();
            logList.add(input);
            if (STOP.equals(input)) {
                marker = false;
            } else if (CONTINUE.equals(input)) {
                marker = true;
            }
            if (marker && !OUT.equals(input)) {
                String answer = answers.get((int) (Math.random() * answers.size()));
                logList.add(answer);
                System.out.println(answer);
            }
        }
        saveLog(logList);
    }

    private ArrayList<String> readPhrases() {
        ArrayList<String> answers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers,
                Charset.forName("WINDOWS-1251")))) {
            bufferedReader.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }


    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\log.txt", ".\\phrases.txt");
        cc.run();
    }
}

package ru.job4j.filesearcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    private static final String NAME = "name";
    private static final String MASK = "mask";
    private static final String REGEX = "regex";

    public static void validationKeys(Map<String, String> values) {
        if (values.size() != 4 || !values.containsKey("d") || !values.containsKey("n")
                || !values.containsKey("t") || !values.containsKey("o")) {
            throw new IllegalArgumentException("Incorrect program launch parameters" + System.lineSeparator()
                    + "Parameters for launching the program:\n"
                    + "-d= directory to start the search.\n"
                    + "-n=file name, mask, regular expression.\n"
                    + "-t=search type(name/mask/regex).\n"
                    + "-o=the name of the file to record the result.");
        }
    }

    public static void filter(Map<String, String> values) {
        checkDirectory(values);
        Path start = Paths.get(values.get("d"));
        String typeSearch = values.get("t");
        String targetSearch = values.get("n");
        List<Path> paths = new ArrayList<>();
        if (NAME.equals(typeSearch)) {
            paths = search(start, p -> p.toFile().getName().equals(targetSearch));
        } else if (MASK.equals(typeSearch)) {
            String maskRegex = targetSearch.replaceAll("\\*", ".*");
            maskRegex = maskRegex.replaceAll("\\?", ".?");
            maskRegex = "^(" + maskRegex + ").*$";
            Pattern pattern = Pattern.compile(maskRegex);
            paths = search(start, p -> pattern.matcher(p.toFile().getName()).matches());
        } else if (REGEX.equals(typeSearch)) {
            Pattern pattern = Pattern.compile(targetSearch);
            paths = search(start, p -> pattern.matcher(p.toFile().getName()).matches());
        }
        outputResult(paths);
    }

    private static void checkDirectory(Map<String, String> values) {
        File file = new File(values.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        Searcher searcher = new Searcher(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private static void outputResult(List<Path> paths) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(ArgsCheck.getValues().get("o"),
                Charset.forName("WINDOWS-1251"), true))) {
            for (Path path : paths) {
                printWriter.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

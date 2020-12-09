package com.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static String[] parseAllLinesAsString(String filename) {
        try {
            return Files
                    .readAllLines(Paths.get("./res/" + filename + ".txt"))
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] parseAllLinesAsInt(String filename) {
        try {
            return Files
                    .readAllLines(Paths.get("./res/" + filename + ".txt"))
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long[] parseAllLinesAsLong(String filename) {
        try {
            return Files
                    .readAllLines(Paths.get("./res/" + filename + ".txt"))
                    .stream()
                    .mapToLong(Long::parseLong)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

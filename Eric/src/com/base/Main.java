package com.base;

import com.days.Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Day6 day6 = new Day6(readAllLines("input_p6"));
        System.out.println("Day6 Puzzle1: " + day6.countForAnyone());
        System.out.println("Day6 Puzzle2: " + day6.countForEveryone());
    }

    public static String[] readAllLines(String filename) {
        try {
            return Files
                    .readAllLines(Paths.get("./res/" + filename + ".txt"))
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] parseEntries(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename))
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.base;

import com.days.Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Day4 day4 = new Day4(readAllLines("input_p4"));
        System.out.println("Day4 puzzle1: " + day4.countValidPassports1());
        System.out.println("Day4 puzzle2: " + day4.countValidPassports2());
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

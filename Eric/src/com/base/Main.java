package com.base;

import com.days.Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Day5 day5 = new Day5(readAllLines("input_p5"));
        System.out.println("Day5 puzzle1: " + day5.getMaxId());
        System.out.println("Day5 puzzle2: " + day5.getMissingSeat());
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

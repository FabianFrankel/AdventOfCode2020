package com.base;

import com.days.Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        var answer1 = Day3.FindTrees1(Day3.parseInput(readAllLines("input_p3")));
        System.out.println("Puzzle1: " + answer1);

        var answer2 = Day3.FindTrees2(Day3.parseInput(readAllLines("input_p3")));
        System.out.println("Puzzle2: " + answer2);
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

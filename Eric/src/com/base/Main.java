package com.base;

import com.puzzles.Puzzle2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        var result1 = Puzzle2.getNumberOfValidPasswords(Puzzle2.parseInput(readAllLines("input_p2")));
        System.out.println("Puzzle 2.1: " + result1);
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

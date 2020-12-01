package com.base;

import com.puzzles.Puzzle1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        int[] entries = parseEntries("./res/input_p1.txt");
        var result = Puzzle1.getProduct(entries);
        System.out.println(result);
    }

    public static int[] parseEntries(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename))
                    .stream()
                    .mapToInt(num -> Integer.parseInt(num))
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

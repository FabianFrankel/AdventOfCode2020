package com.puzzles;

import java.util.Arrays;

public class Puzzle2 {

    public static Password[] parseInput(String[] input) {
        return Arrays
                .stream(input)
                .map(Puzzle2::parsePassword)
                .toArray(Password[]::new);
    }

    public static int getNumberOfValidPasswords(Password[] passwords) {
        return Arrays
                .stream(passwords)
                .filter(Puzzle2::isValidPassword)
                .toArray()
                .length;
    }

    private static boolean isValidPassword(Password pass) {
        int charOcc = getCharOccurrences(pass.password(), pass.rule().ch());
        return (charOcc >= pass.rule().min() && charOcc <= pass.rule().max());
    }

    private static int getCharOccurrences(String input, char ch) {
        return input.chars().filter(c -> c == ch).toArray().length;
    }

    private static Password parsePassword(String x) {
        var rule = new Rule(Integer.parseInt(x.substring(0, x.indexOf('-'))), Integer.parseInt(x.substring(x.indexOf('-') + 1, x.indexOf(' '))), x.charAt(x.indexOf(' ') + 1));
        return new Password(rule, x.substring(x.lastIndexOf(": ") + 2));
    }
}

record Rule(int min, int max, char ch) {
}

record Password(Rule rule, String password) {
}

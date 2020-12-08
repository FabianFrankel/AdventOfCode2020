package com.days;

import java.util.Arrays;

public class Day2 {

    public static Password[] parseInput(String[] input) {
        return Arrays
                .stream(input)
                .map(Day2::parsePassword)
                .toArray(Password[]::new);
    }

    public static int getNumberOfValidPasswords1(Password[] passwords) {
        return Arrays
                .stream(passwords)
                .filter(Day2::hasValidOccurrences)
                .toArray()
                .length;
    }

    public static int getNumberOfValidPasswords2(Password[] passwords) {
        return Arrays
                .stream(passwords)
                .filter(Day2::hasValidPositions)
                .toArray()
                .length;
    }

    private static boolean hasValidOccurrences(Password pass) {
        int charOcc = getCharOccurrences(pass.password(), pass.rule().ch());
        return (charOcc >= pass.rule().min() && charOcc <= pass.rule().max());
    }

    private static boolean hasValidPositions(Password pass) {
        return pass.password().charAt(pass.rule().min() - 1) == pass.rule().ch() ^ pass.password().charAt(pass.rule().max() - 1) == pass.rule().ch();
    }

    private static int getCharOccurrences(String input, char ch) {
        return input.chars().filter(c -> c == ch).toArray().length;
    }

    private static Password parsePassword(String x) {
        var rule = new Rule(Integer.parseInt(x.substring(0, x.indexOf('-'))), Integer.parseInt(x.substring(x.indexOf('-') + 1, x.indexOf(' '))), x.charAt(x.indexOf(' ') + 1));
        return new Password(rule, x.substring(x.lastIndexOf(": ") + 2));
    }

    private record Rule(int min, int max, char ch) {
    }

    private record Password(Rule rule, String password) {
    }
}



package com.base;

import com.days.Day9;

public class Main {

    public static void main(String[] args) {
        Day9 day9 = new Day9(Parser.parseAllLinesAsLong("input_p9"));
        var answer1 = day9.getFirstFailingNumber(25);
        System.out.println("Day9 Puzzle1: " + answer1);
        System.out.println("Da9 Puzzle2: " + day9.getEncryptionWeakness(answer1));
    }
}

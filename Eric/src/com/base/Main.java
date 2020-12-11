package com.base;

import com.days.Day10;

public class Main {

    public static void main(String[] args) {
        Day10 day10 = new Day10(Parser.parseAllLinesAsInt("input_p10"));
        System.out.println("Day10 Puzzle1:" + day10.getJoltDifferences());
        System.out.println("Day10 Puzzle2:" + day10.getArrangements());
    }
}

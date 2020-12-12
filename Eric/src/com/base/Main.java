package com.base;

import com.days.Day12;

public class Main {

    public static void main(String[] args) {

        Day12 day12 = new Day12(Parser.parseAllLinesAsString("input_p12"));
        System.out.println(day12.puzzle1());
        System.out.println(day12.puzzle2());
    }
}

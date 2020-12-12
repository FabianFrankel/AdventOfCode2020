package com.base;

import com.days.Day11;

public class Main {

    public static void main(String[] args) {

        Day11 day11 = new Day11(Parser.parseAllLinesAsString("input_p11"));
        System.out.println(day11.puzzle1());
        System.out.println(day11.puzzle2());
    }
}

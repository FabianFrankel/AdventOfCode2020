package com.days;

import java.util.Arrays;

public class Day3 {

    public static char[][] parseInput(String[] plane) {
        int rows = plane.length, cols = plane[0].length();
        var arr = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = plane[i].charAt(j);
            }
        }
        return arr;
    }

    public static int FindTrees1(char[][] plane) {
        return getTreeCountOnTraversal(plane, 1, 3);
    }

    public static int FindTrees2(char[][] plane) {
        var strategies = new int[]{
                getTreeCountOnTraversal(plane, 1, 1),
                getTreeCountOnTraversal(plane, 1, 3),
                getTreeCountOnTraversal(plane, 1, 5),
                getTreeCountOnTraversal(plane, 1, 7),
                getTreeCountOnTraversal(plane, 2, 1)
        };

        return Arrays
                .stream(strategies)
                .reduce(1, (a, b) -> a * b);
    }

    private static int getTreeCountOnTraversal(char[][] plane, int yDiff, int xDiff) {
        int y = 0, x = 0, trees = 0;

        while (y < plane.length) {
            if (plane[y][x % plane[0].length] == '#') trees++;

            y += yDiff;
            x += xDiff;
        }
        return trees;
    }
}

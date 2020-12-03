package com.days;

import java.util.Arrays;

public class Day3 {
    private char[][] plane;

    public Day3(String plane[]) {
        this.plane = parseInput(plane);
    }

    private char[][] parseInput(String[] plane) {
        int rows = plane.length, cols = plane[0].length();
        var arr = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = plane[i].charAt(j);
            }
        }
        return arr;
    }

    public int findTrees1() {
        return getTreeCountOnTraversal(1, 3);
    }

    public int findTrees2() {
        var strategies = new int[]{
                getTreeCountOnTraversal(1, 1),
                getTreeCountOnTraversal(1, 3),
                getTreeCountOnTraversal(1, 5),
                getTreeCountOnTraversal(1, 7),
                getTreeCountOnTraversal(2, 1)
        };

        return Arrays
                .stream(strategies)
                .reduce(1, (a, b) -> a * b);
    }

    private int getTreeCountOnTraversal(int yDiff, int xDiff) {
        int trees = 0;
        
        for (int y = 0, x = 0; y < plane.length; y += yDiff, x += xDiff)
            if (plane[y][x % plane[0].length] == '#') trees++;

        return trees;
    }
}

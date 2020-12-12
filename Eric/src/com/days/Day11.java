package com.days;

import java.util.Arrays;

public class Day11 {
    private final String[] input;

    public Day11(String[] input) {
        this.input = input;
    }

    public int puzzle1() {
        var map = recurse(new Map(input), 4);
        return map.occupiedCount();
    }

    public int puzzle2() {
        var map = recurse2(new Map(input), 5);
        return map.occupiedCount();
    }

    private Map recurse2(Map map, int maxOccupied) {
        var cMap = (Map) map.clone();
        var hasChanged = false;

        for (int y = 0; y < map.height(); y++) {
            for (int x = 0; x < map.width(); x++) {
                var seat = applyRules(map.get(y, x), map.directionalVicinityOf(y, x), maxOccupied);
                if (!hasChanged && seat != map.get(y, x))
                    hasChanged = true;

                cMap.set(y, x, seat);
            }
        }
        if (hasChanged)
            cMap = recurse2(cMap, maxOccupied);

        return cMap;
    }

    private Map recurse(Map map, int maxOccupied) {
        var cMap = (Map) map.clone();
        var hasChanged = false;

        for (int y = 0; y < map.height(); y++) {
            for (int x = 0; x < map.width(); x++) {
                var seat = applyRules(map.get(y, x), map.vicinityOf(y, x), maxOccupied);
                if (!hasChanged && seat != map.get(y, x))
                    hasChanged = true;

                cMap.set(y, x, seat);
            }
        }
        if (hasChanged) {
            cMap = recurse(cMap, maxOccupied);
        }
        return cMap;
    }

    private char applyRules(char seat, char[] vicinity, int maxOccupied) {
        var occupiedCount = (int) new String(vicinity).chars().mapToObj(c -> (char) c).filter(co -> co == '#').count();
        switch (seat) {
            case 'L' -> seat = occupiedCount == 0 ? '#' : seat;
            case '#' -> seat = occupiedCount >= maxOccupied ? 'L' : seat;
        }
        return seat;
    }
}

class Map implements Cloneable {
    private final char[] map;
    private final int width;
    private final int height;

    Map(String[] input) {
        width = input[0].length();
        height = input.length;
        map = new char[width * height];

        for (int y = 0; y < input.length; y++)
            for (int x = 0; x < width; x++)
                map[y * width + x] = input[y].charAt(x);
    }

    private Map(char[] map, int width, int height) {
        this.map = map;
        this.width = width;
        this.height = height;
    }

    char get(int y, int x) {
        return map[y * width + x];
    }

    void set(int y, int x, char val) {
        map[y * width + x] = val;
    }

    int height() {
        return height;
    }

    int width() {
        return width;
    }

    int length() {
        return map.length;
    }

    boolean isInBounds(int y, int x) {
        return y >= 0 && x >= 0 && x < width && y < height;
    }

    char[] vicinityOf(int y, int x) {
        StringBuilder str = new StringBuilder();
        for (int yOff = y - 1; yOff <= y + 1; yOff++)
            for (int xOff = x - 1; xOff <= x + 1; xOff++) {
                if (isInBounds(yOff, xOff) && !(xOff == x && yOff == y)) {
                    str.append(get(yOff, xOff));
                }
            }
        return str.toString().toCharArray();
    }

    char[] directionalVicinityOf(int y, int x) { // go until find 'L' or '#'
        StringBuilder str = new StringBuilder();
        for (int yOff = y - 1; yOff <= y + 1; yOff++)
            for (int xOff = x - 1; xOff <= x + 1; xOff++) {
                if (isInBounds(yOff, xOff) && !(xOff == x && yOff == y)) {
                    str.append(findSeatInDirection(y, x, yOff, xOff));
                }
            }
        return str.toString().toCharArray();
    }

    private char findSeatInDirection(int y, int x, int yOff, int xOff) {
        int yDir = yOff - y, xDir = xOff - x;
        while (isInBounds(yOff, xOff)) {
            var seat = get(yOff, xOff);
            if (seat != '.')
                return seat;

            yOff += yDir;
            xOff += xDir;
        }
        return 'X';
    }

    int occupiedCount() {
        return (int) new String(map).chars().mapToObj(c -> (char) c).filter(co -> co == '#').count();
    }

    @Override
    public Object clone() {
        return new Map(Arrays.copyOf(map, length()), this.width, this.height);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            if (i != 0 && i % width == 0) str.append("\n");
            str.append(map[i]);

        }
        return str.toString();
    }
}
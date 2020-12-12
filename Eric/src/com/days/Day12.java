package com.days;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day12 {
    private final String[] input;
    private final Instruction[] instructions;

    public Day12(String[] input) {
        this.input = input;
        instructions = generateInstructions();
    }

    private Instruction[] generateInstructions() {
        return Arrays
                .stream(input)
                .map(x -> new Instruction(x.substring(0, 1), Integer.parseInt(x.substring(1))))
                .toArray(Instruction[]::new);
    }

    public int puzzle1() {
        Boat boat = new Boat();
        for (Instruction i : instructions) {
            boat.move(i.direction(), i.val());
        }
        return boat.getManhattanDistance();
    }

    public int puzzle2() {
        Boat boat = new Boat();
        for (Instruction i : instructions) {
            boat.moveToWaypoint(i.direction(), i.val());
        }
        return boat.getManhattanDistance();
    }
}

class Boat {
    Compass dir;
    int degrees;
    final Waypoint waypoint;
    final HashMap<Compass, Integer> foo;

    Boat() {
        degrees = 90;
        dir = getDirFromDegrees(degrees);
        waypoint = new Waypoint();
        foo = new HashMap<>() {{
            put(Compass.N, 0);
            put(Compass.E, 0);
            put(Compass.S, 0);
            put(Compass.W, 0);
        }};

    }

    int getManhattanDistance() {
        return Math.abs(foo.get(Compass.N) - foo.get(Compass.S)) + Math.abs(foo.get(Compass.W) - foo.get(Compass.E));
    }

    void moveToWaypoint(String dir, int val) {
        switch (dir) {
            case "F" -> {
                var n = waypoint.getNorth();
                var e = waypoint.getEast();
                foo.computeIfPresent(e.getKey(), ((k, v) -> v += val * e.getValue()));
                foo.computeIfPresent(n.getKey(), ((k, v) -> v += val * n.getValue()));
            }
            case "E", "W", "N", "S" -> waypoint.move(Compass.valueOf(dir), val);
            case "L" -> waypoint.turn(-val);
            case "R" -> waypoint.turn(val);
        }
    }

    void move(String dir, int val) {
        switch (dir) {
            case "F" -> foo.computeIfPresent(this.dir, ((k, v) -> v += val));
            case "E", "W", "N", "S" -> foo.computeIfPresent(Compass.valueOf(dir), (k, v) -> v += val);
            case "L" -> turn(-val);
            case "R" -> turn(val);
        }
    }

    void turn(int val) {
        int degrees = Math.floorMod(this.degrees + val, 360);
        dir = getDirFromDegrees(degrees);
        this.degrees = degrees;
    }

    Compass getDirFromDegrees(int degrees) {
        Compass dir;
        switch (degrees) {
            case 0 -> dir = Compass.N;
            case 180 -> dir = Compass.S;
            case 270 -> dir = Compass.W;
            default -> dir = Compass.E;
        }
        return dir;
    }
}

class Waypoint {
    HashMap<Compass, Integer> bar = new HashMap<>() {{
        put(Compass.N, 1);
        put(Compass.E, 10);
    }};

    Map.Entry<Compass, Integer> getNorth() {
        var val = bar.get(Compass.N);
        return val < 0 ? new AbstractMap.SimpleEntry(Compass.S, Math.abs(val)) : new AbstractMap.SimpleEntry(Compass.N, Math.abs(val));
    }

    Map.Entry<Compass, Integer> getEast() {
        var val = bar.get(Compass.E);
        return val < 0 ? new AbstractMap.SimpleEntry(Compass.W, Math.abs(val)) : new AbstractMap.SimpleEntry(Compass.E, Math.abs(val));
    }

    void move(Compass key, int val) {
        switch (key) {
            case N -> bar.computeIfPresent(Compass.N, ((k, v) -> v += val));
            case S -> bar.computeIfPresent(Compass.N, ((k, v) -> v -= val));
            case E -> bar.computeIfPresent(Compass.E, ((k, v) -> v += val));
            case W -> bar.computeIfPresent(Compass.E, ((k, v) -> v -= val));
        }
    }

    void turn(int degrees) {
        degrees = Math.floorMod(degrees, 360);
        var n = bar.get(Compass.N);
        var e = bar.get(Compass.E);
        switch (degrees) {
            case 90 -> {
                bar.computeIfPresent(Compass.N, ((k, v) -> v = -e));
                bar.computeIfPresent(Compass.E, ((k, v) -> v = n));
            }
            case 180 -> {
                bar.computeIfPresent(Compass.N, ((k, v) -> v = -n));
                bar.computeIfPresent(Compass.E, ((k, v) -> v = -e));
            }
            case 270 -> {
                bar.computeIfPresent(Compass.N, ((k, v) -> v = e));
                bar.computeIfPresent(Compass.E, ((k, v) -> v = -n));
            }

        }
    }
}

enum Compass {
    N,
    E,
    S,
    W
}

record Instruction(String direction, int val) {
}


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
    String dir;
    int degrees;
    final Waypoint waypoint;
    final HashMap<String, Integer> pos;

    Boat() {
        degrees = 90;
        dir = getDirFromDegrees(degrees);
        waypoint = new Waypoint();
        pos = new HashMap<>() {{
            put("N", 0);
            put("E", 0);
            put("S", 0);
            put("W", 0);
        }};

    }

    int getManhattanDistance() {
        return Math.abs(pos.get("N") - pos.get("S")) + Math.abs(pos.get("W") - pos.get("E"));
    }

    void moveToWaypoint(String dir, int val) {
        switch (dir) {
            case "F" -> {
                var nPos = waypoint.getNorth();
                var ePos = waypoint.getEast();
                pos.computeIfPresent(ePos.getKey(), ((k, v) -> v += val * ePos.getValue()));
                pos.computeIfPresent(nPos.getKey(), ((k, v) -> v += val * nPos.getValue()));
            }
            case "E", "W", "N", "S" -> waypoint.move(dir, val);
            case "L" -> waypoint.turn(-val);
            case "R" -> waypoint.turn(val);
        }
    }

    void move(String dir, int val) {
        switch (dir) {
            case "F" -> pos.computeIfPresent(this.dir, ((k, v) -> v += val));
            case "E", "W", "N", "S" -> pos.computeIfPresent(dir, (k, v) -> v += val);
            case "L" -> turn(-val);
            case "R" -> turn(val);
        }
    }

    void turn(int val) {
        int degrees = Math.floorMod(this.degrees + val, 360);
        dir = getDirFromDegrees(degrees);
        this.degrees = degrees;
    }

    String getDirFromDegrees(int degrees) {
        String dir;
        switch (degrees) {
            case 0 -> dir = "N";
            case 180 -> dir = "S";
            case 270 -> dir = "W";
            default -> dir = "E";
        }
        return dir;
    }
}

class Waypoint {
    HashMap<String, Integer> pos = new HashMap<>() {{
        put("N", 1);
        put("E", 10);
    }};

    Map.Entry<String, Integer> getNorth() {
        var val = pos.get("N");
        return val < 0 ?
                new AbstractMap.SimpleEntry<>("S", Math.abs(val)) :
                new AbstractMap.SimpleEntry<>("N", Math.abs(val));
    }

    Map.Entry<String, Integer> getEast() {
        var val = pos.get("E");
        return val < 0 ?
                new AbstractMap.SimpleEntry<>("W", Math.abs(val)) :
                new AbstractMap.SimpleEntry<>("E", Math.abs(val));
    }

    void move(String key, int val) {
        switch (key) {
            case "N" -> pos.computeIfPresent("N", ((k, v) -> v += val));
            case "S" -> pos.computeIfPresent("N", ((k, v) -> v -= val));
            case "E" -> pos.computeIfPresent("E", ((k, v) -> v += val));
            case "W" -> pos.computeIfPresent("E", ((k, v) -> v -= val));
        }
    }

    void turn(int degrees) {
        var nPos = pos.get("N");
        var ePos = pos.get("E");
        switch (Math.floorMod(degrees, 360)) {
            case 90 -> {
                pos.computeIfPresent("N", ((k, v) -> v = -ePos));
                pos.computeIfPresent("E", ((k, v) -> v = nPos));
            }
            case 180 -> {
                pos.computeIfPresent("N", ((k, v) -> v = -nPos));
                pos.computeIfPresent("E", ((k, v) -> v = -ePos));
            }
            case 270 -> {
                pos.computeIfPresent("N", ((k, v) -> v = ePos));
                pos.computeIfPresent("E", ((k, v) -> v = -nPos));
            }

        }
    }
}

record Instruction(String direction, int val) {
}


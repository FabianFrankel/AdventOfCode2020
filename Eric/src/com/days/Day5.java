package com.days;

import java.util.Arrays;

public record Day5(String[] boardingPasses) {

    public int getMaxId() {
        return Arrays.stream(boardingPasses)
                .map(this::getId)
                .max(Integer::compare)
                .get();
    }

    public int getMissingSeat() {
        var sortedIds = Arrays.stream(boardingPasses).map(this::getId).sorted().mapToInt(i -> i).toArray();
        for (int i = 1; i < sortedIds.length - 1; i++)
            if (sortedIds[i + 1] != sortedIds[i] + 1) return sortedIds[i] + 1;

        return -1;
    }

    private int getId(String boardingPass) {
        int row = parseBSP(boardingPass.substring(0, 7), 127);
        int col = parseBSP(boardingPass.substring(7), 7);
        return (row * 8) + col;
    }

    private int parseBSP(String input, int bound) {
        int mini = 0, maxi = bound;
        for (char c : input.toCharArray()) {
            int length = (maxi - mini);
            switch (c) {
                case 'L', 'F' -> maxi = (int) Math.floor(maxi - (length / 2D));
                case 'R', 'B' -> mini = (int) Math.ceil(mini + (length / 2D));
            }
        }
        return "L,F".indexOf(input.charAt(input.length() - 1)) != -1 ? mini : maxi;
    }
}
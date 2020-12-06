package com.days;

public class Day6 {
    private final String[] entries;

    public Day6(String[] entries) {
        this.entries = entries;
    }

    public int countForAnyone() {
        return countAnswers(this::countUniqueOccurrences);
    }

    public int countForEveryone() {
        return countAnswers(this::countRecurringOccurrences);
    }

    private int countAnswers(ICounter counter) {
        var count = 0;
        var group = new StringBuilder();
        for (var line : entries) {
            if (line.isBlank()) {
                count += counter.count(group.toString());
                group = new StringBuilder();
            } else
                group.append(" ").append(line);
        }
        return count;
    }

    private int countUniqueOccurrences(String input) {
        return (int) input.replace(" ", "").chars().distinct().count();
    }

    private int countRecurringOccurrences(String input) {
        var arr = input.trim().split(" ");

        if (arr.length == 1) return arr[0].length(); // edge case of single length input

        int matches = 0;
        for (var ch : arr[0].toCharArray()) {
            boolean isFound = true;
            for (int i = 1; i < arr.length && isFound; i++)
                isFound = arr[i].indexOf(ch) != -1;

            matches += isFound ? 1 : 0;
        }

        return matches;
    }
}

interface ICounter {
    int count(String input);
}

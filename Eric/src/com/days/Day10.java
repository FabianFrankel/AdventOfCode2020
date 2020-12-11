package com.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    private final int[] adapters;

    public Day10(int[] adapters) {
        this.adapters = adapters;
    }

    public int getJoltDifferences() {
        var sortedAdapters = sortCopy(adapters);
        int ones = sortedAdapters[0] == 1 ? 1 : 0, threes = sortedAdapters[0] == 3 ? 2 : 1;

        for (int i = 0, j = i + 1; j < sortedAdapters.length; i++, j++) {
            int diff = sortedAdapters[j] - sortedAdapters[i];
            switch (diff) {
                case 1 -> ones++;
                case 3 -> threes++;
            }
        }
        return ones * threes;
    }

    public long getArrangements() {
        var adaptersList = toList(sortCopy(adapters));
        adaptersList.add(0, 0); // charging outlet
        adaptersList.add(adaptersList.get(adaptersList.size() - 1) + 3); // built-in adapter
        var arrangementCounts = new HashMap<Integer, Long>() {{
            put(adaptersList.size() - 1, 0L);
            put(adaptersList.size() - 2, 1L);
        }};

        findArrangement(0, adaptersList, arrangementCounts);
        return arrangementCounts.get(0);
    }

    private long findArrangement(int start, List<Integer> adaptersList, HashMap<Integer, Long> arrangementCounts) {
        if (arrangementCounts.containsKey(start)) return arrangementCounts.get(start);

        var arrangementCount = 0L;
        for (int i = 1; i <= 3; i++)
            if ((start + i < adaptersList.size()) && (adaptersList.get(start + i) - adaptersList.get(start) <= 3))
                arrangementCount += findArrangement(start + i, adaptersList, arrangementCounts);

        arrangementCounts.put(start, arrangementCount);
        return arrangementCount;
    }

    private int[] sortCopy(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        return sorted;
    }

    private List<Integer> toList(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}

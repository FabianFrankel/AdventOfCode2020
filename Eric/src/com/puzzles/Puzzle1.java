package com.puzzles;

import java.util.Arrays;

public class Puzzle1 {
    public static int getProduct(int[] entries) {
        Arrays.sort(entries);

        for (int i = 0, j = entries.length - 1; i < j; ) {
            int sum = entries[i] + entries[j];
            if (sum == 2020)
                return entries[i] * entries[j];

            if (sum < 2020) i++;
            else j--;
        }
        return -1;
    }
}

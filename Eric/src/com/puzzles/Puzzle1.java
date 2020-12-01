package com.puzzles;

import java.util.Arrays;
import java.util.HashSet;

public class Puzzle1 {
    public static int getTwoProduct(int[] entries, int target) {
        Arrays.sort(entries);

        for (int i = 0, j = entries.length - 1; i < j; ) {
            var sum = entries[i] + entries[j];
            if (sum == target)
                return entries[i] * entries[j];

            if (sum < target) i++;
            else j--;
        }
        return -1;
    }

    public static int getThreeProduct(int[] entries, int target) {
        for (int i = 0; i < entries.length - 2; i++) {
            var compareSet = new HashSet<Integer>();

            var targetSum = target - entries[i]; // 2020 - [i] == [j1,j2]
            for (int j = i + 1; j < entries.length - 1; j++) {
                var thirdNum = targetSum - entries[j];
                if (compareSet.contains(thirdNum))
                    return entries[i] * entries[j] * thirdNum;
                else
                    compareSet.add(entries[j]);
            }
        }
        return -1;
    }
}

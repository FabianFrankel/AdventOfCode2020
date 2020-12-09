package com.days;

import java.util.Arrays;

public class Day9 {
    private long[] list;

    public Day9(long[] list) {
        this.list = list;
    }

    public long getFirstFailingNumber(int preambleLength) {
        for (int i = preambleLength; i < list.length; i++) {
            var preamble = Arrays.copyOfRange(list, i - preambleLength, i);
            if (!hasSum(list[i], preamble)) return list[i];
        }
        return -1;
    }

    public long getEncryptionWeakness(long target) {
        long sum = 0;
        for (int i = 0, j = i + 1; i < list.length; ) {
            long chaser = list[i], runner = list[j];

            sum += sum == 0 ? chaser + runner : runner;

            if (sum == target) {
                var seen = Arrays.copyOfRange(list, i, j);
                return Arrays.stream(seen).max().getAsLong() + Arrays.stream(seen).min().getAsLong();
            } else if (sum < target) { // traverse runner
                j++;
            } else { // traverse chaser and reset runner and sum
                i++;
                j = i + 1;
                sum = 0;
            }
        }
        return -1;
    }

    private boolean hasSum(long target, long[] preamble) {
        Arrays.sort(preamble);

        for (int i = 0, j = preamble.length - 1; i < j; ) {
            var sum = preamble[i] + preamble[j];
            if (sum == target)
                return true;

            if (sum < target)
                i++;
            else
                j--;
        }
        return false;
    }

}

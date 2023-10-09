package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {
    }

    public static boolean isNested(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            return false;
        }
        if (a2.length == 0) {
            return false;
        }
        if (a1.length == 0) {
            return true;
        }
        return minOfArray(a1) > minOfArray(a2)
            && maxOfArray(a1) < maxOfArray(a2);
    }

    private static int minOfArray(int[] a) {
        return Arrays.stream(a).min().getAsInt();
    }

    private static int maxOfArray(int[] a) {
        return Arrays.stream(a).max().getAsInt();
    }
}

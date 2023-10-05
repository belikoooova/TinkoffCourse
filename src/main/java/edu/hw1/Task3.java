package edu.hw1;

import java.util.Arrays;

public class Task3 {
    @SuppressWarnings("MagicNumber")
    public static boolean isNested(int[] a1, int[] a2) {
        if (a2.length == 0) {
            return false;
        }
        if (a1.length == 0) {
            return true;
        }
        return Arrays.stream(a1).min().getAsInt() > Arrays.stream(a2).min().getAsInt() &&
            Arrays.stream(a1).max().getAsInt() < Arrays.stream(a2).max().getAsInt();
    }
}

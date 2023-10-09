package edu.hw1;

public class Task2 {
    private Task2() {
    }

    private static final int ONE = 1;
    private static final int BASE = 10;

    public static int countDigit(long number) {
        if (number == 0) {
            return ONE;
        }
        long posNumber = Math.abs(number);
        int count = 0;
        while (posNumber > 0) {
            ++count;
            posNumber /= BASE;
        }
        return count;
    }
}

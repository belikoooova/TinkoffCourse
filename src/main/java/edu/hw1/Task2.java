package edu.hw1;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigit(long number) {
        if (number == 0) {
            return 1;
        }
        long posNumber = Math.abs(number);
        int count = 0;
        while (posNumber > 0) {
            ++count;
            posNumber /= 10;
        }
        return count;
    }
}

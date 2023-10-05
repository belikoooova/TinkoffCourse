package edu.hw1;

public class Task2 {
    @SuppressWarnings("MagicNumber")
    public static int countDigit(long number) {
        if (number == 0) {
            return 1;
        }
        number = Math.abs(number);
        int count = 0;
        while (number > 0) {
            ++count;
            number /= 10;
        }
        return count;
    }
}

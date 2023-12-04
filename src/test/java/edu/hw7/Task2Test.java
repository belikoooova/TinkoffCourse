package edu.hw7;

import edu.hw7.task2.ParallelFactorizer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    private static final int[] NUMBERS = new int[] {1, 9, 27, 81};

    @Test
    void testMultipleThreadsFactorizing() {
        // given and when and then
        for (var number : NUMBERS) {
            assertEquals(singleThreadCount(number), ParallelFactorizer.count(number));
        }
    }

    private static long singleThreadCount(int number) {
        long result = 1;
        for (int i = 1; i <= number; ++i) {
            result *= i;
        }
        return result;
    }
}

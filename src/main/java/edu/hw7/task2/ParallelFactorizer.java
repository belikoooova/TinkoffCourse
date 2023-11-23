package edu.hw7.task2;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParallelFactorizer {
    public static long count(int number) {
        List<Long> multipliers = new ArrayList<>();
        for (int i = 1; i <= number; ++i) {
            multipliers.add((long) i);
        }

        return multipliers.parallelStream().reduce(1L, (a, b) -> (a * b));
    }
}

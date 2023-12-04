package edu.hw7.task2;

import java.util.stream.LongStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParallelFactorizer {
    public static long count(int number) {
        return LongStream.rangeClosed(1, number).parallel().reduce(1L, (a, b) -> (a * b));
    }
}

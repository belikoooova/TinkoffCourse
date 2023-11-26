package edu.hw7;

import edu.hw7.task4.MultiThreadPiCounter;
import edu.hw7.task4.PiCounter;
import edu.hw7.task4.PiUtils;
import edu.hw7.task4.SingleThreadPiCounter;
import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    private static final long SMALL = 10_000_000;
    private static final long MIDDLE = 100_000_000;
    private static final long BIG = 1_000_000_000;
    private static final int NUMBER_OF_THREADS = 4;

    @Test
    void testInaccuracy() {
        // given
        PiCounter counter = new SingleThreadPiCounter();

        // when
        var small = counter.getPiValue(SMALL);
        var middle = counter.getPiValue(MIDDLE);
        var big = counter.getPiValue(BIG);

        // then
        assertTrue(PiUtils.countInaccuracy(small) > PiUtils.countInaccuracy(middle));
        assertTrue(PiUtils.countInaccuracy(middle) > PiUtils.countInaccuracy(big));
        System.out.println(PiUtils.countInaccuracy(small));
        System.out.println(PiUtils.countInaccuracy(middle));
        System.out.println(PiUtils.countInaccuracy(big));
    }

    @SneakyThrows
    @Test
    void calculateTimeDifferenceSmall() {
        // given and when
        var singleThreadTime = PiUtils.getTimeOfCalculateInSingleThread(BIG);
        var multiThreadTime = PiUtils.getTimeOfCalculateInMultiThread(BIG, NUMBER_OF_THREADS);
        // then
        System.out.println(singleThreadTime);
        System.out.println(multiThreadTime);
        assertTrue(singleThreadTime > multiThreadTime);
    }
}

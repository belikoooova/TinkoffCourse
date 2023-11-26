package edu.hw7;

import edu.hw7.task4.PiCounter;
import edu.hw7.task4.PiUtils;
import edu.hw7.task4.SingleThreadPiCounter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    private static final long SMALL = 100_000;
    private static final long MIDDLE = 1_000_000;
    private static final long BIG = 10_000_000;
    private static final int NUMBER_OF_THREADS = 4;

    @Test
    void testInaccuracySingleThread() {
        // given
        PiCounter counter = new SingleThreadPiCounter();

        // when
        var small = counter.getPiValue(SMALL);
        var middle = counter.getPiValue(MIDDLE);
        var big = counter.getPiValue(BIG);

        // then
        assertTrue(PiUtils.countInaccuracy(small) > PiUtils.countInaccuracy(middle));
        assertTrue(PiUtils.countInaccuracy(middle) > PiUtils.countInaccuracy(big));
    }

    @SneakyThrows
    @Test
    void testInaccuracyMultiThread() {
        // given and when
        var small = PiUtils.getPiInMultiThread(SMALL, NUMBER_OF_THREADS);
        var middle = PiUtils.getPiInMultiThread(MIDDLE, NUMBER_OF_THREADS);
        var big = PiUtils.getPiInMultiThread(BIG, NUMBER_OF_THREADS);

        // then
        assertTrue(PiUtils.countInaccuracy(small) > PiUtils.countInaccuracy(big));
    }
}

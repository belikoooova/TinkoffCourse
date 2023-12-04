package edu.hw7;

import edu.hw7.task1.AtomicCounter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    private static final int AMOUNT_OF_THREADS = 10;
    private static final int AMOUNT_OF_INCREMENTS_PER_THREAD = 1000;

    @SneakyThrows
    @Test
    void testMultipleThreadsIncrementing() {
        // given
        AtomicCounter atomicCounter = new AtomicCounter();
        Thread[] threads = getThreadsArray(atomicCounter);

        // when
        for (var thread : threads) {
            thread.start();
        }

        for (var thread : threads) {
            thread.join();
        }

        // then
        assertEquals(AMOUNT_OF_THREADS * AMOUNT_OF_INCREMENTS_PER_THREAD, atomicCounter.get());
    }

    private static Thread[] getThreadsArray(AtomicCounter atomicCounter) {
        Thread[] threads = new Thread[AMOUNT_OF_THREADS];

        for (int i = 0; i < AMOUNT_OF_THREADS; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < AMOUNT_OF_INCREMENTS_PER_THREAD; ++j) {
                    atomicCounter.increment();
                }
            });
        }
        return threads;
    }
}

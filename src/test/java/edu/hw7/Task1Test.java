package edu.hw7;

import edu.hw7.task1.Value;
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
        Value value = new Value();
        Thread[] threads = getThreadsArray(value);

        // when
        for (var thread : threads) {
            thread.start();
        }

        for (var thread : threads) {
            thread.join();
        }

        // then
        assertEquals(AMOUNT_OF_THREADS * AMOUNT_OF_INCREMENTS_PER_THREAD, value.get());
    }

    private static Thread[] getThreadsArray(Value value) {
        Thread[] threads = new Thread[AMOUNT_OF_THREADS];

        for (int i = 0; i < AMOUNT_OF_THREADS; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < AMOUNT_OF_INCREMENTS_PER_THREAD; ++j) {
                    value.increment();
                }
            });
        }
        return threads;
    }
}

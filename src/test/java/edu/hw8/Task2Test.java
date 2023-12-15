package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    private static final int THREADS_AMOUNT = 5;
    private static final List<Integer> FIB_NUMBERS = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

    @SneakyThrows
    @Test
    void testCountingFib() {
        // given
        ThreadPool pool = FixedThreadPool.create(THREADS_AMOUNT);
        List<Integer> counted =
            Collections.synchronizedList(new ArrayList<>(Collections.nCopies(FIB_NUMBERS.size(), 0)));
        CountDownLatch latch = new CountDownLatch(FIB_NUMBERS.size());

        // when
        pool.start();
        for (int i = 1; i <= FIB_NUMBERS.size(); ++i) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    counted.set(finalI - 1, countFib(finalI));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        pool.close();

        // then
        for (int i = 0; i < FIB_NUMBERS.size(); ++i) {
            assertEquals(FIB_NUMBERS.get(i), counted.get(i));
        }
    }

    private static int countFib(int n) {
        int a = 0;
        int b = 1;
        if (n == 1) {
            return a;
        }
        if (n == 2) {
            return b;
        }
        for (int i = 3; i <= n; ++i) {
            int temp = b;
            b += a;
            a = temp;
        }
        return b;
    }
}

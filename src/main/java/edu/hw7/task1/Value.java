package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Value {
    private final AtomicInteger value = new AtomicInteger(0);

    public int get() {
        return value.get();
    }

    public void increment() {
        value.getAndIncrement();
    }
}

package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    private FixedThreadPool(int threadLimit) {
        threads = new Thread[threadLimit];
        for (int i = 0; i < threadLimit; ++i) {
            threads[i] = new Worker();
        }
        tasks = new LinkedBlockingDeque<>();
    }

    public static FixedThreadPool create(int threadLimit) {
        return new FixedThreadPool(threadLimit);
    }

    @Override
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            for (var thread : threads) {
                thread.start();
            }
        }
    }

    @SneakyThrows
    @Override
    public void execute(Runnable runnable) {
        if (isRunning.get()) {
            tasks.offer(runnable);
        }
    }

    @Override
    public void close() throws Exception {
        isRunning.set(false);
    }

    private class Worker extends Thread {
        @SneakyThrows
        public void run() {
            while (isRunning.get()) {
                Runnable task = tasks.take();
                task.run();
            }
        }
    }
}

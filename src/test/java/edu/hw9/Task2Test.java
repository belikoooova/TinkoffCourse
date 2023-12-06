package edu.hw9;

import edu.hw9.task2.filters.ExtensionFilter;
import edu.hw9.task2.filters.SizeFilter;
import edu.hw9.task2.tasks.CountingRecursiveTask;
import edu.hw9.task2.tasks.FilteringRecursiveTask;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {
    private static final int FILE_SIZE = 100;
    private static final int SMALL_FILES_AMOUNT = 500;
    private static final int BIG_FILES_AMOUNT = 1000;

    @TempDir
    Path tempDir;
    private ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    @AfterEach
    void cleanup() {
        CountingRecursiveTask.getNeededDirectories().clear();
    }

    @SneakyThrows
    @Test
    void testCountingRecursiveTaskWithFewFiles() {
        // given
        for (int i = 0; i < SMALL_FILES_AMOUNT; i++) {
            Files.createFile(tempDir.resolve("file" + i + ".txt"));
        }

        // when
        CountingRecursiveTask task = new CountingRecursiveTask(tempDir);

        // then
        assertEquals(SMALL_FILES_AMOUNT, forkJoinPool.invoke(task).intValue());
        assertTrue(CountingRecursiveTask.getNeededDirectories().isEmpty());
    }

    @SneakyThrows
    @Test
    void testCountingRecursiveTaskWithNeededFilesAmount() {
        // given
        for (int i = 0; i < BIG_FILES_AMOUNT; i++) {
            Files.createFile(tempDir.resolve("file" + i + ".txt"));
        }

        // when
        CountingRecursiveTask task = new CountingRecursiveTask(tempDir);

        // then
        assertEquals(1000, forkJoinPool.invoke(task).intValue());
        assertTrue(CountingRecursiveTask.getNeededDirectories().contains(tempDir));
    }

    @SneakyThrows
    @Test
    void testCountingRecursiveTaskInNestedDirectories() {
        // given
        Path nestedDir = Files.createDirectories(tempDir.resolve("nested"));
        for (int i = 0; i < BIG_FILES_AMOUNT; i++) {
            Files.createFile(nestedDir.resolve("file" + i + ".txt"));
        }

        // when
        CountingRecursiveTask task = new CountingRecursiveTask(tempDir);
        forkJoinPool.invoke(task);

        // then
        assertTrue(CountingRecursiveTask.getNeededDirectories().contains(nestedDir));
    }

    @SneakyThrows
    @Test
    void testFilteringRecursiveTaskWithSizeFilter() {
        // given
        Path file = Files.createFile(tempDir.resolve("testfile.txt"));
        Files.write(file, new byte[FILE_SIZE]);
        SizeFilter filter = new SizeFilter(file, FILE_SIZE);
        FilteringRecursiveTask task = new FilteringRecursiveTask(file, filter);

        // when
        List<Path> result = task.compute();

        // then
        assertTrue(result.contains(file));
    }

    @SneakyThrows
    @Test
    void testExtensionFilter() {
        // given
        Path file = Files.createFile(tempDir.resolve("testfile.txt"));
        ExtensionFilter filter = new ExtensionFilter(file, "txt");

        // when and then
        assertTrue(filter.match());
    }

}

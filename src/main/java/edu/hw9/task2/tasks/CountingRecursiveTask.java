package edu.hw9.task2.tasks;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class CountingRecursiveTask extends RecursiveTask<Integer> {
    private static final int NEEDED_FILES_AMOUNT = 1000;
    private static final List<Path> NEEDED_DIRECTORIES = new CopyOnWriteArrayList<>();
    private Path startPath;

    public static List<Path> getNeededDirectories() {
        return NEEDED_DIRECTORIES;
    }

    @Override
    public Integer compute() {
        if (!Files.isDirectory(startPath)) {
            return 1;
        }
        int filesAmount = ForkJoinTask.invokeAll(createSubtasks())
            .stream()
            .mapToInt(ForkJoinTask::join)
            .sum();
        if (filesAmount >= NEEDED_FILES_AMOUNT) {
            NEEDED_DIRECTORIES.add(startPath);
        }
        return filesAmount;
    }

    @SneakyThrows
    private Collection<CountingRecursiveTask> createSubtasks() {
        List<CountingRecursiveTask> dividedTasks = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(startPath)) {
            for (Path filePath : files) {
                dividedTasks.add(new CountingRecursiveTask(filePath));
            }
        }
        return dividedTasks;
    }
}

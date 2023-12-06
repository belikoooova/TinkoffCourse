package edu.hw9.task2.tasks;

import edu.hw9.task2.filters.FileFilter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class FilteringRecursiveTask extends RecursiveTask<List<Path>> {
    private Path startPath;
    private FileFilter filter;

    @Override public List<Path> compute() {
        if (!Files.isDirectory(startPath)) {
            return process();
        }
        return ForkJoinTask.invokeAll(createSubtasks())
            .stream()
            .map(ForkJoinTask::join)
            .reduce(Collections.emptyList(), (a, b) -> {
                var copy = new ArrayList<Path>(Collections.emptyList());
                copy.addAll(a);
                copy.addAll(b);
                return copy;
            });
    }

    private List<Path> process() {
        if (filter.match()) {
            return List.of(startPath);
        }
        return Collections.emptyList();
    }

    @SneakyThrows
    private Collection<FilteringRecursiveTask> createSubtasks() {
        List<FilteringRecursiveTask> dividedTasks = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(startPath)) {
            for (Path filePath : files) {
                dividedTasks.add(new FilteringRecursiveTask(filePath, filter));
            }
        }
        return dividedTasks;
    }
}

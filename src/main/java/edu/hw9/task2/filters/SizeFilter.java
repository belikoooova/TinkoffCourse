package edu.hw9.task2.filters;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class SizeFilter implements FileFilter {
    private Path path;
    private Integer size;

    @SneakyThrows
    @Override
    public boolean match() {
        return Files.size(path) == size;
    }
}

package edu.hw9.task2.filters;

import java.nio.file.Path;

public interface FileFilter {
    boolean match();

    static FileFilter getInstance(Path path, Object param) {
        if (param instanceof Integer size) {
            return new SizeFilter(path, size);
        }
        if (param instanceof String extension) {
            return new ExtensionFilter(path, extension);
        }
        throw new UnsupportedOperationException("Incorrect param to filter");
    }
}

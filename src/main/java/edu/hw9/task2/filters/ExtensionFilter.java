package edu.hw9.task2.filters;

import java.nio.file.Path;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExtensionFilter implements FileFilter {
    private Path path;
    private String extension;

    @Override
    public boolean match() {
        return getFileExtension().equals(extension);
    }

    private String getFileExtension() {
        String pathString = String.valueOf(path);
        int index = pathString.indexOf('.');
        return index == -1 ? "" : pathString.substring(index + 1);
    }
}

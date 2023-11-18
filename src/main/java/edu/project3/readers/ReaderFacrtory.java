package edu.project3.readers;

import edu.project3.readers.Reader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReaderFacrtory {
    private static final Pattern HTTP_PATTERN = Pattern.compile(
        "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");
    private static final PathMatcher FILE_PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**//*.txt");
    private static final PathMatcher DIR_PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**//");

    public static Reader getReader(String stringPath) {
        if (HTTP_PATTERN.matcher(stringPath).find()) {
            return getHttpReader(stringPath);
        }
        Path path = Path.of(stringPath);
        if (FILE_PATH_MATCHER.matches(path)) {
            return new FileReader(path);
        }
        if (DIR_PATH_MATCHER.matches(path)) {
            return getDirReader(path);
        }
        throw new IllegalArgumentException("Can't find info to read.");
    }

    private static HttpReader getHttpReader(String path) {
        try {
            return new HttpReader(new URI(path));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Incorrect url address.", e);
        }
    }

    private static DirectoryReader getDirReader(Path path) {
        List<Path> pathList = new ArrayList<>();
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (FILE_PATH_MATCHER.matches(file)) {
                        pathList.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Error while finding files in the directory. Try type a different path.", e);
        }
        return new DirectoryReader(pathList);
    }
}

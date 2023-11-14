package edu.hw6.task3;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Filters {
    private Filters() {
    }

    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter readable = Files::isReadable;
    public static AbstractFilter writable = Files::isWritable;
    public static AbstractFilter executable = Files::isExecutable;

    public static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractFilter lessThan(long size) {
        return path -> Files.size(path) < size;
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            Pattern pattern = Pattern.compile(".*\\." + glob.substring(glob.indexOf(".") + 1));
            return pattern.matcher(path.toString()).matches();
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile(regex).matcher(path.toString()).find();
    }

    public static AbstractFilter magicNumber(int... bytes) {
        return path -> {
            try (FileInputStream inputStream = new FileInputStream(String.valueOf(path))) {
                for (int givenByte : bytes) {
                    if (inputStream.available() == 0) {
                        return false;
                    }
                    int fileByte = inputStream.read();
                    if (fileByte != givenByte) {
                        return false;
                    }
                }
            }
            return true;
        };
    }
}

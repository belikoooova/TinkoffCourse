package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import static edu.hw6.task3.Filters.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    @Test
    @DisplayName("Test filter union (statement code)")
    void testFilterUnion() {
        // given
        Path dir = Path.of(System.getProperty("user.dir")).resolve("HW6Task3Files");

        // when
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        // then
        assertDoesNotThrow(() -> {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
                entries.forEach(System.out::println);
            }
        });
    }

    @Test
    @DisplayName("Test filter for format and size")
    void testFormatAndSize() throws IOException {
        // given
        Path dir = Path.of(System.getProperty("user.dir")).resolve("HW6Task3Files");

        // when
        DirectoryStream.Filter<Path> filterForFirst = regularFile
            .and(lessThan(100_000_000))
            .and(globMatches("*.jpg"));
        DirectoryStream.Filter<Path> filterForSecond = regularFile
            .and(largerThan(3_000_000))
            .and(globMatches("*.png"))
            .and(magicNumber(0x89, 'P', 'N', 'G'));

        // then
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filterForFirst)) {
            entries.forEach(System.out::println);
        }
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filterForSecond)) {
            entries.forEach(System.out::println);
        }
    }
}

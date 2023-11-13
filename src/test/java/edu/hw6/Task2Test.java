package edu.hw6;

import edu.hw6.task2.CopiedFileHandler;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {
    @Test
    @DisplayName("Put and Contains")
    void putAndContains() throws IOException {
        // given
        Path filePath = Path.of("../hello.txt");

        // when and then
        CopiedFileHandler.cloneFile(filePath);
        assertTrue(Files.exists(filePath));
        CopiedFileHandler.cloneFile(filePath);
        assertTrue(Files.exists(Path.of("../hello - копия.txt")));
        CopiedFileHandler.cloneFile(filePath);
        assertTrue(Files.exists(Path.of("../hello - копия (2).txt")));
        CopiedFileHandler.cloneFile(filePath);
        assertTrue(Files.exists(Path.of("../hello - копия (3).txt")));
    }
}

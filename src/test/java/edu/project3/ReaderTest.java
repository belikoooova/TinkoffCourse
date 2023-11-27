package edu.project3;

import edu.project3.readers.DirectoryReader;
import edu.project3.readers.HttpReader;
import edu.project3.readers.Reader;
import edu.project3.readers.ReaderFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReaderTest {
    private static final String FILE_PATH = "src/main/resources/logs/logs2/logggg.txt";

    @Test
    void testValidHttpPath() {
        // given
        String path =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        // when
        Reader reader = ReaderFactory.getReader(path);

        // then
        assertTrue(reader instanceof HttpReader);
    }

    @Test
    void testGetDirectoryReaderWithValidPath() {
        // given
        String path = "src/**/logggg.txt";

        // when
        Reader reader = ReaderFactory.getReader(path);

        // then
        assertTrue(reader instanceof DirectoryReader);
        assertEquals(1, ((DirectoryReader) reader).getPaths().size());
        assertEquals(FILE_PATH, ((DirectoryReader) reader).getPaths().get(0).toString());
    }

    @Test
    void testGetDirectoryReaderWithAnotherValidPath() {
        // given
        String path = "src/**/logs2/*";

        // when
        Reader reader = ReaderFactory.getReader(path);

        // then
        assertTrue(reader instanceof DirectoryReader);
        assertEquals(1, ((DirectoryReader) reader).getPaths().size());
        assertEquals(FILE_PATH, ((DirectoryReader) reader).getPaths().get(0).toString());
    }

    @Test
    void testGetDirectoryReaderWithInvalidPath() {
        // given
        String path = "nonexistent/path/*.log";

        // when and then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReaderFactory.getReader(path);
        });
        assertEquals("No such file.", exception.getMessage());
    }
}

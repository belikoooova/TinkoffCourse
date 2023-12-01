package edu.project3;

import edu.project3.userinput.FileFormat;
import edu.project3.userinput.UserInputParser;
import edu.project3.userinput.UserInputRecord;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineTest {
    @Test
    void testValidInput() {
        // given
        String[] args = {"--path", "logs/*.log", "--from", "2023-01-01", "--to", "2023-01-31", "--format", "markdown"};

        // when
        UserInputRecord result = UserInputParser.parse(args);

        // then
        assertNotNull(result);
        assertEquals("logs/*.log", result.path());
        assertEquals(OffsetDateTime.parse("2023-01-01T00:00:00+00:00"), result.from());
        assertEquals(OffsetDateTime.parse("2023-01-31T00:00:00+00:00"), result.to());
        assertEquals(FileFormat.MARKDOWN, result.format());
    }

    @Test
    void testMissingPath() {
        // given
        String[] args = {"--from", "2023-01-01", "--to", "2023-01-31"};

        // when and then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserInputParser.parse(args);
        });
        assertEquals("The path was not specified.", exception.getMessage());
    }

    @Test
    void testInvalidFormat() {
        // given
        String[] args = {"--path", "logs/*.log", "--format", "invalidFormat"};

        // when and then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserInputParser.parse(args);
        });
        assertEquals("Incorrect format of file", exception.getMessage());
    }
}

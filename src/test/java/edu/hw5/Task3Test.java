package edu.hw5;

import edu.hw5.task3.DashParser;
import edu.hw5.task3.GeneralDateParser;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void testParseCorrectDate() {
        // given
        String[] dates = {
            "2020-10-10",
            "2020-12-2",
            "1/3/1976",
            "1/3/20",
            "tomorrow",
            "today",
            "yesterday",
            "1 day ago",
            "2234 days ago"
        };

        // when and then
        for (String dateStr : dates) {
            Optional<LocalDate> parsedDate = GeneralDateParser.parse(dateStr);
            assertTrue(parsedDate.isPresent());
        }
    }

    @Test
    void testParseIncorrectDate() {
        // given
        String[] dates = {
            "The 3rd of September, 2023",
            "3.9.2023",
            "33/9/2023"
        };

        // when and then
        for (String dateStr : dates) {
            Optional<LocalDate> parsedDate = GeneralDateParser.parse(dateStr);
            assertFalse(parsedDate.isPresent());
        }
    }
}

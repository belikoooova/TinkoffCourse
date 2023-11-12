package edu.hw5;

import edu.hw5.task1.TimeCounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task1Test {
    private static final int CORRECT_HOURS = 3;
    private static final int CORRECT_MINUTES = 40;
    private static final int MULTIPLICATOR = 60;

    @Test
    @DisplayName("Sample test")
    void sampleTest() {
        // given
        List<String> data = new ArrayList<>();
        data.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        data.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        // when
        var result = TimeCounter.getAverageTime(data);

        // then
        var totalSecs = result.getSeconds();
        assertThat((totalSecs / MULTIPLICATOR) % MULTIPLICATOR).isEqualTo(CORRECT_MINUTES);
        assertThat(totalSecs / (MULTIPLICATOR * MULTIPLICATOR)).isEqualTo(CORRECT_HOURS);
    }

    @Test
    @DisplayName("Incorrect interval format")
    void incorrectIntervalTest() {
        // given
        List<String> data = new ArrayList<>();
        data.add("2022-03-12, 20:20 - 2022-03-12, 23:50 - jj");
        data.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        // when and then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> TimeCounter.getAverageTime(data));
        assertThat(exception.getMessage()).isEqualTo("Interval must contain 2 dates: start and end");
    }

    @Test
    @DisplayName("Incorrect time format")
    void incorrectTimeTest() {
        // given
        List<String> data = new ArrayList<>();
        data.add("2022-03-12, 26:20 - 2022-03-12, 23:50");
        data.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        // when and then
        Exception exception = assertThrows(DateTimeParseException.class, () -> TimeCounter.getAverageTime(data));
    }
}

package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeCounter {
    private static final int PAIR_SIZE = 2;
    private static final int MINUTES_IN_HOUR = 60;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public static String getAverageTime(List<String> timeIntervalStrings) {
        List<TimeSegment> startEndPairs = parseStrings(timeIntervalStrings);
        List<Duration> intervals = getIntervals(startEndPairs);
        long averageMinutes = getAverageTimeInMinutes(intervals);
        Duration duration = Duration.ofMinutes(averageMinutes);
        return String.format("%dч %dм", duration.toHours(), duration.toMinutes() % MINUTES_IN_HOUR);
    }

    private static List<TimeSegment> parseStrings(List<String> timeIntervalStrings) {
        List<TimeSegment> startEndPairs = new ArrayList<>();
        for (var timeString : timeIntervalStrings) {
            String[] splittedString = timeString.split(" - ");
            if (splittedString.length != PAIR_SIZE) {
                throw new IllegalArgumentException("Interval must contain 2 dates: start and end");
            }
            TimeSegment segment = new TimeSegment(
                LocalDateTime.parse(splittedString[0], FORMATTER),
                LocalDateTime.parse(splittedString[1], FORMATTER)
            );
            startEndPairs.add(segment);
        }
        return startEndPairs;
    }

    private static List<Duration> getIntervals(List<TimeSegment> startEndPairs) {
        List<Duration> intervals = new ArrayList<>();
        for (var startEndPair : startEndPairs) {
            intervals.add(Duration.between(startEndPair.start(), startEndPair.end()));
        }
        return intervals;
    }

    private static long getAverageTimeInMinutes(List<Duration> intervals) {
        long total = 0;
        for (var interval : intervals) {
            total += interval.toMinutes();
        }
        return total / intervals.size();
    }

    private record TimeSegment(LocalDateTime start, LocalDateTime end) {
    }
}

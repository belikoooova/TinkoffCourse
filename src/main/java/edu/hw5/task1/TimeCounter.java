package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeCounter {
    private static final int PAIR_SIZE = 2;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private TimeCounter() {
    }

    public static Duration getAverageTime(List<String> timeIntervalStrings) {
        List<LocalDateTime[]> startEndPairs = parseStrings(timeIntervalStrings);
        List<Duration> intervals = getIntervals(startEndPairs);
        long averageMinutes = getAverageTimeInMinutes(intervals);
        return Duration.ofMinutes(averageMinutes);
    }

    private static List<LocalDateTime[]> parseStrings(List<String> timeIntervalStrings) {
        List<LocalDateTime[]> startEndPairs = new ArrayList<>();
        for (var timeString : timeIntervalStrings) {
            String[] splittedString = timeString.split(" - ");
            if (splittedString.length != PAIR_SIZE) {
                throw new IllegalArgumentException("Interval must contain 2 dates: start and end");
            }
            LocalDateTime[] startEndPair = new LocalDateTime[2];
            startEndPair[0] = LocalDateTime.parse(splittedString[0], FORMATTER);
            startEndPair[1] = LocalDateTime.parse(splittedString[1], FORMATTER);
            startEndPairs.add(startEndPair);
        }
        return startEndPairs;
    }

    private static List<Duration> getIntervals(List<LocalDateTime[]> startEndPairs) {
        List<Duration> intervals = new ArrayList<>();
        for (var startEndPair : startEndPairs) {
            intervals.add(Duration.between(startEndPair[0], startEndPair[1]));
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
}

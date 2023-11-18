package edu.project3.logs;

import edu.project3.userinputs.UserInputRecord;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class LogAnalyzer {
    private static final int TOP = 3;
    private Stream<LogRecord> logs;
    private UserInputRecord request;

    public LogReport getReport() {
        getLogsWithNeededDate();
        OffsetDateTime startDate = request.from();
        OffsetDateTime endDate = request.to();
        long totalAmount = logs.count();
        int averageSize = getAverageSize();
        var mostPopularResources = getMostPopularResources();
        var mostPopularAnswers = getMostPopularAnswers();
        var mostPopularTypes = getMostPopularTypes();
        long maxAmountRequestsPerDay = getMaxAmountRequestsPerDay().longValue();
        var dayWithMaxAmountRequests = getDayWithMaxAmountRequests();
        return new LogReport(
            startDate,
            endDate,
            totalAmount,
            averageSize,
            mostPopularResources,
            mostPopularAnswers,
            mostPopularTypes,
            maxAmountRequestsPerDay,
            dayWithMaxAmountRequests
        );
    }

    private void getLogsWithNeededDate() {
        OffsetDateTime startDate = request.from() == null ? OffsetDateTime.MIN : request.from();
        OffsetDateTime endDate = request.to() == null ? OffsetDateTime.MAX : request.to();
        var logList = logs.toList();
        List<LogRecord> newLogList = new ArrayList<>();
        for (var log : logList) {
            if (!log.date().isBefore(startDate) && !log.date().isAfter(endDate)) {
                newLogList.add(log);
            }
        }
        logs = newLogList.stream();
    }

    private int getAverageSize() {
        return ((int) logs.mapToInt(LogRecord::size).average().getAsDouble());
    }

    private List<Map.Entry<String, Long>> getMostPopularResources() {
        Map<String, Long> map = logs.collect(Collectors.groupingBy(LogRecord::resource, Collectors.counting()));
        return getTop(map);
    }

    private List<Map.Entry<Integer, Long>> getMostPopularAnswers() {
        Map<Integer, Long> map = logs.collect(Collectors.groupingBy(LogRecord::httpCode, Collectors.counting()));
        return getTop(map);
    }

    private List<Map.Entry<String, Long>> getMostPopularTypes() {
        Map<String, Long> map = logs.collect(Collectors.groupingBy(LogRecord::requestType, Collectors.counting()));
        return getTop(map);
    }

    public static <K> List<Map.Entry<K, Long>> getTop(Map<K, Long> map) {
        List<Map.Entry<K, Long>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        List<Map.Entry<K, Long>> finalList = new ArrayList<>();
        for (int i = 0; i < Math.min(list.size(), TOP); ++i) {
            finalList.add(list.get(list.size() - i - 1));
        }
        return finalList;
    }

    private Long getMaxAmountRequestsPerDay() {
        return groupAndSortByDate().get(groupAndSortByDate().size() - 1).getValue();
    }

    private OffsetDateTime getDayWithMaxAmountRequests() {
        return groupAndSortByDate().get(groupAndSortByDate().size() - 1).getKey();
    }

    private List<Map.Entry<OffsetDateTime, Long>> groupAndSortByDate() {
        var map = logs.collect(Collectors.groupingBy(LogRecord::date, Collectors.counting()));
        List<Map.Entry<OffsetDateTime, Long>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }
}

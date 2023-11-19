package edu.project3.logs;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public record LogReport(
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    long totalAmount,
    int averageSize,
    List<Map.Entry<String, Long>> mostPopularResources,
    List<Map.Entry<Integer, Long>> mostPopularAnswers,
    List<Map.Entry<String, Long>> mostPopularTypes,
    long maxAmountRequestsPerDay,
    LocalDate dayWithMaxAmountRequests
    ) {
}

package edu.hw5.task3;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GeneralDateParser {
    private GeneralDateParser() {
    }

    private static final List<DateParser> PARSERS =
        List.of(new DashParser(), new DayAgoParser(), new LongSlashParser(), new ShortSlashParser(), new WordParser());

    public static Optional<LocalDate> parse(String date) {
        return PARSERS
            .stream()
            .filter(p -> p.canParseDate(date))
            .findFirst()
            .map(dateParser -> dateParser.parseDate(date));
    }
}

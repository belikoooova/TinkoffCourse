package edu.project3.logs;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern NGINX_REGEX =
        Pattern.compile("((?:\\d{1,3}\\.)\\d{1,3}) - (\\S*) \\[(\\d{2}/[A-Z][a-z]+/\\d{4}(?::\\d{2}){3} [+-]\\d{4})] \"([A-Z]+) (\\S*) \\S*\" (\\d{3}) (\\d+) \"(.*)\" \"(.*)\"");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss ZZZZZ");

    public static LogRecord parse(String stringLog) {
        Matcher matcher = NGINX_REGEX.matcher(stringLog);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("NGINX log was not in correct format: %s", stringLog));
        }
        String address = matcher.group(1);
        String user = matcher.group(2);
        OffsetDateTime date = OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER);
        String requestType = matcher.group(4);
        String resource = matcher.group(5);
        int httpCode = Integer.parseInt(matcher.group(6));
        int size = Integer.parseInt(matcher.group(7));
        String referer = matcher.group(8);
        String userAgent = matcher.group(9);
        return new LogRecord(address, user, date, requestType, resource, httpCode, size, referer, userAgent);
    }
}

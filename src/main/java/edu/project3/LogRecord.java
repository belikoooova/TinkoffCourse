package edu.project3;

import java.time.OffsetDateTime;

public record LogRecord(String address, String user, OffsetDateTime date, String requestType, String resource,
                        int httpCode, int size, String httpReferer, String httpAgent) {
}

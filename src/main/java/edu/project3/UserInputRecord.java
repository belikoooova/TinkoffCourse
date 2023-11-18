package edu.project3;

import java.time.OffsetDateTime;
public record UserInputRecord(String path, OffsetDateTime from, OffsetDateTime to, FileFormat format) {
}

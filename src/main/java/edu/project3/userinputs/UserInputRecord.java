package edu.project3.userinputs;

import java.time.OffsetDateTime;

public record UserInputRecord(String path, OffsetDateTime from, OffsetDateTime to, FileFormat format) {
}

package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogParser;
import edu.project3.log.LogRecord;
import edu.project3.log.LogReport;
import edu.project3.readers.Reader;
import edu.project3.readers.ReaderFactory;
import edu.project3.userinput.UserInputParser;
import edu.project3.userinput.UserInputRecord;
import edu.project3.writers.Writer;
import edu.project3.writers.WriterFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    public static void main(String[] args) {
        UserInputRecord userInput = UserInputParser.parse(args);
        Reader reader = ReaderFactory.getReader(userInput.path());
        List<LogRecord> logRecordStream = reader.read().stream().map(LogParser::parse).toList();
        LogReport report = new LogAnalyzer(logRecordStream, userInput).getReport();
        Writer writer = WriterFactory.getWriter(userInput, report);
        writer.write();
    }
}

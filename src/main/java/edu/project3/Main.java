package edu.project3;

import edu.project3.readers.Reader;
import edu.project3.readers.ReaderFacrtory;
import edu.project3.writers.Writer;
import edu.project3.writers.WriterFactory;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        UserInputRecord userInput = UserInputParser.parse(args);
        Reader reader = ReaderFacrtory.getReader(userInput.path());
        Stream<LogRecord> logRecordStream = reader.read().stream().map(LogParser::parse);
        LogReport report = new LogAnalyzer(logRecordStream, userInput).getReport();
        Writer writer = WriterFactory.getWriter(userInput, report);
        writer.write();
    }
}

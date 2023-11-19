package edu.project3;

import edu.project3.logs.LogAnalyzer;
import edu.project3.logs.LogParser;
import edu.project3.logs.LogRecord;
import edu.project3.logs.LogReport;
import edu.project3.readers.Reader;
import edu.project3.readers.ReaderFacrtory;
import edu.project3.userinputs.UserInputParser;
import edu.project3.userinputs.UserInputRecord;
import edu.project3.writers.Writer;
import edu.project3.writers.WriterFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserInputRecord userInput = UserInputParser.parse(args);
        Reader reader = ReaderFacrtory.getReader(userInput.path());
        List<LogRecord> logRecordStream = reader.read().stream().map(LogParser::parse).toList();
        LogReport report = new LogAnalyzer(logRecordStream, userInput).getReport();
        Writer writer = WriterFactory.getWriter(userInput, report);
        writer.write();
    }
}
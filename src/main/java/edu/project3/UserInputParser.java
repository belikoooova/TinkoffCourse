package edu.project3;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.cli.*;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInputParser {
    private static final Option path = Option.builder("p").longOpt("path").hasArg().build();
    private static final Option from = Option.builder("f").longOpt("from").hasArg().build();
    private static final Option to = Option.builder("t").longOpt("to").hasArg().build();
    private static final Option format = Option.builder("fmt").longOpt("format").hasArg().build();
    public static UserInputRecord parse(String[] args) {
        CommandLine commandLine;
        try {
            commandLine = new DefaultParser().parse(getOptions(), args);
            if (!commandLine.hasOption(path)) {
                throw new IllegalArgumentException("The path was not specified.");
            }
        } catch (ParseException e) {
            throw new RuntimeException("The command line could not be parsed", e);
        }
        String userPath = commandLine.getOptionValue(path);
        OffsetDateTime userFrom = getDate(from, commandLine);
        OffsetDateTime userTo = getDate(to, commandLine);
        FileFormat userFormat = getFormat(commandLine);
        return new UserInputRecord(userPath, userFrom, userTo, userFormat);
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption(path);
        options.addOption(from);
        options.addOption(to);
        options.addOption(format);
        return options;
    }

    private static OffsetDateTime getDate(Option option, CommandLine commandLine) {
        if (commandLine.hasOption(option)) {
            return OffsetDateTime.parse(commandLine.getOptionValue(option));
        }
        return null;
    }

    private static FileFormat getFormat(CommandLine commandLine) {
        if (!commandLine.hasOption(format)) {
            return null;
        }
        var formatString = commandLine.getOptionValue(format);
        switch (formatString) {
            case "markdown" -> {
                return FileFormat.MARKDOWN;
            }
            case "adoc" -> {
                return FileFormat.ADOC;
            }
            default -> throw new IllegalArgumentException("Incorrect format of file");
        }
    }
}

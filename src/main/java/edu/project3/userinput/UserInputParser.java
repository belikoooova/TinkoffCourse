package edu.project3.userinput;

import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInputParser {
    private static final Option PATH = Option.builder("p").longOpt("path").hasArg().build();
    private static final Option FROM = Option.builder("f").longOpt("from").hasArg().build();
    private static final Option TO = Option.builder("t").longOpt("to").hasArg().build();
    private static final Option FORMAT = Option.builder("fmt").longOpt("format").hasArg().build();

    public static UserInputRecord parse(String[] args) {
        CommandLine commandLine;
        try {
            commandLine = new DefaultParser().parse(getOptions(), args);
            if (!commandLine.hasOption(PATH)) {
                throw new IllegalArgumentException("The path was not specified.");
            }
        } catch (ParseException e) {
            throw new RuntimeException("The command line could not be parsed", e);
        }
        String userPath = commandLine.getOptionValue(PATH);
        OffsetDateTime userFrom = getDate(FROM, commandLine);
        OffsetDateTime userTo = getDate(TO, commandLine);
        FileFormat userFormat = getFORMAT(commandLine);
        return new UserInputRecord(userPath, userFrom, userTo, userFormat);
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption(PATH);
        options.addOption(FROM);
        options.addOption(TO);
        options.addOption(FORMAT);
        return options;
    }

    private static OffsetDateTime getDate(Option option, CommandLine commandLine) {
        if (commandLine.hasOption(option)) {
            return OffsetDateTime.parse(commandLine.getOptionValue(option) + "T00:00:00+00:00");
        }
        return null;
    }

    private static FileFormat getFORMAT(CommandLine commandLine) {
        if (!commandLine.hasOption(FORMAT)) {
            return null;
        }
        var formatString = commandLine.getOptionValue(FORMAT);
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

package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CLIPrinter implements Printer {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void printLine(HumanReadableMessage message) {
        String line = message.toString();
        logger.info(line);
    }

    @Override
    public void printLine(String line) {
        logger.info(line);
    }
}

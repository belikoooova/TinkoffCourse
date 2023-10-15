package edu.project1;

import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

public class CLIPrinter implements Printer {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @Override
    public void outputLine(Object object) {
        String line = object.toString();
        logger.info(line);
    }

    @Override
    public void outputLine(String line) {
        logger.info(line);
    }

    @Override
    public void outputLine(String line, int firstParameter, int secondParameter) {
        String parametrizedLine = String.format(line, firstParameter, secondParameter);
        logger.info(parametrizedLine);
    }

    @Override
    public void outputLine(String line, String firstParameter) {
        String parametrizedLine = String.format(line, firstParameter);
        logger.info(parametrizedLine);
    }
}
